import { Component, inject, OnInit } from '@angular/core';
import { Portfolio, PortfolioService } from '../../services/portfolio/portfolio-service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit {
  portfolios: Portfolio[] = [];

  private portfolioService = inject(PortfolioService);

  ngOnInit() {
    this.portfolioService.getFeaturedPortfolios().subscribe((data) => {
      this.portfolios = data;
    });
  }
}
