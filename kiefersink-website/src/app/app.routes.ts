import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { About } from './pages/about/about';
import { Gallery } from './pages/gallery/gallery';
import { Contact } from './pages/contact/contact';
import { Artists } from './pages/artists/artists';
import { Offerings } from './pages/offerings/offerings';

export const routes: Routes = [
  {
    path: '',
    component: Home,
    title: 'Home',
  },
  {
    path: 'about',
    component: About,
    title: 'About',
  },
  {
    path: 'artists',
    component: Artists,
    title: 'Artists',
  },
  {
    path: 'gallery',
    component: Gallery,
    title: 'Gallery',
  },
  {
    path: 'contact',
    component: Contact,
    title: 'Contact',
  },

  {
    path: 'services',
    component: Offerings,
    title: 'Services',
  },
];
