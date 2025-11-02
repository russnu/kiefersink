import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { FontAwesomeModule, FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faTrash, faPen, faPlus } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, RouterLinkActive, FontAwesomeModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('kiefersink-admin');
  constructor(library: FaIconLibrary) {
    library.addIcons(faTrash, faPen, faPlus);
  }
}
