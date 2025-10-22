package com.kiefersink.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CompanyContacts")
public class CompanyContactData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String platform;
    private String handle;
    private String url;
}
