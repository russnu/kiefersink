import { Offering } from './offering';

export interface Category {
  id: number;
  name: string;
  offerings: Offering[];
}
