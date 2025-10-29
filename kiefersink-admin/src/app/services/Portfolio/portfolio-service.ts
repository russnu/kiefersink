import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Portfolio } from '../../models/portfolio';
import { Offering } from '../../models/offering';
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
}
