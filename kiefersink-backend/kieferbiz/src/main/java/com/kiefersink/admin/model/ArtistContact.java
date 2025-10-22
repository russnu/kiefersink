package com.kiefersink.admin.model;

import lombok.Data;

@Data
public class ArtistContact {
    private Integer id;
    private Integer artistId;
    private String platform;
    private String handle;
    private String url;
}
