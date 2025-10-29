import { ArtistContact } from './artist-contact';

export interface Artist {
  id?: number;
  name: string;
  imageUrl?: string;
  contacts?: ArtistContact[];
}
