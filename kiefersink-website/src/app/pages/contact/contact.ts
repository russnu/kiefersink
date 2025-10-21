import { Component, inject, OnInit } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule } from '@angular/forms';
import {
  CompanyContact,
  CompanyContactService,
} from '../../services/company_contact/company-contact-service';
import { SettingsService } from '../../services/settings/settings-service';
import { Inquiry, InquiryService } from '../../services/inquiry/inquiry-service';

@Component({
  selector: 'app-contact',
  imports: [FontAwesomeModule, FormsModule],
  templateUrl: './contact.html',
  styleUrl: './contact.css',
})
export class Contact implements OnInit {
  companyContacts: CompanyContact[] = [];
  address = '';
  openHours = '';

  inquiry: Inquiry = {
    customerName: '',
    customerEmail: '',
    customerPhone: '',
    subject: '',
    message: '',
  };

  isSubmitting = false;

  private companyContactService = inject(CompanyContactService);
  private settingsService = inject(SettingsService);
  private inquiryService = inject(InquiryService);

  ngOnInit() {
    this.companyContactService.getCompanyContacts().subscribe((data) => {
      this.companyContacts = data;
    });

    this.settingsService.getSettings('address').subscribe((data) => {
      this.address = data.settingValue;
    });

    this.settingsService.getSettings('openHours').subscribe((data) => {
      this.openHours = data.settingValue;
    });
  }

  displayLinks(contact: CompanyContact) {
    return this.companyContactService.displayContactLink(contact);
  }

  get emailContacts(): CompanyContact[] {
    return this.companyContacts.filter((c) => c.platform === 'email');
  }

  get phoneContacts(): CompanyContact[] {
    return this.companyContacts.filter((c) => c.platform === 'phone');
  }

  get socialContacts(): CompanyContact[] {
    return this.companyContacts.filter((c) => c.platform !== 'phone' && c.platform !== 'email');
  }
  //==========================================================================================//
  onSubmit(event: Event) {
    event.preventDefault();

    if (this.isSubmitting) {
      return;
    }

    this.isSubmitting = true;

    this.inquiryService.sendInquiry(this.inquiry).subscribe({
      next: (response) => {
        console.log('Inquiry submitted successfully:', response);
        // this.showSuccessModal();
        this.resetForm();
        this.isSubmitting = false;
      },
      error: (error) => {
        console.error('Error submitting inquiry:', error);
        // this.showErrorModal();
        this.isSubmitting = false;
      },
    });
  }

  resetForm() {
    this.inquiry = {
      customerName: '',
      customerEmail: '',
      customerPhone: '',
      subject: '',
      message: '',
    };
  }
}
