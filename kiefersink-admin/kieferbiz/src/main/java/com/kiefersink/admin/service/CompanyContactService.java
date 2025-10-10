package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Artist;
import com.kiefersink.admin.model.CompanyContact;

import java.util.List;

public interface CompanyContactService {
    List<CompanyContact> getAll();
    CompanyContact get(String platform);
    CompanyContact create(CompanyContact companyContact);
    CompanyContact update(String platform, CompanyContact companyContact);
    void delete(String platform);
}
