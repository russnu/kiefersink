import {
  ApplicationConfig,
  provideBrowserGlobalErrorListeners,
  provideZoneChangeDetection,
} from '@angular/core';

import { LucideIconProvider, LUCIDE_ICONS } from 'lucide-angular';

import { provideHttpClient } from '@angular/common/http';

import { provideRouter } from '@angular/router';

import { routes } from './app.routes';

import {
  Home,
  User,
  Mail,
  Phone,
  Instagram,
  Clock,
  MapPin,
  MessageCircle,
  Facebook,
} from 'lucide-angular';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(),
    {
      provide: LUCIDE_ICONS,
      multi: true,
      useValue: new LucideIconProvider({
        Home,
        User,
        Mail,
        Phone,
        Instagram,
        Clock,
        MapPin,
        MessageCircle,
        Facebook,
      }),
    },
  ],
};
