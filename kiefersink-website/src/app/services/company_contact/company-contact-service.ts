import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
//==========================================================//
export interface CompanyContact {
  platform: string;
  handle: string;
  url: string;
}
//==========================================================//
@Injectable({
  providedIn: 'root',
})
export class CompanyContactService {
  private apiUrl = 'http://localhost:8080/api/company_contact';

  constructor(private http: HttpClient) {}

  getCompanyContacts(): Observable<CompanyContact[]> {
    return this.http.get<CompanyContact[]>(this.apiUrl);
  }
}
