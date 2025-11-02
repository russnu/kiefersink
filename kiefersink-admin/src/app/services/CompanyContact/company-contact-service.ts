import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { CompanyContact } from '../../models/company-contact';

@Injectable({
  providedIn: 'root',
})
export class CompanyContactService {
  private apiUrl = `${environment.apiBaseUrl}/company_contact`;

  constructor(private http: HttpClient) {}

  getAllCompanyContacts(): Observable<CompanyContact[]> {
    return this.http.get<CompanyContact[]>(this.apiUrl);
  }

  createCompanyContact(companyContact: CompanyContact) {
    return this.http.post<CompanyContact>(this.apiUrl, companyContact);
  }

  updateCompanyContact(id: number, companyContact: CompanyContact) {
    return this.http.put<CompanyContact>(`${this.apiUrl}/${id}`, companyContact);
  }

  deleteCompanyContact(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
