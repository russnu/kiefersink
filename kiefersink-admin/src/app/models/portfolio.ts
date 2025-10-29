import { Artist } from './artist';
import { Offering } from './offering';

export interface Portfolio {
  id: number;
  artist: Artist;
  title: string;
  description: string;
  imageUrl: string;
  offering: Offering;
}
