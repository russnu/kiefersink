import { Component, Input, inject, OnInit } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, NgForm } from '@angular/forms';
import { Offering } from '../../models/offering';
import { OfferingService } from '../../services/Offering/offering-service';
@Component({
  selector: 'edit-offering-form',
  imports: [FontAwesomeModule, FormsModule],
  templateUrl: './edit-offering.html',
  styleUrl: './edit-offering.css',
})
export class EditOffering {
  @Input() offering: Offering | null = null;
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
  onUpdate(form: NgForm) {
    if (!this.offering || this.offering.id == null) {
      console.error('Offering not found.');
      return;
    }
    const formData = new FormData();
    formData.append(
      'offering',
      new Blob([JSON.stringify(this.offering)], { type: 'application/json' }),
    );
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    // const offeringBlob = formData.get('offering') as Blob;
    // if (offeringBlob) {
    //   offeringBlob.text().then((jsonText) => {
    //     console.log('Offering JSON:', JSON.parse(jsonText));
    //   });
    // }

    this.service.updateOffering(this.offering.id, formData).subscribe({
      next: (response) => {
        console.log('Offering updated successfully!:', response);
        this.closeModal(form);
        window.location.reload();
      },
      error: (err) => console.error('Error updating offering:', err),
    });
  }
  // ====================================================== //
  closeModal(form: NgForm) {
    const dialog = document.getElementById('edit_offering_form') as HTMLDialogElement;
    form.resetForm();
    this.previewUrl = null;
    this.selectedFile = null;
    dialog.close();
  }
}
