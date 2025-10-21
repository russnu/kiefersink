package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Category;

import java.util.List;

public interface CategoryService{
    List<Category> getAll(boolean includeOfferings);
    Category get(Integer id, boolean includeOfferings);
    Category create(Category category);
    Category update(Integer id, Category category);
    void delete(Integer id);
}
