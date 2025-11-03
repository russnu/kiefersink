import { Component, OnInit, inject } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TitleCasePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import {
  CompanyContact,
  CompanyContactService,
} from '../../services/company_contact/company-contact-service';
import { Settings, SettingsService } from '../../services/settings/settings-service';

@Component({
  selector: 'app-footer',
  imports: [RouterLink, FontAwesomeModule, TitleCasePipe],
  templateUrl: './footer.html',
  styleUrl: './footer.css',
})
export class Footer implements OnInit {
  companyContacts: CompanyContact[] = [];
  companyName = '';
  address = '';
  openHours = '';

  private companyContactService = inject(CompanyContactService);
  private settingsService = inject(SettingsService);

  ngOnInit() {
    this.companyContactService.getCompanyContacts().subscribe((data) => {
      this.companyContacts = data;
    });

    this.settingsService.getSettings('companyName').subscribe((data) => {
      this.companyName = data.settingValue;
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
