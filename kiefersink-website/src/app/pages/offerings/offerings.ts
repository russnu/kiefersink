import { Component, inject, OnInit } from '@angular/core';
import { Category, CategoryService } from '../../services/category/category-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-offerings',
  imports: [FontAwesomeModule, RouterLink],
  templateUrl: './offerings.html',
  styleUrl: './offerings.css',
})
export class Offerings implements OnInit {
  categories: Category[] = [];

  private categoryService = inject(CategoryService);
  private router = inject(Router);

  ngOnInit() {
    this.categoryService.getAllCategoriesWithOfferings().subscribe((categories) => {
      this.categories = categories;
    });
  }

  viewGalleryByOffering(offeringId: number) {
    this.router.navigate(['/gallery'], {
      queryParams: { offering: offeringId },
    });
  }
}
