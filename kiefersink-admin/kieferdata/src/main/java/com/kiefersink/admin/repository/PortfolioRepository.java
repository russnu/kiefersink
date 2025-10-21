package com.kiefersink.admin.repository;

import com.kiefersink.admin.entity.PortfolioData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PortfolioRepository extends CrudRepository<PortfolioData, Integer> {
    @Override
    List<PortfolioData> findAll();

    List<PortfolioData> findByFeaturedTrue();
}
