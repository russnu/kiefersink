package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Artist;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArtistService {
    List<Artist> getAll(boolean includeContacts);
    Artist get(Integer id, boolean includeContacts);
    //========================================================================================================//
    Artist create(Artist artist, MultipartFile image);
    Artist update(Integer id, Artist artist, MultipartFile image);
    //========================================================================================================//
    void delete(Integer id);
}
