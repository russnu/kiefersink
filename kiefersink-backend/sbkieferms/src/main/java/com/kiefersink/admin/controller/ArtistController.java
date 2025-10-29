package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.Artist;
import com.kiefersink.admin.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/artist")
@RestController
public class ArtistController {
    @Autowired private ArtistService service;
    //========================================================================================================//
    @GetMapping
    public List<Artist> getAllArtists(@RequestParam(name = "includeContacts", defaultValue = "false") boolean includeContacts) {
        return service.getAll(includeContacts);
    }
    //========================================================================================================//
    @GetMapping("/{id}")
    public Artist getArtist(@PathVariable("id") Integer id, @RequestParam(name = "includeContacts", defaultValue = "false") boolean includeContacts) {
        return service.get(id, includeContacts);
    }
    //========================================================================================================//
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(  @RequestPart("artist") Artist artist, @RequestPart(value = "image", required = false) MultipartFile image) {
        return service.create(artist, image);
    }
    //========================================================================================================//
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Artist updateArtist(@PathVariable("id") Integer id, @RequestPart("artist") Artist artist, @RequestPart(value = "image", required = false) MultipartFile image) {
        return service.update(id, artist, image);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable("id") Integer id) {
        service.delete(id);
    }
    //========================================================================================================//

}
