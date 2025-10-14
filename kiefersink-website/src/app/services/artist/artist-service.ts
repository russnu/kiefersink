import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, map, switchMap } from 'rxjs';
//==========================================================//
export interface Artist {
  id: number;
  name: string;
  imageUrl: string;
  contacts?: ArtistContact[];
}

export interface ArtistContact {
  id: number;
  artistId: number;
  platform: string;
  handle: string;
  url: string;
}
//==========================================================//
@Injectable({
  providedIn: 'root',
})
export class ArtistService {
  private artistsUrl = 'http://localhost:8080/api/artist';
  private contactsUrl = 'http://localhost:8080/api/artist_contact';

  constructor(private http: HttpClient) {}

  getAllWithContacts(): Observable<Artist[]> {
    return this.http.get<Artist[]>(this.artistsUrl).pipe(
      switchMap((artists) => {
        const contactRequests = artists.map((artist) =>
          this.http.get<ArtistContact[]>(`${this.contactsUrl}/${artist.id}`).pipe(
            map((contacts) => ({
              ...artist,
              contacts,
            }))
          )
        );
        return forkJoin(contactRequests);
      })
    );
  }
}
