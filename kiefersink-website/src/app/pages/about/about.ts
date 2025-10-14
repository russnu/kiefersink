import { Component, inject, OnInit } from '@angular/core';
import { LucideAngularModule } from 'lucide-angular';
import {
  CompanyContact,
  CompanyContactService,
} from '../../services/company_contact/company-contact-service';
import { Settings, SettingsService } from '../../services/settings/settings-service';

@Component({
  selector: 'app-about',
  imports: [LucideAngularModule],
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

  displayContactLink(contact: CompanyContact) {
    if (contact.platform === 'email') {
      return {
        href: `mailto:${contact.handle}`,
        icon: 'mail',
      };
    }
    if (contact.platform === 'phone') {
      return {
        href: `tel:${contact.handle}`,
        icon: 'phone',
      };
    }
    return {
      href: contact.url,
      icon: contact.platform,
    };
  }
}
