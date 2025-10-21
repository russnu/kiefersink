import { Injectable } from '@angular/core';
import { Offering } from '../offering/offering-service';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
//==========================================================//
export interface Category {
  id: number;
  name: string;
  offerings: Offering[];
}
//==========================================================//
@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private apiUrl = `${environment.apiBaseUrl}/category`;

  constructor(private http: HttpClient) {}

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.apiUrl);
  }

  getAllCategoriesWithOfferings(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.apiUrl}?includeOfferings=true`);
  }
}
