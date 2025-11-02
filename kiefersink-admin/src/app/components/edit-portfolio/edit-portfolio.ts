import { Component, Input, inject, OnInit } from '@angular/core';
import { Portfolio } from '../../models/portfolio';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, NgForm } from '@angular/forms';
import { PortfolioService } from '../../services/Portfolio/portfolio-service';
import { Category } from '../../models/category';
import { Offering } from '../../models/offering';
import { CategoryService } from '../../services/Category/category-service';
import { ArtistService } from '../../services/Artist/artist-service';
import { Artist } from '../../models/artist';

@Component({
  selector: 'edit-portfolio-form',
  imports: [FontAwesomeModule, FormsModule],
  templateUrl: './edit-portfolio.html',
  styleUrl: './edit-portfolio.css',
})
export class EditPortfolio implements OnInit {
  @Input() portfolio: Portfolio | null = null;
  // ====================================================== //
  categories: Category[] = [];
  artists: Artist[] = [];
  // ====================================================== //
  selectedCategory: Category | null = null;
  filteredOfferings: Offering[] = [];
  // ====================================================== //
  selectedFile: File | null = null;
  previewUrl: string | ArrayBuffer | null = null;
  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];

      const reader = new FileReader();
      reader.onload = () => {
        this.previewUrl = reader.result;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }
  // ====================================================== //
  private service = inject(PortfolioService);
  private categoryService = inject(CategoryService);
  private artistService = inject(ArtistService);
  // ====================================================== //
  ngOnInit() {
    this.categoryService.getAllCategoriesWithOfferings().subscribe((data) => {
      this.categories = data;
    });

    this.artistService.getAllArtists().subscribe((data) => {
      this.artists = data;
    });
  }
  // ====================================================== //
  onUpdate(form: NgForm) {
    if (!this.portfolio || this.portfolio.id == null) {
      console.error('Artist not found.');
      return;
    }
    const formData = new FormData();
    formData.append(
      'portfolio',
      new Blob([JSON.stringify(this.portfolio)], { type: 'application/json' }),
    );
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    // const portfolioBlob = formData.get('portfolio') as Blob;
    // if (portfolioBlob) {
    //   portfolioBlob.text().then((jsonText) => {
    //     console.log('Portfolio JSON:', JSON.parse(jsonText));
    //   });
    // }

    this.service.updatePortfolio(this.portfolio.id, formData).subscribe({
      next: (response) => {
        console.log('Portfolio updated successfully!:', response);
        this.closeModal(form);
        window.location.reload();
      },
      error: (err) => console.error('Error updating artist:', err),
    });
  }
  // ====================================================== //
  onCategoryChange() {
    this.filteredOfferings = this.selectedCategory?.offerings || [];

    this.portfolio!.offering = {} as Offering;
  }
  // ====================================================== //
  closeModal(form: NgForm) {
    const dialog = document.getElementById('edit_portfolio_form') as HTMLDialogElement;
    form.resetForm();
    this.previewUrl = null;
    this.selectedFile = null;
    dialog.close();
  }

  ngOnChanges() {
    if (this.categories.length && this.portfolio?.offering?.category) {
      this.selectedCategory =
        this.categories.find((cat) => cat.id === this.portfolio?.offering.category?.id) ?? null;

      this.filteredOfferings = this.selectedCategory?.offerings || [];

      const currentOffering = this.filteredOfferings.find(
        (off) => off.id === this.portfolio!.offering.id,
      );

      if (currentOffering) {
        this.portfolio!.offering = currentOffering;
      }

      if (this.portfolio!.artist) {
        const match = this.artists.find((a) => a.id === this.portfolio!.artist.id);
        if (match) this.portfolio.artist = match;
      }
    }
  }
}
