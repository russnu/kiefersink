import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Portfolio } from '../../models/portfolio';
import { OfferingService } from '../Offering/offering-service';

OfferingService;
@Injectable({
  providedIn: 'root',
})
export class PortfolioService {
  private apiUrl = `${environment.apiBaseUrl}/portfolio`;
  protected offeringService = inject(OfferingService);

  constructor(private http: HttpClient) {}

  getPortfolios(): Observable<Portfolio[]> {
    return this.http.get<Portfolio[]>(this.apiUrl);
  }

  getFeaturedPortfolios(): Observable<Portfolio[]> {
    return this.http.get<Portfolio[]>(`${this.apiUrl}?featured=true`);
  }

  createPortfolio(formData: FormData) {
    return this.http.post(this.apiUrl, formData);
  }

  updatePortfolio(id: number, formData: FormData) {
    return this.http.put<Portfolio>(`${this.apiUrl}/${id}`, formData);
  }

  updateFeatured(id: number, featured: boolean) {
    return this.http.patch<Portfolio>(`${this.apiUrl}/${id}/featured?featured=${featured}`, {});
  }

  deletePortfolio(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
