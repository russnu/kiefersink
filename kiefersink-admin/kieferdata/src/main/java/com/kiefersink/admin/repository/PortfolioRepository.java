package com.kiefersink.admin.repository;

import com.kiefersink.admin.entity.PortfolioData;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends CrudRepository<PortfolioData, Integer> {
}
