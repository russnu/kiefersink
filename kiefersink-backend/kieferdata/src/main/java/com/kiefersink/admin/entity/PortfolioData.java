package com.kiefersink.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Portfolio")
public class PortfolioData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String imageUrl;
    private boolean featured;

    // Relationships ======================================= //
    @ManyToOne
    @JoinColumn(name = "artistId", nullable = false)
    private ArtistData artist;

    @ManyToOne
    @JoinColumn(name = "offeringId", nullable = false)
    private OfferingData offering;


}
