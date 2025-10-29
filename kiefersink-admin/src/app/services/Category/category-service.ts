import { Injectable } from '@angular/core';
import { Category } from '../../models/category';
import { Offering } from '../../models/offering';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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
