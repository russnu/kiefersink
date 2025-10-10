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

    // Relationships ======================================= //
    @ManyToOne
    @JoinColumn(name = "artistId", nullable = false)
    private ArtistData artist;

}
