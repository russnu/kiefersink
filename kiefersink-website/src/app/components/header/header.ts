import { Component, HostListener, inject, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { NgClass } from '@angular/common';
import { SettingsService } from '../../services/settings/settings-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
@Component({
  selector: 'app-header',
  imports: [RouterLink, RouterLinkActive, NgClass, FontAwesomeModule],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header implements OnInit {
  private settingsService = inject(SettingsService);
  logoUrl = '';
  isMenuOpen = false;

  ngOnInit() {
    this.settingsService.getSettings('logoUrl').subscribe((data) => {
      this.logoUrl = data.settingValue;
    });
  }

  isScrolled = false;
  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.isScrolled = window.scrollY > 50;
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }

  closeMenu() {
    this.isMenuOpen = false;
  }
}
