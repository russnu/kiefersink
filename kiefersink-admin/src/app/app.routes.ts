import { Routes } from '@angular/router';
import { Portfolios } from './pages/portfolios/portfolios';
import { Artists } from './pages/artists/artists';
import { Offerings } from './pages/offerings/offerings';
import { Settings } from './pages/settings/settings';

export const routes: Routes = [
  {
    path: '',
    component: Artists,
    title: 'Artists',
  },
  {
    path: 'services',
    component: Offerings,
    title: 'Services',
  },
  {
    path: 'portfolios',
    component: Portfolios,
    title: 'Portfolios',
  },
  {
    path: 'settings',
    component: Settings,
    title: 'Settings',
  },
];
