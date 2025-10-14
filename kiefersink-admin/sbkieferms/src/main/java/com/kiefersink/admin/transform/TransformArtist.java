package com.kiefersink.admin.transform;

import com.kiefersink.admin.entity.ArtistData;
import com.kiefersink.admin.model.Artist;

public class TransformArtist implements Transform<Artist, ArtistData>{

    @Override
    public Artist toModel(ArtistData data) {
        Artist artist = new Artist();
        artist.setId(data.getId());
        artist.setName(data.getName());
        artist.setImageUrl("http://localhost:8080/uploads/artist-images/" + data.getImageUrl());

        return artist;
    }

    @Override
    public ArtistData toData(Artist model) {
        ArtistData artistData = new ArtistData();
        artistData.setId(model.getId());
        artistData.setName(model.getName());
        artistData.setImageUrl(model.getImageUrl());

        return artistData;
    }
}
