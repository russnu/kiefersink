import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
//==========================================================//
export interface ArtistContact {
  id: number;
  artist_id: number;
  platform: string;
  handle: string;
  url: string;
}
//==========================================================//
@Injectable({
  providedIn: 'root',
})
export class ArtistContactService {
  private apiUrl = `${environment.apiBaseUrl}/artist_contact`;

  constructor(private http: HttpClient) {}

  getArtistContacts(artistId: number): Observable<ArtistContact[]> {
    return this.http.get<ArtistContact[]>(`${this.apiUrl}/${artistId}`);
  }

  displayContactLink(contact: ArtistContact) {
    if (contact.platform === 'email') {
      return {
        href: `mailto:${contact.handle}`,
        icon: 'envelope',
        prefix: 'fas',
      };
    }
    if (contact.platform === 'phone') {
      return {
        href: `tel:${contact.handle}`,
        icon: 'phone',
        prefix: 'fas',
      };
    }
    return {
      href: contact.url,
      icon: contact.platform,
      prefix: 'fab',
    };
  }
}
