import { Component, inject, Input } from '@angular/core';
import { Artist } from '../../models/artist';
import { ArtistService } from '../../services/Artist/artist-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'edit-artist-form',
  imports: [FontAwesomeModule, FormsModule],
  templateUrl: './edit-artist.html',
  styleUrl: './edit-artist.css',
})
export class EditArtist {
  @Input() artist: Artist | null = null;
  // ====================================================== //
  selectedFile: File | null = null;
  previewUrl: string | ArrayBuffer | null = null;
  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];

      const reader = new FileReader();
      reader.onload = () => {
        this.previewUrl = reader.result;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }
  // ====================================================== //
  private artistService = inject(ArtistService);
  // ====================================================== //
  addContact() {
    if (!this.artist) return;
    this.artist.contacts ??= [];
    this.artist.contacts.push({ platform: '', handle: '', url: '' });
  }
  // ====================================================== //
  onUpdate(form: NgForm) {
    if (form.invalid) {
      form.control.markAllAsTouched();
      return;
    }
    if (!this.artist || this.artist.id == null) {
      console.error('Artist or artist ID missing.');
      return;
    }
    const formData = new FormData();
    formData.append(
      'artist',
      new Blob([JSON.stringify(this.artist)], { type: 'application/json' }),
    );
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    this.artistService.updateArtist(this.artist.id, formData).subscribe({
      next: (response) => {
        console.log('Artist updated successfully!:', response);
        this.closeModal(form);
        window.location.reload();
      },
      error: (err) => console.error('Error updating artist:', err),
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
    const dialog = document.getElementById('edit_artist_form') as HTMLDialogElement;
    form.resetForm();
    this.previewUrl = null;
    this.selectedFile = null;
    const fileInput = document.querySelector('input[type="file"]') as HTMLInputElement;
    if (fileInput) {
      fileInput.value = '';
    }
    dialog.close();
  }
}
