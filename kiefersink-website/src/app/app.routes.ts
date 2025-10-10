import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { About } from './pages/about/about';
import { Gallery } from './pages/gallery/gallery';
import { Contact } from './pages/contact/contact';

export const routes: Routes = [
  {
    path: '',
    component: Home,
    title: 'Home Page',
  },
  {
    path: 'about',
    component: About,
    title: 'About Page',
  },
  {
    path: 'gallery',
    component: Gallery,
    title: 'Gallery Page',
  },
  {
    path: 'contact',
    component: Contact,
    title: 'Contact Page',
  },
];
