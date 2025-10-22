package com.kiefersink.admin.repository;

import com.kiefersink.admin.entity.CategoryData;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryData, Integer> {
}
