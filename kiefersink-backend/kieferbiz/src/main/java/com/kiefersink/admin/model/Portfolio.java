package com.kiefersink.admin.model;

import lombok.Data;

@Data
public class Portfolio {
    private Integer id;
    private Artist artist;
    private String title;
    private String description;
    private String imageUrl;
    private boolean featured;
    private Offering offering;

}
