package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.OfferingData;
import com.kiefersink.admin.model.Offering;
import com.kiefersink.admin.repository.OfferingRepository;
import com.kiefersink.admin.service.OfferingService;
import com.kiefersink.admin.transform.TransformOffering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferingServiceImpl implements OfferingService {
    @Autowired
    private OfferingRepository offeringRepository;
    private final TransformOffering transformOffering = new TransformOffering();
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
    public Offering create(Offering offering) {
        OfferingData offeringData = new OfferingData();
        offeringData.setId(offering.getId());
        offeringData.setName(offering.getName());
        offeringData.setDescription(offering.getDescription());
        offeringData.setPriceRange(offering.getPriceRange());

        OfferingData saved = offeringRepository.save(offeringData);
        offering.setId(saved.getId());

        return offering;
    }
    //========================================================================================================//
    @Override
    public Offering update(Integer id, Offering offering) {
        OfferingData offeringData = offeringRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offering not found"));

        offeringData.setId(offering.getId());
        offeringData.setName(offering.getName());
        offeringData.setDescription(offering.getDescription());
        offeringData.setPriceRange(offering.getPriceRange());

        offeringRepository.save(offeringData);
        return offering;
    }
    //========================================================================================================//
    @Override
    public void delete(Integer id) {
        offeringRepository.deleteById(id);
    }
}
