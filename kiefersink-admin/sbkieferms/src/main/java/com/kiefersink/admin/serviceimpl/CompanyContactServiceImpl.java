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
    public CompanyContact get(String platform) {
        CompanyContactData companyContactData = companyContactRepository.findById(platform)
                .orElseThrow(() -> new RuntimeException("Company contact not found"));

        return transformCompanyContact.toModel(companyContactData);
    }
    //========================================================================================================//
    @Override
    public CompanyContact create(CompanyContact companyContact) {
        CompanyContactData companyContactData = new CompanyContactData();
        companyContactData.setPlatform(companyContact.getPlatform());
        companyContactData.setValue(companyContact.getValue());

        companyContactRepository.save(companyContactData);

        return companyContact;
    }
    //========================================================================================================//
    @Override
    public CompanyContact update(String platform, CompanyContact companyContact) {
        CompanyContactData companyContactData = companyContactRepository.findById(platform)
                .orElseThrow(() -> new RuntimeException("Company contact not found"));

        companyContactData.setPlatform(companyContact.getPlatform());
        companyContactData.setValue(companyContact.getValue());

        companyContactRepository.save(companyContactData);
        return companyContact;
    }
    //========================================================================================================//
    @Override
    public void delete(String platform) {
        companyContactRepository.deleteById(platform);
    }
}
