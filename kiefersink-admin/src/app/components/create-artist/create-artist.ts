import { Component, OnInit, inject } from '@angular/core';
import { Artist } from '../../models/artist';
import { ArtistContact } from '../../models/artist-contact';
import { ArtistService } from '../../services/Artist/artist-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Router } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'create-artist-form',
  imports: [FontAwesomeModule, FormsModule],
  templateUrl: './create-artist.html',
  styleUrl: './create-artist.css',
})
export class CreateArtist {
  artist: Artist = {
    name: '',
    imageUrl: '',
    contacts: [{ platform: '', handle: '', url: '' } as ArtistContact],
  };
  // ====================================================== //
  selectedFile: File | null = null;
  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }
  // ====================================================== //
  private artistService = inject(ArtistService);
  // ====================================================== //
  addContact() {
    this.artist.contacts ??= [];
    this.artist.contacts.push({ platform: '', handle: '', url: '' });
  }
  // ====================================================== //
  onCreate(form: NgForm) {
    const formData = new FormData();

    formData.append(
      'artist',
      new Blob([JSON.stringify(this.artist)], { type: 'application/json' }),
    );
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    this.artistService.createArtist(formData).subscribe({
      next: (response) => {
        console.log('Artist created:', response);
        form.resetForm();
      },
      error: (err) => console.error('Error creating artist:', err),
    });
  }
  // ====================================================== //

  closeModal(form: NgForm) {
    const dialog = document.getElementById('create_form') as HTMLDialogElement;
    form.resetForm();
    dialog.close();
  }
}
