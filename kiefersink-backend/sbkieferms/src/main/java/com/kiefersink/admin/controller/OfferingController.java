package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.Offering;
import com.kiefersink.admin.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/offering")
@RestController
public class OfferingController {
    @Autowired
    private OfferingService service;
    //========================================================================================================//
    @GetMapping
    public List<Offering> getAllOfferings() {
        return service.getAll();
    }
    //========================================================================================================//
    @GetMapping("/{id}")
    public Offering getOffering(@PathVariable("id") Integer id) {
        return service.get(id);
    }
    //========================================================================================================//
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Offering createOffering(@RequestPart("offering") Offering offering, @RequestPart(value = "image", required = false) MultipartFile image) {
        return service.create(offering, image);
    }
    //========================================================================================================//
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Offering updateOffering(@PathVariable("id") Integer id, @RequestPart("offering") Offering offering, @RequestPart(value = "image", required = false) MultipartFile image) {
        return service.update(id, offering, image);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deleteOffering(@PathVariable("id") Integer id) {
        service.delete(id);
    }
    //========================================================================================================//

}
