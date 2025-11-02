import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Inquiry } from '../../models/inquiry';

@Injectable({
  providedIn: 'root',
})
export class InquiryService {
  private apiUrl = `${environment.apiBaseUrl}/inquiry`;

  constructor(private http: HttpClient) {}

  getAllInquiries(): Observable<Inquiry[]> {
    return this.http.get<Inquiry[]>(this.apiUrl);
  }

  deleteInquiry(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
