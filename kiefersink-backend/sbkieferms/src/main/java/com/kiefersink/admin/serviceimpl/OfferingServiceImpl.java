package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.OfferingData;
import com.kiefersink.admin.entity.PortfolioData;
import com.kiefersink.admin.model.Offering;
import com.kiefersink.admin.repository.OfferingRepository;
import com.kiefersink.admin.service.OfferingService;
import com.kiefersink.admin.transform.TransformCategory;
import com.kiefersink.admin.transform.TransformOffering;
import com.kiefersink.admin.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferingServiceImpl implements OfferingService {
    @Autowired
    private OfferingRepository offeringRepository;
    @Autowired
    private ImageUtils imageUtils;
    private final TransformOffering transformOffering = new TransformOffering();
    private final TransformCategory transformCategory = new TransformCategory();
    //========================================================================================================//
    @Override
    public List<Offering> getAll() {
        List<OfferingData> offeringDataList = new ArrayList<>();
        offeringRepository.findAll().forEach(offeringDataList::add);

        List<Offering> offerings = new ArrayList<>();
        for (OfferingData data : offeringDataList) {
            Offering offering = transformOffering.toModel(data);
            offerings.add(offering);
        }
        return offerings;
    }
    //========================================================================================================//
    @Override
    public Offering get(Integer id) {
        OfferingData offeringData = offeringRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offering not found"));

        return transformOffering.toModel(offeringData);
    }
    //========================================================================================================//
    @Override
    public Offering create(Offering offering, MultipartFile image) {

        try {
            if (image != null && !image.isEmpty()) {
                String fileName = offering.getImageFileName();
                String savedFileName = imageUtils.saveImage(image, "offering-images", fileName);
                offering.setImageUrl(savedFileName);


            } else {
                offering.setImageUrl("default-image.png");
            }

            OfferingData offeringData = transformOffering.toData(offering);
            OfferingData saved = offeringRepository.save(offeringData);
            return transformOffering.toModel(saved);

        } catch(IOException e){
            throw new RuntimeException("Failed to save image file", e);
        }

    }
    //========================================================================================================//
    @Override
    public Offering update(Integer id, Offering offering, MultipartFile image) {
        OfferingData existing = offeringRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offering not found"));

        try {
            if (image != null && !image.isEmpty()) {
                String fileName = offering.getImageFileName();
                String savedFileName = imageUtils.saveImage(image, "offering-images", fileName);
                offering.setImageUrl(savedFileName);
            } else {
                offering.setImageUrl(existing.getImageUrl());
            }

            existing.setName(offering.getName());
            existing.setDescription(offering.getDescription());
            existing.setPriceRange(offering.getPriceRange());
            existing.setImageUrl(offering.getImageUrl());

            OfferingData saved = offeringRepository.save(existing);
            return transformOffering.toModel(saved);

        } catch(IOException e){
            throw new RuntimeException("Failed to save image file", e);
        }
    }
    //========================================================================================================//
    @Override
    public void delete(Integer id) {
        OfferingData offeringData = offeringRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offering not found"));

        String imageUrl = offeringData.getImageUrl();
        if (imageUrl != null && !imageUrl.equals("default-image.png")) {
            imageUtils.deleteImage(imageUrl, "offering-images");
        }
        offeringRepository.deleteById(id);
    }
}
