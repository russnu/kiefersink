package com.kiefersink.admin.transform;

import com.kiefersink.admin.entity.OfferingData;
import com.kiefersink.admin.model.Offering;

public class TransformOffering implements Transform<Offering, OfferingData>{

    private final TransformCategory transformCategory = new TransformCategory();


    public Offering toModel(OfferingData data) {
        Offering offering = new Offering();
        offering.setId(data.getId());
        offering.setName(data.getName());
        offering.setDescription(data.getDescription());
        offering.setPriceRange(data.getPriceRange());
        offering.setImageUrl("http://localhost:8080/uploads/offering-images/" + data.getImageUrl());
        offering.setCategory(transformCategory.toModel(data.getCategory()));

        return offering;
    }

    @Override
    public OfferingData toData(Offering model) {
        OfferingData offeringData = new OfferingData();
        offeringData.setId(model.getId());
        offeringData.setName(model.getName());
        offeringData.setDescription(model.getDescription());
        offeringData.setPriceRange(model.getPriceRange());
        offeringData.setImageUrl(model.getImageUrl());
        offeringData.setCategory(transformCategory.toData(model.getCategory()));

        return offeringData;
    }
}
