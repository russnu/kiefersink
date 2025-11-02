package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.CategoryData;
import com.kiefersink.admin.entity.PortfolioData;
import com.kiefersink.admin.model.Category;
import com.kiefersink.admin.repository.CategoryRepository;
import com.kiefersink.admin.service.CategoryService;
import com.kiefersink.admin.transform.TransformCategory;
import com.kiefersink.admin.transform.TransformOffering;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;
    private final TransformCategory transformCategory = new TransformCategory();
    private final TransformOffering transformOffering = new TransformOffering();
    //========================================================================================================//
    @Transactional
    @Override
    public List<Category> getAll(boolean includeOfferings) {
        List<CategoryData> categoryDataList = new ArrayList<>();
        repository.findAll().forEach(categoryDataList::add);

        List<Category> categories = new ArrayList<>();
        for (CategoryData data : categoryDataList) {
            Category category = transformCategory.toModel(data);
            if (includeOfferings) {
                category.setOfferings(
                        data.getOfferings().stream()
                                .map(offeringData -> {
                                    var offering = transformOffering.toModel(offeringData);
                                    offering.setCategory(null);
                                    return offering;
                                })
                                .toList()
                );
            }
            categories.add(category);
        }
        return categories;
    }
    //========================================================================================================//
    @Transactional
    @Override
    public Category get(Integer id, boolean includeOfferings) {
        CategoryData categoryData = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Category category = transformCategory.toModel(categoryData);
        if (includeOfferings) {
            category.setOfferings(
                    categoryData.getOfferings().stream()
                            .map(transformOffering::toModel)
                            .toList()
            );
        }

        return category;
    }
    //========================================================================================================//
    @Transactional
    @Override
    public Category create(Category category) {

        CategoryData categoryData = transformCategory.toData(category);
        CategoryData saved = repository.save(categoryData);
        return transformCategory.toModel(saved);
    }
    //========================================================================================================//
    @Transactional
    @Override
    public Category update(Integer id, Category category) {

        CategoryData existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existing.setName(category.getName());
        CategoryData saved = repository.save(existing);
        return transformCategory.toModel(saved);

    }
    //========================================================================================================//
    @Transactional
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
