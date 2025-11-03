import { Component, inject } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, NgForm } from '@angular/forms';
import { Category } from '../../models/category';
import { CategoryService } from '../../services/Category/category-service';

@Component({
  selector: 'create-category-form',
  imports: [FontAwesomeModule, FormsModule],
  templateUrl: './create-category.html',
  styleUrl: './create-category.css',
})
export class CreateCategory {
  category: Category = {
    name: '',
  };
  // ====================================================== //
  private service = inject(CategoryService);
  // ====================================================== //
  onCreate(form: NgForm) {
    console.log(this.category);
    this.service.createCategory(this.category).subscribe({
      next: (response) => {
        console.log('Category created successfully!:', response);
        this.closeModal(form);
        window.location.reload();
      },
      error: (err) => console.error('Error creating category:', err),
    });
  }
  // ====================================================== //
  closeModal(form: NgForm) {
    const dialog = document.getElementById('create_category_form') as HTMLDialogElement;
    form.resetForm();
    dialog.close();
  }
}
