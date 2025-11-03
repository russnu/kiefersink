import { Component, inject, OnInit, Input } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, NgForm } from '@angular/forms';
import { Category } from '../../models/category';
import { Offering } from '../../models/offering';
import { OfferingService } from '../../services/Offering/offering-service';

@Component({
  selector: 'create-offering-form',
  imports: [FontAwesomeModule, FormsModule],
  templateUrl: './create-offering.html',
  styleUrl: './create-offering.css',
})
export class CreateOffering {
  @Input() category: Category | null = null;

  offering: Offering = {
    name: '',
    description: '',
    imageUrl: '',
    priceRange: '',
  };

  ngOnChanges() {
    if (this.category) {
      this.offering.category = this.category!;
    }
  }
  // ====================================================== //
  categories: Category[] = [];
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
  private service = inject(OfferingService);
  // ====================================================== //
  onCreate(form: NgForm) {
    if (form.invalid) {
      form.control.markAllAsTouched();
      return;
    }

    if (this.offering.category && 'offerings' in this.offering.category) {
      delete this.offering.category.offerings;
    }

    const formData = new FormData();
    formData.append(
      'offering',
      new Blob([JSON.stringify(this.offering)], { type: 'application/json' }),
    );
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    const offeringBlob = formData.get('offering') as Blob;
    if (offeringBlob) {
      offeringBlob.text().then((jsonText) => {
        console.log('Offering JSON:', JSON.parse(jsonText));
      });
    }

    this.service.createOffering(formData).subscribe({
      next: (response) => {
        console.log('Offering created successfully!:', response);
        this.closeModal(form);
        window.location.reload();
      },
      error: (err) => console.error('Error creating offering:', err),
    });
  }
  // ====================================================== //
  closeModal(form: NgForm) {
    const dialog = document.getElementById('create_offering_form') as HTMLDialogElement;
    form.resetForm();
    this.previewUrl = null;
    this.selectedFile = null;
    const fileInput = document.querySelector('input[type="file"]') as HTMLInputElement;
    if (fileInput) {
      fileInput.value = '';
    }
    dialog.close();
  }
}
