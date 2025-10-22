package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.InquiryData;
import com.kiefersink.admin.model.Inquiry;
import com.kiefersink.admin.repository.InquiryRepository;
import com.kiefersink.admin.service.InquiryService;
import com.kiefersink.admin.transform.TransformInquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {
    @Autowired
    private InquiryRepository inquiryRepository;
    private final TransformInquiry transformInquiry = new TransformInquiry();
    //========================================================================================================//
    @Override
    public List<Inquiry> getAll() {
        List<InquiryData> inquiryDataList = new ArrayList<>();
        inquiryRepository.findAll().forEach(inquiryDataList::add);

        List<Inquiry> inquiries = new ArrayList<>();
        for (InquiryData data : inquiryDataList) {
            Inquiry inquiry = transformInquiry.toModel(data);
            inquiries.add(inquiry);
        }
        return inquiries;
    }
    //========================================================================================================//
    @Override
    public Inquiry get(Integer id) {
        InquiryData inquiryData = inquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        return transformInquiry.toModel(inquiryData);
    }
    //========================================================================================================//
    @Override
    public Inquiry create(Inquiry inquiry) {
        InquiryData inquiryData = new InquiryData();
        inquiryData.setId(inquiry.getId());
        inquiryData.setCustomerName(inquiry.getCustomerName());
        inquiryData.setCustomerEmail(inquiry.getCustomerEmail());
        inquiryData.setCustomerPhone(inquiry.getCustomerPhone());
        inquiryData.setSubject(inquiry.getSubject());
        inquiryData.setMessage(inquiry.getMessage());
        inquiryData.setCreatedAt(inquiry.getCreatedAt());

        InquiryData saved = inquiryRepository.save(inquiryData);
        inquiry.setId(saved.getId());

        return inquiry;
    }
    //========================================================================================================//
    @Override
    public Inquiry update(Integer id, Inquiry inquiry) {
        InquiryData inquiryData = inquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        inquiryData.setId(inquiry.getId());
        inquiryData.setCustomerName(inquiry.getCustomerName());
        inquiryData.setCustomerEmail(inquiry.getCustomerEmail());
        inquiryData.setCustomerPhone(inquiry.getCustomerPhone());
        inquiryData.setSubject(inquiry.getSubject());
        inquiryData.setMessage(inquiry.getMessage());
        inquiryData.setCreatedAt(inquiry.getCreatedAt());

        inquiryRepository.save(inquiryData);
        return inquiry;
    }
    //========================================================================================================//
    @Override
    public void delete(Integer id) {
        inquiryRepository.deleteById(id);
    }
}
