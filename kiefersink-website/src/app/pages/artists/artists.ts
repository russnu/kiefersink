import { Component, OnInit, inject } from '@angular/core';
import { Artist, ArtistService } from '../../services/artist/artist-service';
import {
  ArtistContactService,
  ArtistContact,
} from '../../services/artist_contact/artist-contact-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Router } from '@angular/router';
//==========================================================//
@Component({
  selector: 'app-artists',
  imports: [FontAwesomeModule],
  templateUrl: './artists.html',
  styleUrl: './artists.css',
})
//==========================================================//
export class Artists implements OnInit {
  artists: Artist[] = [];

  private artistService = inject(ArtistService);
  private artistContactService = inject(ArtistContactService);
  private router = inject(Router);

  ngOnInit() {
    this.artistService.getAllWithContacts().subscribe((data) => {
      this.artists = data;
    });
  }

  displayLinks(contact: ArtistContact) {
    return this.artistContactService.displayContactLink(contact);
  }

  viewGalleryByArtist(artistId: number) {
    this.router.navigate(['/gallery'], {
      queryParams: { artist: artistId },
    });
  }
}
