package com.kiefersink.admin.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {
    private Integer id;
    private String name;
    private List<Offering> offerings;

}
