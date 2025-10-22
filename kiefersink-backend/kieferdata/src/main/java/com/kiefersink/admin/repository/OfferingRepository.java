package com.kiefersink.admin.repository;

import com.kiefersink.admin.entity.OfferingData;
import org.springframework.data.repository.CrudRepository;

public interface OfferingRepository extends CrudRepository<OfferingData, Integer> {
}
