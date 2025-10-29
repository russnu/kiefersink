import { Category } from './category';

export interface Offering {
  id: number;
  name: string;
  description: string;
  priceRange: string;
  imageUrl: string;
  category: Category;
}
