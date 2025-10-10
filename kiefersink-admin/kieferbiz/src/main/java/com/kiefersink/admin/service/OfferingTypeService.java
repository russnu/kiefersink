package com.kiefersink.admin.service;

import com.kiefersink.admin.model.OfferingType;

import java.util.List;

public interface OfferingTypeService {
    List<OfferingType> getAll();
    OfferingType get(Integer id);
    OfferingType create(OfferingType offeringType);
    OfferingType update(Integer id, OfferingType offeringType);
    void delete(Integer id);
}
