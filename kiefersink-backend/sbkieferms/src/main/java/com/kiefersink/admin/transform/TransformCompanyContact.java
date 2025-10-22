package com.kiefersink.admin.transform;


import com.kiefersink.admin.entity.CompanyContactData;
import com.kiefersink.admin.model.CompanyContact;

public class TransformCompanyContact implements Transform<CompanyContact, CompanyContactData>{
    @Override
    public CompanyContact toModel(CompanyContactData data) {
        CompanyContact companyContact = new CompanyContact();
        companyContact.setId(data.getId());
        companyContact.setPlatform(data.getPlatform());
        companyContact.setHandle(data.getHandle());
        companyContact.setUrl(data.getUrl());

        return companyContact;
    }

    @Override
    public CompanyContactData toData(CompanyContact model) {
        CompanyContactData companyContactData = new CompanyContactData();
        companyContactData.setId(model.getId());
        companyContactData.setPlatform(model.getPlatform());
        companyContactData.setHandle(model.getHandle());
        companyContactData.setUrl(model.getUrl());

        return companyContactData;
    }
}
