package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> getAll(boolean includeContacts);
    Artist get(Integer id, boolean includeContacts);
    Artist create(Artist artist);
    Artist update(Integer id, Artist artist);
    void delete(Integer id);
}
