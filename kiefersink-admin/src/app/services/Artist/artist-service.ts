import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Artist } from '../../models/artist';

@Injectable({
  providedIn: 'root',
})
export class ArtistService {
  private apiUrl = `${environment.apiBaseUrl}/artist`;

  constructor(private http: HttpClient) {}

  getAllArtists(): Observable<Artist[]> {
    return this.http.get<Artist[]>(this.apiUrl);
  }

  getAllArtistsWithContacts(): Observable<Artist[]> {
    return this.http.get<Artist[]>(`${this.apiUrl}?includeContacts=true`);
  }

  createArtist(formData: FormData) {
    return this.http.post(this.apiUrl, formData);
  }

  updateArtist(id: number, formData: FormData) {
    return this.http.put(`${this.apiUrl}/${id}`, formData);
  }

  deleteArtist(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
