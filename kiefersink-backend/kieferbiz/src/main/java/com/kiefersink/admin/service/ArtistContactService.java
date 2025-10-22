package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Artist;
import com.kiefersink.admin.model.ArtistContact;

import java.util.List;

public interface ArtistContactService {
    List<ArtistContact> getByArtist(Integer artistId);
    ArtistContact create(ArtistContact artistContact);
    ArtistContact update(Integer id, ArtistContact artistContact);
    void delete(Integer id);
}
