package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.OfferingData;
import com.kiefersink.admin.entity.OfferingTypeData;
import com.kiefersink.admin.model.OfferingType;
import com.kiefersink.admin.repository.OfferingTypeRepository;
import com.kiefersink.admin.service.OfferingTypeService;
import com.kiefersink.admin.transform.TransformOfferingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferingTypeServiceImpl implements OfferingTypeService {
    @Autowired
    private OfferingTypeRepository offeringTypeRepository;
    private final TransformOfferingType transformOfferingType = new TransformOfferingType();
    //========================================================================================================//
    @Override
    public List<OfferingType> getAll() {
        List<OfferingTypeData> offeringTypeDataList = new ArrayList<>();
        offeringTypeRepository.findAll().forEach(offeringTypeDataList::add);

        List<OfferingType> offeringTypes = new ArrayList<>();
        for (OfferingTypeData data : offeringTypeDataList) {
            OfferingType offeringType = transformOfferingType.toModel(data);
            offeringTypes.add(offeringType);
        }
        return offeringTypes;
    }
    //========================================================================================================//
    @Override
    public OfferingType get(Integer id) {
        OfferingTypeData offeringTypeData = offeringTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offering type not found"));

        return transformOfferingType.toModel(offeringTypeData);
    }
    //========================================================================================================//
    @Override
    public OfferingType create(OfferingType offeringType) {
        OfferingTypeData offeringTypeData = new OfferingTypeData();
        offeringTypeData.setId(offeringType.getId());
        offeringTypeData.setName(offeringType.getName());

        OfferingTypeData saved = offeringTypeRepository.save(offeringTypeData);
        offeringType.setId(saved.getId());

        return offeringType;
    }
    //========================================================================================================//
    @Override
    public OfferingType update(Integer id, OfferingType offeringType) {
        OfferingTypeData offeringTypeData = offeringTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offering type not found"));

        offeringTypeData.setId(offeringType.getId());
        offeringTypeData.setName(offeringType.getName());

        offeringTypeRepository.save(offeringTypeData);
        return offeringType;
    }
    //========================================================================================================//
    @Override
    public void delete(Integer id) {
        offeringTypeRepository.deleteById(id);
    }
}
