package com.kiefersink.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "artistcontacts")
public class ArtistContactData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String platform;
    private String handle;
    private String url;

    // Relationships ======================================= //
    @ManyToOne
    @JoinColumn(name = "artistId", nullable = false)
    private ArtistData artist;

}
