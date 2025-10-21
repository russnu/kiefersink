package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.Artist;
import com.kiefersink.admin.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist artist) {
        return service.create(artist);
    }
    //========================================================================================================//
    @PutMapping("/{id}")
    public Artist updateArtist(@PathVariable("id") Integer id, @RequestBody Artist artist) {
        return service.update(id, artist);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable("id") Integer id) {
        service.delete(id);
    }
    //========================================================================================================//

}
