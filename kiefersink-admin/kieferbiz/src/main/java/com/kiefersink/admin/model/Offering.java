package com.kiefersink.admin.model;

import lombok.Data;

@Data
public class Offering {
    private Integer id;
    private String name;
    private String description;
    private OfferingType offeringType;
    private String priceRange;
}
