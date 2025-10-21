package com.kiefersink.admin.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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
    @CreationTimestamp
    private String createdAt;
}
