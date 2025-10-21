import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
//==========================================================//
export interface CompanyContact {
  id: number;
  platform: string;
  handle: string;
  url: string;
}
//==========================================================//
@Injectable({
  providedIn: 'root',
})
export class CompanyContactService {
  private apiUrl = `${environment.apiBaseUrl}/company_contact`;

  constructor(private http: HttpClient) {}

  getCompanyContacts(): Observable<CompanyContact[]> {
    return this.http.get<CompanyContact[]>(this.apiUrl);
  }

  displayContactLink(contact: CompanyContact) {
    if (contact.platform === 'email') {
      return {
        href: `mailto:${contact.handle}`,
        icon: 'envelope',
        prefix: 'fas',
      };
    }
    if (contact.platform === 'phone') {
      return {
        href: `tel:${contact.handle}`,
        icon: 'phone',
        prefix: 'fas',
      };
    }
    return {
      href: contact.url,
      icon: contact.platform,
      prefix: 'fab',
    };
  }
}
