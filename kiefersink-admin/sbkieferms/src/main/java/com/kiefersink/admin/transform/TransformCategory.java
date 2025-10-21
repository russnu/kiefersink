package com.kiefersink.admin.transform;

import com.kiefersink.admin.entity.CategoryData;
import com.kiefersink.admin.model.Category;

public class TransformCategory implements Transform<Category, CategoryData>{
    @Override
    public Category toModel(CategoryData data) {
        Category category = new Category();
        category.setId(data.getId());
        category.setName(data.getName());

        return category;
    }

    @Override
    public CategoryData toData(Category model) {
        CategoryData categoryData = new CategoryData();
        categoryData.setId(model.getId());
        categoryData.setName(model.getName());

        return categoryData;
    }
}
