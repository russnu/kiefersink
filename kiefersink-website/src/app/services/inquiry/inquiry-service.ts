import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
//==========================================================//
export interface Inquiry {
  customerName: string;
  customerEmail: string;
  customerPhone: string;
  subject: string;
  message: string;
}
//==========================================================//
@Injectable({
  providedIn: 'root',
})
export class InquiryService {
  private apiUrl = `${environment.apiBaseUrl}/inquiry`;

  constructor(private http: HttpClient) {}

  sendInquiry(inquiry: Inquiry): Observable<Inquiry> {
    return this.http.post<Inquiry>(this.apiUrl, inquiry);
  }
}
