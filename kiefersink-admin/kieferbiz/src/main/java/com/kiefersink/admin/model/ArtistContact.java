package com.kiefersink.admin.model;

import lombok.Data;

@Data
public class ArtistContact {
    private Integer id;
    private Artist artist;
    private String platform;
    private String value;
}
