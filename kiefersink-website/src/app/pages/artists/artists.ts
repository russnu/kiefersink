import { Component, OnInit, inject } from '@angular/core';
import { Artist, ArtistService } from '../../services/artist/artist-service';
import { LucideAngularModule, FileIcon } from 'lucide-angular';
//==========================================================//
@Component({
  selector: 'app-artists',
  imports: [LucideAngularModule],
  templateUrl: './artists.html',
  styleUrl: './artists.css',
})
//==========================================================//
export class Artists implements OnInit {
  readonly FileIcon = FileIcon;
  artists: Artist[] = [];

  private artistService = inject(ArtistService);

  ngOnInit() {
    this.artistService.getAllWithContacts().subscribe((data) => {
      this.artists = data;
    });
  }
}
