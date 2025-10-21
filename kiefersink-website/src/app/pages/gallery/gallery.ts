import { Component, inject, OnInit } from '@angular/core';
import { Portfolio, PortfolioService } from '../../services/portfolio/portfolio-service';
import { Category, CategoryService } from '../../services/category/category-service';
import { Artist, ArtistService } from '../../services/artist/artist-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-gallery',
  imports: [FontAwesomeModule],
  templateUrl: './gallery.html',
  styleUrl: './gallery.css',
})
export class Gallery implements OnInit {
  portfolios: Portfolio[] = [];
  filteredPortfolios: Portfolio[] = [];
  paginatedPortfolios: Portfolio[] = [];
  selectedPortfolio: Portfolio | null = null;
  categories: Category[] = [];
  artists: Artist[] = [];

  currentIndex: number = 0;

  currentPage: number = 1;
  itemsPerPage: number = 12; // 3 rows x 4 columns
  totalPages: number = 1;

  selectedArtists: Set<number> = new Set();
  selectedCategories: Set<number> = new Set();
  selectedOfferings: Set<number> = new Set();

  private portfolioService = inject(PortfolioService);
  private categoryService = inject(CategoryService);
  private artistService = inject(ArtistService);

  ngOnInit() {
    this.categoryService.getAllCategoriesWithOfferings().subscribe((categories) => {
      this.categories = categories;
    });

    this.portfolioService.getPortfolios().subscribe((data) => {
      this.portfolios = data;
      this.filteredPortfolios = data;
      this.updatePagination();
    });

    this.artistService.getAllArtists().subscribe((data) => {
      this.artists = data;
    });
  }

  //==================================================================================//
  toggleArtist(artistId: number, event: Event) {
    event.stopPropagation();
    if (this.selectedArtists.has(artistId)) {
      this.selectedArtists.delete(artistId);
    } else {
      this.selectedArtists.add(artistId);
    }
  }
  //----------------------------------------------------------------//
  isArtistSelected(artistId: number): boolean {
    return this.selectedArtists.has(artistId);
  }
  //----------------------------------------------------------------//
  toggleAllArtists(event: Event) {
    event.stopPropagation();
    const checkbox = event.target as HTMLInputElement;

    if (checkbox.checked) {
      this.artists.forEach((artist) => this.selectedArtists.add(artist.id));
    } else {
      this.selectedArtists.clear();
    }
  }
  //----------------------------------------------------------------//
  areAllArtistsSelected(): boolean {
    return this.artists.length > 0 && this.selectedArtists.size === this.artists.length;
  }
  // ================================================================================== //
  toggleCategory(category: Category, event: Event) {
    event.stopPropagation();
    const checkbox = event.target as HTMLInputElement;

    if (checkbox.checked) {
      // Select category and all its offerings
      this.selectedCategories.add(category.id);
      category.offerings?.forEach((offering) => {
        this.selectedOfferings.add(offering.id);
      });
    } else {
      // Deselect category and all its offerings
      this.selectedCategories.delete(category.id);
      category.offerings?.forEach((offering) => {
        this.selectedOfferings.delete(offering.id);
      });
    }
  }
  //----------------------------------------------------------------//
  isCategorySelected(categoryId: number): boolean {
    return this.selectedCategories.has(categoryId);
  }
  // ================================================================================== //
  toggleOffering(categoryId: number, offeringId: number, event: Event) {
    event.stopPropagation();
    const checkbox = event.target as HTMLInputElement;

    if (checkbox.checked) {
      this.selectedOfferings.add(offeringId);

      // Check if all offerings in this category are selected
      const category = this.categories.find((c) => c.id === categoryId);
      const allOfferingsSelected = category?.offerings?.every((o) =>
        this.selectedOfferings.has(o.id),
      );

      if (allOfferingsSelected) {
        this.selectedCategories.add(categoryId);
      }
    } else {
      this.selectedOfferings.delete(offeringId);
      this.selectedCategories.delete(categoryId);
    }
  }

  //----------------------------------------------------------------//
  isOfferingSelected(offeringId: number): boolean {
    return this.selectedOfferings.has(offeringId);
  }
  // ================================================================================== //

  // Apply filters
  applyFilters() {
    let filtered = [...this.portfolios];

    // Filter by artists
    if (this.selectedArtists.size > 0) {
      filtered = filtered.filter((portfolio) => this.selectedArtists.has(portfolio.artist.id));
    }

    // Filter by offerings
    if (this.selectedOfferings.size > 0) {
      filtered = filtered.filter((portfolio) => this.selectedOfferings.has(portfolio.offering.id));
    }

    this.filteredPortfolios = filtered;
  }
  //----------------------------------------------------------------//
  clearFilters() {
    this.selectedArtists.clear();
    this.selectedCategories.clear();
    this.selectedOfferings.clear();
    this.filteredPortfolios = [...this.portfolios];
    this.currentPage = 1;
    this.updatePagination();
  }
  //----------------------------------------------------------------//
  hasActiveArtistFilters(): boolean {
    return this.selectedArtists.size > 0;
  }
  //----------------------------------------------------------------//
  hasActiveCategoryFilters(): boolean {
    return this.selectedCategories.size > 0 || this.selectedOfferings.size > 0;
  }
  //----------------------------------------------------------------//
  getArtistFilterCount(): number {
    return this.selectedArtists.size;
  }

  getCategoryFilterCount(): number {
    return this.selectedOfferings.size;
  }
  //==================================================================================//
  // Modal
  //==================================================================================//
  openModal(portfolio: Portfolio) {
    this.selectedPortfolio = portfolio;
    const modal = document.getElementById('image_modal') as HTMLDialogElement;
    modal?.showModal();
  }
  //----------------------------------------------------------------//
  closeModal() {
    this.selectedPortfolio = null;
    const modal = document.getElementById('image_modal') as HTMLDialogElement;
    modal?.close();
  }
  //==================================================================================//
  // Pagination
  //==================================================================================//
  updatePagination() {
    this.totalPages = Math.ceil(this.filteredPortfolios.length / this.itemsPerPage);
    this.currentPage = Math.min(this.currentPage, this.totalPages || 1);
    this.updatePaginatedPortfolios();
  }
  //----------------------------------------------------------------//
  updatePaginatedPortfolios() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.paginatedPortfolios = this.filteredPortfolios.slice(startIndex, endIndex);
  }
  //----------------------------------------------------------------//
  goToPage(page: number) {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
      this.updatePaginatedPortfolios();
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }
  }
  //----------------------------------------------------------------//
  nextPage() {
    this.goToPage(this.currentPage + 1);
  }
  //----------------------------------------------------------------//
  previousPage() {
    this.goToPage(this.currentPage - 1);
  }
  //----------------------------------------------------------------//
  getPageNumbers(): number[] {
    const pages: number[] = [];
    const maxVisible = 5;

    let start = Math.max(1, this.currentPage - Math.floor(maxVisible / 2));
    let end = Math.min(this.totalPages, start + maxVisible - 1);

    if (end - start < maxVisible - 1) {
      start = Math.max(1, end - maxVisible + 1);
    }

    for (let i = start; i <= end; i++) {
      pages.push(i);
    }

    return pages;
  }
}
