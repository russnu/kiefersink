import { Component, inject, OnInit } from '@angular/core';

import { Portfolio } from '../../models/portfolio';
import { Category } from '../../models/category';
import { Artist } from '../../models/artist';

import { CategoryService } from '../../services/Category/category-service';
import { PortfolioService } from '../../services/Portfolio/portfolio-service';
import { ArtistService } from '../../services/Artist/artist-service';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-portfolios',
  imports: [],
  templateUrl: './portfolios.html',
  styleUrl: './portfolios.css',
})
export class Portfolios implements OnInit {
  portfolios: Portfolio[] = [];

  private portfolioService = inject(PortfolioService);

  ngOnInit() {
    this.portfolioService.getPortfolios().subscribe((data) => {
      this.portfolios = data;
    });
  }
}
