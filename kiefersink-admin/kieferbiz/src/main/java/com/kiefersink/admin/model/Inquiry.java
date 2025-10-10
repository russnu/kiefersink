package com.kiefersink.admin.model;

import lombok.Data;

@Data
public class Inquiry {
    private Integer id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Offering offering;
    private String subject;
    private String message;
    private String createdAt;
}
