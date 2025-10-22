package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.ArtistContact;
import com.kiefersink.admin.service.ArtistContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/artist_contact")
@RestController
public class ArtistContactController {
    @Autowired
    private ArtistContactService service;
    //========================================================================================================//
    @GetMapping("/{artist_id}")
    public List<ArtistContact> getArtistContact(@PathVariable("artist_id") Integer artistId) {
        return service.getByArtist(artistId);
    }
    //========================================================================================================//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistContact createArtistContact(@RequestBody ArtistContact artistContact) {
        return service.create(artistContact);
    }
    //========================================================================================================//
    @PutMapping("/{id}")
    public ArtistContact updateArtistContact(@PathVariable("id") Integer id, @RequestBody ArtistContact artistContact) {
        return service.update(id, artistContact);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deleteArtistContact(@PathVariable("id") Integer id) {
        service.delete(id);
    }
    //========================================================================================================//
}
