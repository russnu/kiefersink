package com.kiefersink.admin.transform;


import com.kiefersink.admin.entity.ArtistContactData;
import com.kiefersink.admin.entity.CompanyContactData;
import com.kiefersink.admin.model.CompanyContact;

public class TransformCompanyContact implements Transform<CompanyContact, CompanyContactData>{
    @Override
    public CompanyContact toModel(CompanyContactData data) {
        CompanyContact companyContact = new CompanyContact();
        companyContact.setPlatform(data.getPlatform());
        companyContact.setValue(data.getValue());

        return companyContact;
    }

    @Override
    public CompanyContactData toData(CompanyContact model) {
        CompanyContactData companyContactData = new CompanyContactData();
        companyContactData.setPlatform(model.getPlatform());
        companyContactData.setValue(model.getValue());

        return companyContactData;
    }
}
