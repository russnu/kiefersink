import { Component, inject, OnInit } from '@angular/core';
import { Category } from '../../models/category';
import { CategoryService } from '../../services/Category/category-service';
import { FormsModule, NgForm } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Router, RouterLink } from '@angular/router';
import { CreateCategory } from '../../components/create-category/create-category';
import { CreateOffering } from '../../components/create-offering/create-offering';
import { Offering } from '../../models/offering';
import { EditOffering } from '../../components/edit-offering/edit-offering';
import { OfferingService } from '../../services/Offering/offering-service';

@Component({
  selector: 'app-offerings',
  imports: [FontAwesomeModule, FormsModule, CreateCategory, CreateOffering, EditOffering],
  templateUrl: './offerings.html',
  styleUrl: './offerings.css',
})
export class Offerings implements OnInit {
  categories: Category[] = [];
  // ====================================================== //
  selectedCategory: Category | null = null;
  selectedOffering: Offering | null = null;
  // ====================================================== //
  private offeringService = inject(OfferingService);
  private categoryService = inject(CategoryService);
  private router = inject(Router);
  // ====================================================== //
  ngOnInit() {
    this.categoryService.getAllCategoriesWithOfferings().subscribe((categories) => {
      this.categories = categories.map((c) => ({ ...c, editing: false }));
    });
  }
  // ====================================================== //
  toggleEdit(category: Category) {
    category.editing = !category.editing;
  }
  // ====================================================== //
  saveCategory(category: Category) {
    this.categoryService.updateCategory(category.id!, category).subscribe({
      next: (response) => {
        console.log('Category updated successfully!:', response);
        category.editing = false;
        window.location.reload();
      },
      error: (err) => console.error('Error updating category:', err),
    });
  }
  // ====================================================== //
  openDeleteCategoryModal(category: Category) {
    this.selectedCategory = category;
    const dialog = document.getElementById('delete_category_confirm') as HTMLDialogElement;
    dialog.showModal();
  }
  // ====================================================== //
  deleteCategory() {
    if (!this.selectedCategory) return;
    this.categoryService.deleteCategory(this.selectedCategory.id!).subscribe({
      next: () => {
        const dialog = document.getElementById('delete_category_confirm') as HTMLDialogElement;
        dialog?.close();
        window.location.reload();
      },
      error: (err) => console.error('Error deleting category:', err),
    });
  }
  // ====================================================================================================================================== //
  openCreateOfferingModal(category: Category) {
    this.selectedCategory = category;
    (document.getElementById('create_offering_form') as HTMLDialogElement).showModal();
  }
  // ====================================================== //
  openEditOfferingModal(offering: Offering) {
    this.selectedOffering = JSON.parse(JSON.stringify(offering));
    const dialog = document.getElementById('edit_offering_form') as HTMLDialogElement;
    dialog.showModal();
  }
  // ====================================================== //
  openDeleteOfferingModal(offering: Offering) {
    this.selectedOffering = offering;
    const dialog = document.getElementById('delete_offering_confirm') as HTMLDialogElement;
    dialog.showModal();
  }
  // ====================================================== //
  deleteOffering() {
    if (!this.selectedOffering) return;
    this.offeringService.deleteOffering(this.selectedOffering.id!).subscribe({
      next: () => {
        const dialog = document.getElementById('delete_offering_confirm') as HTMLDialogElement;
        dialog?.close();
        window.location.reload();
      },
      error: (err) => console.error('Error deleting artist:', err),
    });
  }
}
