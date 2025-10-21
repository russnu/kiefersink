import { Component, inject, OnInit } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RouterLink } from '@angular/router';
import {
  CompanyContact,
  CompanyContactService,
} from '../../services/company_contact/company-contact-service';
import { SettingsService } from '../../services/settings/settings-service';

@Component({
  selector: 'app-about',
  imports: [FontAwesomeModule, RouterLink],
  templateUrl: './about.html',
  styleUrl: './about.css',
})
export class About implements OnInit {
  companyContacts: CompanyContact[] = [];
  address = '';
  openHours = '';

  private companyContactService = inject(CompanyContactService);
  private settingsService = inject(SettingsService);

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
}
