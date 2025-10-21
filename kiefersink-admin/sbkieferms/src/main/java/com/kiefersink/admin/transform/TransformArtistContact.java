package com.kiefersink.admin.transform;

import com.kiefersink.admin.entity.ArtistContactData;
import com.kiefersink.admin.entity.ArtistData;
import com.kiefersink.admin.model.ArtistContact;

public class TransformArtistContact implements Transform<ArtistContact, ArtistContactData>{
    private final TransformArtist transformArtist = new TransformArtist();
    @Override
    public ArtistContact toModel(ArtistContactData data) {
        ArtistContact artistContact = new ArtistContact();
        artistContact.setId(data.getId());
        artistContact.setPlatform(data.getPlatform());
        artistContact.setHandle(data.getHandle());
        artistContact.setUrl(data.getUrl());
        artistContact.setArtistId(data.getArtist().getId());

        return artistContact;
    }

    @Override
    public ArtistContactData toData(ArtistContact model) {
        ArtistContactData artistContactData = new ArtistContactData();
        artistContactData.setId(model.getId());
        artistContactData.setPlatform(model.getPlatform());
        artistContactData.setHandle(model.getHandle());
        artistContactData.setUrl(model.getUrl());

        ArtistData artist = new ArtistData();
        artist.setId(model.getArtistId());
        artistContactData.setArtist(artist);

        return artistContactData;
    }
}
