import { Injectable, inject } from '@angular/core';
import { Artist } from '../artist/artist-service';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Offering, OfferingService } from '../offering/offering-service';
//==========================================================//
export interface Portfolio {
  id: number;
  artist: Artist;
  title: string;
  description: string;
  imageUrl: string;
  offering: Offering;
}
//==========================================================//
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
