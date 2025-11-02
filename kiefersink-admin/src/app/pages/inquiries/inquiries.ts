import { Component, inject, OnInit } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Inquiry } from '../../models/inquiry';
import { InquiryService } from '../../services/Inquiry/inquiry-service';
// ====================================================== //
@Component({
  selector: 'app-inquiries',
  imports: [FontAwesomeModule],
  templateUrl: './inquiries.html',
  styleUrl: './inquiries.css',
})
export class Inquiries implements OnInit {
  inquiries: Inquiry[] = [];
  // ====================================================== //
  selectedInquiry: Inquiry | null = null;
  // ====================================================== //
  private service = inject(InquiryService);
  // ====================================================== //
  ngOnInit() {
    this.service.getAllInquiries().subscribe((categories) => {
      this.inquiries = categories;
    });
  }
  // ====================================================== //
  openDeleteInquiryModal(inquiry: Inquiry) {
    this.selectedInquiry = inquiry;
    const dialog = document.getElementById('delete_inquiry_confirm') as HTMLDialogElement;
    dialog.showModal();
  }
  // ====================================================== //
  deleteInquiry() {
    if (!this.selectedInquiry) return;
    this.service.deleteInquiry(this.selectedInquiry.id!).subscribe({
      next: () => {
        const dialog = document.getElementById('delete_inquiry_confirm') as HTMLDialogElement;
        dialog?.close();
        window.location.reload();
      },
      error: (err) => console.error('Error deleting inquiry:', err),
    });
  }
}
