import { Component, inject, OnInit } from '@angular/core';

import { Portfolio } from '../../models/portfolio';

import { PortfolioService } from '../../services/Portfolio/portfolio-service';

import { CreatePortfolio } from '../../components/create-portfolio/create-portfolio';

import { EditPortfolio } from '../../components/edit-portfolio/edit-portfolio';

@Component({
  selector: 'app-portfolios',
  imports: [EditPortfolio, CreatePortfolio],
  templateUrl: './portfolios.html',
  styleUrl: './portfolios.css',
})
export class Portfolios implements OnInit {
  portfolios: Portfolio[] = [];
  selectedPortfolio: Portfolio | null = null;
  private portfolioService = inject(PortfolioService);
  // ====================================================== //
  ngOnInit() {
    this.portfolioService.getPortfolios().subscribe((data) => {
      this.portfolios = data;
    });
  }
  // ====================================================== //
  openEditModal(portfolio: Portfolio) {
    this.selectedPortfolio = JSON.parse(JSON.stringify(portfolio));
    const dialog = document.getElementById('edit_portfolio_form') as HTMLDialogElement;
    dialog.showModal();
  }
  // ====================================================== //
  onToggle(portfolio: Portfolio) {
    const newFeaturedState = !portfolio.featured;
    this.portfolioService.updateFeatured(portfolio.id!, newFeaturedState).subscribe({
      next: (updated) => {
        portfolio.featured = updated.featured;
        // console.log(`Portfolio ${updated.id} featured:`, updated.featured);
      },
      error: (err) => console.error('Error updating featured:', err),
    });
  }
  // ====================================================== //
  openDeleteModal(portfolio: Portfolio) {
    this.selectedPortfolio = portfolio;
    const dialog = document.getElementById('delete_portfolio_confirm') as HTMLDialogElement;
    dialog.showModal();
  }
  // ====================================================== //
  deletePortfolio() {
    if (!this.selectedPortfolio) return;
    this.portfolioService.deletePortfolio(this.selectedPortfolio.id!).subscribe({
      next: () => {
        const dialog = document.getElementById('delete_portfolio_confirm') as HTMLDialogElement;
        dialog?.close();
        window.location.reload();
      },
      error: (err) => console.error('Error deleting artist:', err),
    });
  }
}
