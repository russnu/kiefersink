import { Component, OnInit, inject } from '@angular/core';
import { Artist } from '../../models/artist';
import { ArtistService } from '../../services/Artist/artist-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CreateArtist } from '../../components/create-artist/create-artist';
import { EditArtist } from '../../components/edit-artist/edit-artist';

@Component({
  selector: 'app-artists',
  imports: [FontAwesomeModule, FormsModule, CreateArtist, EditArtist],
  templateUrl: './artists.html',
  styleUrl: './artists.css',
})
export class Artists implements OnInit {
  artists: Artist[] = [];
  selectedArtist: Artist | null = null;

  private router = inject(Router);
  private artistService = inject(ArtistService);
  // ====================================================== //
  ngOnInit() {
    this.artistService.getAllArtistsWithContacts().subscribe((data) => {
      this.artists = data;
    });
  }
  // ====================================================== //
  openEditModal(artist: Artist) {
    this.selectedArtist = JSON.parse(JSON.stringify(artist));
    const dialog = document.getElementById('edit_form') as HTMLDialogElement;
    dialog.showModal();
  }
  // ====================================================== //
  openDeleteModal(artist: Artist) {
    this.selectedArtist = artist;
    const dialog = document.getElementById('delete_confirm') as HTMLDialogElement;
    dialog.showModal();
  }
  // ====================================================== //
  deleteArtist() {
    if (!this.selectedArtist) return;
    this.artistService.deleteArtist(this.selectedArtist.id!).subscribe({
      next: () => {
        const dialog = document.getElementById('delete_confirm') as HTMLDialogElement;
        dialog?.close();
        window.location.reload();
      },
      error: (err) => console.error('Error deleting artist:', err),
    });
  }
  // ====================================================== //
  toTitleCase(s: string) {
    const result = s.replace(/([A-Z])/g, ' $1');
    return result.charAt(0).toUpperCase() + result.slice(1);
  }
}
