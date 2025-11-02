package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Offering;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OfferingService {
    List<Offering> getAll();
    Offering get(Integer id);
    Offering create(Offering offering, MultipartFile image);
    Offering update(Integer id, Offering offering, MultipartFile image);
    void delete(Integer id);
}
