package com.kiefersink.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Inquiries")
public class InquiryData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String subject;
    private String message;
    private String createdAt;

    // Relationships ======================================= //
    @ManyToOne
    @JoinColumn(name = "offeringId", nullable = false)
    private OfferingData offeringData;
}
