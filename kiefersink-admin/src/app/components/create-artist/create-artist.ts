import { Component, inject } from '@angular/core';
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

  platforms: string[] = [];
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
  private router = inject(Router);
  // ====================================================== //
  // ngOnInit() {
  //   this.artistService.getAllArtistsWithContacts().subscribe((data) => {
  //     this.artists = data;
  //   });
  // }
  // ====================================================== //
  addContact() {
    this.artist.contacts ??= [];
    this.artist.contacts.push({ platform: '', handle: '', url: '' });
  }
  // ====================================================== //
  onCreate(form: NgForm) {
    if (this.artist.contacts) {
      this.artist.contacts = this.artist.contacts.map((c) => ({
        ...c,
        platform: c.platform?.toLowerCase().trim() || '',
      }));
    }

    const formData = new FormData();

    formData.append(
      'artist',
      new Blob([JSON.stringify(this.artist)], { type: 'application/json' }),
    );
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    // const artistBlob = formData.get('artist') as Blob;
    // if (artistBlob) {
    //   artistBlob.text().then((jsonText) => {
    //     console.log('Artist JSON:', JSON.parse(jsonText));
    //   });
    // }

    this.artistService.createArtist(formData).subscribe({
      next: (response) => {
        console.log('Artist created:', response);
        this.closeModal(form);
        window.location.reload();
      },
      error: (err) => console.error('Error creating artist:', err),
    });
  }
  // ====================================================== //
  removeContact(index: number) {
    if (!this.artist?.contacts) return;
    if (confirm('Remove this contact?')) {
      this.artist.contacts.splice(index, 1);
    }
  }
  // ====================================================== //
  closeModal(form: NgForm) {
    const dialog = document.getElementById('create_form') as HTMLDialogElement;
    form.resetForm();
    dialog.close();
  }
}
