import { Component, signal, inject } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { FontAwesomeModule, FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faTrash, faPen, faPlus, faBars } from '@fortawesome/free-solid-svg-icons';
import { SettingsService } from './services/Settings/settings-service';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, RouterLinkActive, FontAwesomeModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('kiefersink-admin');
  constructor(library: FaIconLibrary) {
    library.addIcons(faTrash, faPen, faPlus, faBars);
  }

  logo: string = '';

  settingsService = inject(SettingsService);

  ngOnInit() {
    this.settingsService.getSettings('logoUrl').subscribe((data) => {
      this.logo = data.settingValue;
    });
  }
}
