import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Offering } from '../../models/offering';
@Injectable({
  providedIn: 'root',
})
export class OfferingService {
  private apiUrl = `${environment.apiBaseUrl}/offering`;

  constructor(private http: HttpClient) {}

  getAllOfferings(): Observable<Offering[]> {
    return this.http.get<Offering[]>(this.apiUrl);
  }

  createOffering(formData: FormData) {
    return this.http.post(this.apiUrl, formData);
  }

  updateOffering(id: number, formData: FormData) {
    return this.http.put<Offering>(`${this.apiUrl}/${id}`, formData);
  }

  deleteOffering(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
