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
    private ArtistContactService artistContactService;
    //========================================================================================================//
    @GetMapping("/{artist_id}")
    public List<ArtistContact> getArtistContact(@PathVariable("artist_id") Integer artistId) {
        return artistContactService.getByArtist(artistId);
    }
    //========================================================================================================//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistContact createArtistContact(@RequestBody ArtistContact artistContact) {
        return artistContactService.create(artistContact);
    }
    //========================================================================================================//
    @PutMapping("/{id}")
    public ArtistContact updateArtistContact(@PathVariable("id") Integer id, @RequestBody ArtistContact artistContact) {
        return artistContactService.update(id, artistContact);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deleteArtistContact(@PathVariable("id") Integer id) {
        artistContactService.delete(id);
    }
    //========================================================================================================//
}
