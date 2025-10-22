import { Component, inject, OnInit } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RouterLink } from '@angular/router';
import {
  CompanyContact,
  CompanyContactService,
} from '../../services/company_contact/company-contact-service';
import { SettingsService } from '../../services/settings/settings-service';
import { Category, CategoryService } from '../../services/category/category-service';

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
  categories: Category[] = [];

  private companyContactService = inject(CompanyContactService);
  private settingsService = inject(SettingsService);
  private categoryService = inject(CategoryService);

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

    this.categoryService.getAllCategoriesWithOfferings().subscribe((data) => {
      this.categories = data;
    });
  }

  displayLinks(contact: CompanyContact) {
    return this.companyContactService.displayContactLink(contact);
  }
}
