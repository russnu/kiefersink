import { Component, inject, OnInit } from '@angular/core';
import { Portfolio } from '../../models/portfolio';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, NgForm } from '@angular/forms';
import { Artist } from '../../models/artist';
import { PortfolioService } from '../../services/Portfolio/portfolio-service';
import { Category } from '../../models/category';
import { Offering } from '../../models/offering';
import { CategoryService } from '../../services/Category/category-service';
import { ArtistService } from '../../services/Artist/artist-service';

@Component({
  selector: 'create-portfolio-form',
  imports: [FontAwesomeModule, FormsModule],
  templateUrl: './create-portfolio.html',
  styleUrl: './create-portfolio.css',
})
export class CreatePortfolio implements OnInit {
  portfolio: Portfolio = {
    title: '',
    description: '',
    imageUrl: '',
    artist: {} as Artist,
    offering: {} as Offering,
    featured: false,
  };
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
  onCreate(form: NgForm) {
    if (this.selectedCategory && this.portfolio.offering) {
      this.portfolio.offering.category = { id: this.selectedCategory.id } as Category;
    }
    const formData = new FormData();
    formData.append(
      'portfolio',
      new Blob([JSON.stringify(this.portfolio)], { type: 'application/json' }),
    );
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    const portfolioBlob = formData.get('portfolio') as Blob;
    if (portfolioBlob) {
      portfolioBlob.text().then((jsonText) => {
        console.log('Portfolio JSON:', JSON.parse(jsonText));
      });
    }

    // this.service.createPortfolio(formData).subscribe({
    //   next: (response) => {
    //     console.log('Portfolio created successfully!:', response);
    //     this.closeModal(form);
    //     window.location.reload();
    //   },
    //   error: (err) => console.error('Error creating artist:', err),
    // });
  }
  // ====================================================== //
  onCategoryChange() {
    this.filteredOfferings = this.selectedCategory?.offerings || [];

    console.log(this.selectedCategory);
    this.portfolio.offering = {} as Offering;
  }
  // ====================================================== //
  closeModal(form: NgForm) {
    const dialog = document.getElementById('create_portfolio_form') as HTMLDialogElement;
    form.resetForm();
    this.previewUrl = null;
    this.selectedFile = null;
    dialog.close();
  }
}
