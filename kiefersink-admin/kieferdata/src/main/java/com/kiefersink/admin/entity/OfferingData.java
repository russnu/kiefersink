package com.kiefersink.admin.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Offerings")
public class OfferingData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String priceRange;

    // Relationships ======================================= //
    @ManyToOne
    @JoinColumn(name = "offeringTypeId", nullable = false)
    private OfferingTypeData offeringTypeData;
}
