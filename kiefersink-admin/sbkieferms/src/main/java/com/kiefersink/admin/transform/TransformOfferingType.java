package com.kiefersink.admin.transform;

import com.kiefersink.admin.entity.OfferingTypeData;
import com.kiefersink.admin.model.OfferingType;

public class TransformOfferingType implements Transform<OfferingType, OfferingTypeData>{

    @Override
    public OfferingType toModel(OfferingTypeData data) {
        OfferingType offeringType = new OfferingType();
        offeringType.setId(data.getId());
        offeringType.setName(data.getName());

        return offeringType;
    }

    @Override
    public OfferingTypeData toData(OfferingType model) {
        OfferingTypeData offeringTypeData = new OfferingTypeData();
        offeringTypeData.setId(model.getId());
        offeringTypeData.setName(model.getName());

        return offeringTypeData;
    }
}
