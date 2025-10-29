import { Component, inject, OnInit } from '@angular/core';
import { Category } from '../../models/category';
import { CategoryService } from '../../services/Category/category-service';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-offerings',
  imports: [],
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
}
