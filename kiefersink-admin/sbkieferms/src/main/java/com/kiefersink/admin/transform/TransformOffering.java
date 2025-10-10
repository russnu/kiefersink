package com.kiefersink.admin.transform;

import com.kiefersink.admin.entity.OfferingData;
import com.kiefersink.admin.model.Offering;
import org.springframework.beans.factory.annotation.Autowired;

public class TransformOffering implements Transform<Offering, OfferingData>{
    private final TransformOfferingType transformOfferingType = new TransformOfferingType();

    public Offering toModel(OfferingData data) {
        Offering offering = new Offering();
        offering.setId(data.getId());
        offering.setName(data.getName());
        offering.setDescription(data.getDescription());
        offering.setOfferingType(transformOfferingType.toModel(data.getOfferingTypeData()));
        offering.setPriceRange(data.getPriceRange());

        return offering;
    }

    @Override
    public OfferingData toData(Offering model) {
        OfferingData offeringData = new OfferingData();
        offeringData.setId(model.getId());
        offeringData.setName(model.getName());
        offeringData.setDescription(model.getDescription());
        offeringData.setOfferingTypeData(transformOfferingType.toData(model.getOfferingType()));
        offeringData.setPriceRange(model.getPriceRange());

        return offeringData;
    }
}
