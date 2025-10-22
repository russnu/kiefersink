package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Offering;

import java.util.List;

public interface OfferingService {
    List<Offering> getAll();
    Offering get(Integer id);
    Offering create(Offering offering);
    Offering update(Integer id, Offering offering);
    void delete(Integer id);
}
