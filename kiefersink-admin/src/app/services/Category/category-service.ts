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

  getCategoryWithOfferings(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.apiUrl}/${id}?includeOfferings=true`);
  }

  createCategory(category: Category) {
    return this.http.post(this.apiUrl, category);
  }

  updateCategory(id: number, category: Category) {
    return this.http.patch<Category>(`${this.apiUrl}/${id}`, category);
  }

  deleteCategory(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
