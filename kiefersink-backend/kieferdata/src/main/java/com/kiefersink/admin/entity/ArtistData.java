package com.kiefersink.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "artists")
public class ArtistData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String imageUrl;

    // Relationships ======================================= //
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "artist", cascade = CascadeType.ALL)
    private List<ArtistContactData> contacts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "artist", cascade = CascadeType.ALL)
    private List<PortfolioData> artistPortfolio;
}
