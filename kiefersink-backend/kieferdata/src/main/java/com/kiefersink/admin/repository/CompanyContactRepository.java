package com.kiefersink.admin.repository;

import com.kiefersink.admin.entity.CompanyContactData;
import org.springframework.data.repository.CrudRepository;

public interface CompanyContactRepository extends CrudRepository<CompanyContactData, Integer> {
}
