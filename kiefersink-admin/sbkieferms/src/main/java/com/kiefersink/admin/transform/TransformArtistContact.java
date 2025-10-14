package com.kiefersink.admin.transform;

import com.kiefersink.admin.entity.ArtistContactData;
import com.kiefersink.admin.model.ArtistContact;

public class TransformArtistContact implements Transform<ArtistContact, ArtistContactData>{
    private final TransformArtist transformArtist = new TransformArtist();
    @Override
    public ArtistContact toModel(ArtistContactData data) {
        ArtistContact artistContact = new ArtistContact();
        artistContact.setPlatform(data.getPlatform());
        artistContact.setHandle(data.getHandle());
        artistContact.setUrl(data.getUrl());
        artistContact.setArtist(transformArtist.toModel(data.getArtist()));

        return artistContact;
    }

    @Override
    public ArtistContactData toData(ArtistContact model) {
        ArtistContactData artistContactData = new ArtistContactData();
        artistContactData.setPlatform(model.getPlatform());
        artistContactData.setHandle(model.getHandle());
        artistContactData.setUrl(model.getUrl());
        artistContactData.setArtist(transformArtist.toData(model.getArtist()));

        return artistContactData;
    }
}
