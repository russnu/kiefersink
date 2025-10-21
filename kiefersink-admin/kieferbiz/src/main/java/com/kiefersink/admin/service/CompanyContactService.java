package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Artist;
import com.kiefersink.admin.model.CompanyContact;

import java.util.List;

public interface CompanyContactService {
    List<CompanyContact> getAll();
    CompanyContact get(Integer id);
    CompanyContact create(CompanyContact companyContact);
    CompanyContact update(Integer id, CompanyContact companyContact);
    void delete(Integer id);
}
