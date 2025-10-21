import { Component, signal, HostListener } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './components/header/header';
import { Footer } from './components/footer/footer';
import { FontAwesomeModule, FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { fab } from '@fortawesome/free-brands-svg-icons';
import {
  faMessage,
  faLocationDot,
  faEnvelope,
  faPhone,
  faClock,
  faCaretLeft,
  faCircleXmark,
  faBars,
  faPaperPlane,
} from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Header, Footer, FontAwesomeModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('kiefersink-website');
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fab);
    library.addIcons(
      faMessage,
      faLocationDot,
      faEnvelope,
      faPhone,
      faClock,
      faCaretLeft,
      faCircleXmark,
      faBars,
      faPaperPlane,
    );
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    const target = event.target as HTMLElement;
    const dropdowns = document.querySelectorAll('details.dropdown');

    dropdowns.forEach((dropdown) => {
      if (!dropdown.contains(target)) {
        (dropdown as HTMLDetailsElement).open = false;
      }
    });
  }
}
