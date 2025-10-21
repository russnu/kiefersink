package com.kiefersink.admin.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Offering {
    private Integer id;
    private String name;
    private String description;
    private String priceRange;
    private Category category;
}
