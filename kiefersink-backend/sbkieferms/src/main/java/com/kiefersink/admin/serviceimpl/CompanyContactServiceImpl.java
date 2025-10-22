package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.CompanyContactData;
import com.kiefersink.admin.model.CompanyContact;
import com.kiefersink.admin.repository.CompanyContactRepository;
import com.kiefersink.admin.service.CompanyContactService;
import com.kiefersink.admin.transform.TransformCompanyContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyContactServiceImpl implements CompanyContactService {
    @Autowired
    private CompanyContactRepository companyContactRepository;
    private final TransformCompanyContact transformCompanyContact = new TransformCompanyContact();
    //========================================================================================================//
    @Override
    public List<CompanyContact> getAll() {
        List<CompanyContactData> companyContactDataList = new ArrayList<>();
        companyContactRepository.findAll().forEach(companyContactDataList::add);

        List<CompanyContact> companyContacts = new ArrayList<>();
        for (CompanyContactData data : companyContactDataList) {
            CompanyContact companyContact = transformCompanyContact.toModel(data);
            companyContacts.add(companyContact);
        }
        return companyContacts;
    }
    //========================================================================================================//
    @Override
    public CompanyContact get(Integer id) {
        CompanyContactData companyContactData = companyContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company contact not found"));

        return transformCompanyContact.toModel(companyContactData);
    }
    //========================================================================================================//
    @Override
    public CompanyContact create(CompanyContact companyContact) {
        CompanyContactData companyContactData = transformCompanyContact.toData(companyContact);
        CompanyContactData saved = companyContactRepository.save(companyContactData);

        return transformCompanyContact.toModel(saved);
    }
    //========================================================================================================//
    @Override
    public CompanyContact update(Integer id, CompanyContact companyContact) {
        CompanyContactData existing = companyContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company contact not found"));

        existing.setPlatform(companyContact.getPlatform());
        existing.setHandle(companyContact.getHandle());
        existing.setUrl(companyContact.getUrl());

        companyContactRepository.save(existing);
        return companyContact;
    }
    //========================================================================================================//
    @Override
    public void delete(Integer id) {
        companyContactRepository.deleteById(id);
    }
}
