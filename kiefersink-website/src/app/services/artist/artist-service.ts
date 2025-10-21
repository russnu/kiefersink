import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, map, switchMap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ArtistContactService, ArtistContact } from '../artist_contact/artist-contact-service';
//==========================================================//
export interface Artist {
  id: number;
  name: string;
  imageUrl: string;
  contacts?: ArtistContact[];
}
//==========================================================//
@Injectable({
  providedIn: 'root',
})
export class ArtistService {
  private apiUrl = `${environment.apiBaseUrl}/artist`;
  protected artistContactService = inject(ArtistContactService);

  constructor(private http: HttpClient) {}

  getAllArtists(): Observable<Artist[]> {
    return this.http.get<Artist[]>(this.apiUrl);
  }

  getAllWithContacts(): Observable<Artist[]> {
    return this.http.get<Artist[]>(this.apiUrl).pipe(
      switchMap((artists) => {
        const contactRequests = artists.map((artist) =>
          this.artistContactService.getArtistContacts(artist.id).pipe(
            map((contacts) => ({
              ...artist,
              contacts,
            })),
          ),
        );
        return forkJoin(contactRequests);
      }),
    );
  }
}
