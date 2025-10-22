package com.kiefersink.admin.transform;

import com.kiefersink.admin.entity.InquiryData;
import com.kiefersink.admin.model.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;

public class    TransformInquiry implements Transform<Inquiry, InquiryData>{
    private final TransformOffering transformOffering = new TransformOffering();

    @Override
    public Inquiry toModel(InquiryData data) {
        Inquiry inquiry = new Inquiry();
        inquiry.setId(data.getId());
        inquiry.setCustomerName(data.getCustomerName());
        inquiry.setCustomerEmail(data.getCustomerEmail());
        inquiry.setCustomerPhone(data.getCustomerPhone());
        inquiry.setSubject(data.getSubject());
        inquiry.setMessage(data.getMessage());
        inquiry.setCreatedAt(data.getCreatedAt());

        return inquiry;
    }

    @Override
    public InquiryData toData(Inquiry model) {
        InquiryData inquiryData = new InquiryData();
        inquiryData.setId(model.getId());
        inquiryData.setCustomerName(model.getCustomerName());
        inquiryData.setCustomerEmail(model.getCustomerEmail());
        inquiryData.setCustomerPhone(model.getCustomerPhone());
        inquiryData.setSubject(model.getSubject());
        inquiryData.setMessage(model.getMessage());
        inquiryData.setCreatedAt(model.getCreatedAt());

        return inquiryData;
    }
}
