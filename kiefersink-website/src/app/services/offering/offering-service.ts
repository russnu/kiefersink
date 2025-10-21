import { Injectable } from '@angular/core';
import { Category } from '../category/category-service';

export interface Offering {
  id: number;
  name: string;
  description: string;
  priceRange: string;
  category: Category;
}

@Injectable({
  providedIn: 'root',
})
export class OfferingService {}
