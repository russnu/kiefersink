package com.kiefersink.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "offerings")
public class OfferingData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String priceRange;
    private String imageUrl;

    // Relationships ======================================= //
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private CategoryData category;

}
