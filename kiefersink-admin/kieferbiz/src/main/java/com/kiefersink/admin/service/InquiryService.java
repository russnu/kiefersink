package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Inquiry;

import java.util.List;

public interface InquiryService {
    List<Inquiry> getAll();
    Inquiry get(Integer id);
    Inquiry create(Inquiry inquiry);
    Inquiry update(Integer id, Inquiry inquiry);
    void delete(Integer id);
}
