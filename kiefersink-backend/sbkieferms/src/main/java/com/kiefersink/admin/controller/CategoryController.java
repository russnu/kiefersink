package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.Category;
import com.kiefersink.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/category")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;
    //========================================================================================================//
    @GetMapping
    public List<Category> getAllCategories(@RequestParam(name = "includeOfferings", defaultValue = "false") boolean includeOfferings) {
        return service.getAll(includeOfferings);
    }
    //========================================================================================================//
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable("id") Integer id,  @RequestParam(name = "includeOfferings", defaultValue = "false") boolean includeOfferings) {
        return service.get(id, includeOfferings);
    }
    //========================================================================================================//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return service.create(category);
    }
    //========================================================================================================//
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
        return service.update(id, category);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Integer id) {
        service.delete(id);
    }
    //========================================================================================================//
}
