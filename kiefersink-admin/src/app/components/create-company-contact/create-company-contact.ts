import { Component, inject } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, NgForm } from '@angular/forms';
import { Category } from '../../models/category';
import { Offering } from '../../models/offering';
import { CompanyContactService } from '../../services/CompanyContact/company-contact-service';
import { CompanyContact } from '../../models/company-contact';
// ====================================================== //
@Component({
  selector: 'create-company-contact-form',
  imports: [FontAwesomeModule, FormsModule],
  templateUrl: './create-company-contact.html',
  styleUrl: './create-company-contact.css',
})
// ====================================================== //
export class CreateCompanyContact {
  companyContact: CompanyContact = {
    platform: '',
    handle: '',
    url: '',
  };
  // ====================================================== //
  private service = inject(CompanyContactService);
  // ====================================================== //
  onCreate(form: NgForm) {
    if (form.invalid) {
      form.control.markAllAsTouched();
      return;
    }

    console.log(this.companyContact);

    this.service.createCompanyContact(this.companyContact).subscribe({
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
    const dialog = document.getElementById('create_company_contact_form') as HTMLDialogElement;
    form.resetForm();
    dialog.close();
  }
}
