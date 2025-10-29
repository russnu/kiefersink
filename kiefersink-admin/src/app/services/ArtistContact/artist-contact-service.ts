import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ArtistContact } from '../../models/artist-contact';

@Injectable({
  providedIn: 'root',
})
export class ArtistContactService {
  private apiUrl = `${environment.apiBaseUrl}/artist_contact`;
  constructor(private http: HttpClient) {}

  getArtistContacts(artistId: number): Observable<ArtistContact[]> {
    return this.http.get<ArtistContact[]>(`${this.apiUrl}/${artistId}`);
  }
}
