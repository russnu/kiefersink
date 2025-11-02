import { Routes } from '@angular/router';
import { Portfolios } from './pages/portfolios/portfolios';
import { Artists } from './pages/artists/artists';
import { Offerings } from './pages/offerings/offerings';
import { Settings } from './pages/settings/settings';
import { Inquiries } from './pages/inquiries/inquiries';

export const routes: Routes = [
  {
    path: '',
    component: Inquiries,
    title: 'Admin | Inquiries',
  },
  {
    path: 'artists',
    component: Artists,
    title: 'Admin | Artists',
  },
  {
    path: 'services',
    component: Offerings,
    title: 'Admin | Services',
  },
  {
    path: 'portfolios',
    component: Portfolios,
    title: 'Admin | Portfolios',
  },
  {
    path: 'settings',
    component: Settings,
    title: 'Admin | Settings',
  },
];
