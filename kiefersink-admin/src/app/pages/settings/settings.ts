import { Component, inject, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Router, RouterLink } from '@angular/router';
import { Settings as SettingsModel } from '../../models/settings';
import { SettingsService } from '../../services/Settings/settings-service';
import { CompanyContact } from '../../models/company-contact';
import { CompanyContactService } from '../../services/CompanyContact/company-contact-service';
import { CreateCompanyContact } from '../../components/create-company-contact/create-company-contact';

@Component({
  selector: 'app-settings',
  imports: [FontAwesomeModule, FormsModule, CreateCompanyContact],
  templateUrl: './settings.html',
  styleUrl: './settings.css',
})
export class Settings {
  settings: SettingsModel[] = [];
  companyContacts: CompanyContact[] = [];
  // ====================================================== //
  selectedCompanyContact: CompanyContact | null = null;
  // ====================================================== //
  selectedFile: File | null = null;
  previewUrl: string | ArrayBuffer | null = null;
  onFileSelected(event: Event, setting: any) {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];

      this.saveSetting(setting);
    }
  }
  // ====================================================== //
  private settingsService = inject(SettingsService);
  private companyContactService = inject(CompanyContactService);
  // ====================================================== //
  ngOnInit() {
    this.settingsService.getAllSettings().subscribe((settings) => {
      this.settings = settings.map((s) => ({ ...s, editing: false }));
    });
    this.companyContactService.getAllCompanyContacts().subscribe((companyContacts) => {
      this.companyContacts = companyContacts.map((cc) => ({ ...cc, editing: false }));
    });
  }
  // ====================================================== //
  toggleEdit(item: any) {
    item.editing = !item.editing;
  }
  // ====================================================== //
  saveSetting(setting: any) {
    setting.editing = false;

    const formData = new FormData();
    formData.append('setting', new Blob([JSON.stringify(setting)], { type: 'application/json' }));
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    // const settingsBlob = formData.get('setting') as Blob;
    // if (settingsBlob) {
    //   settingsBlob.text().then((jsonText) => {
    //     console.log('Setting JSON:', JSON.parse(jsonText));
    //   });
    // }

    this.settingsService.updateSettings(setting.settingKey, formData).subscribe({
      next: (response) => {
        console.log('Settings updated successfully!:', response);
        window.location.reload();
      },
      error: (err) => console.error('Error updating settings:', err),
    });
  }
  // ====================================================== //
  saveContact(contact: any) {
    contact.editing = false;

    this.companyContactService.updateCompanyContact(contact.id, contact).subscribe({
      next: (response) => {
        console.log('Company contact updated successfully!:', response);
        window.location.reload();
      },
      error: (err) => console.error('Error updating company contact:', err),
    });
  }
  // ====================================================== //
  openDeleteCompanyContactModal(companyContact: CompanyContact) {
    this.selectedCompanyContact = companyContact;
    const dialog = document.getElementById('delete_company_contact_confirm') as HTMLDialogElement;
    dialog.showModal();
  }
  // ====================================================== //
  deleteCompanyContact() {
    if (!this.selectedCompanyContact) return;
    this.companyContactService.deleteCompanyContact(this.selectedCompanyContact.id!).subscribe({
      next: () => {
        const dialog = document.getElementById(
          'delete_company_contact_confirm',
        ) as HTMLDialogElement;
        dialog?.close();
        window.location.reload();
      },
      error: (err) => console.error('Error deleting company contact:', err),
    });
  }
  // ====================================================== //
  toTitleCase(s: string) {
    const result = s.replace(/([A-Z])/g, ' $1');
    return result.charAt(0).toUpperCase() + result.slice(1);
  }

  // ====================================================================================================================================== //
}
