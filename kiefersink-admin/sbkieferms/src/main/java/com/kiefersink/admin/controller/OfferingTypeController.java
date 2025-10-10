package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.OfferingType;
import com.kiefersink.admin.service.OfferingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/offering_type")
@RestController
public class OfferingTypeController {
    @Autowired
    private OfferingTypeService offeringTypeService;
    //========================================================================================================//
    @GetMapping
    public List<OfferingType> getAllOfferingTypes() {
        return offeringTypeService.getAll();
    }
    //========================================================================================================//
    @GetMapping("/{id}")
    public OfferingType getOfferingType(@PathVariable("id") Integer id) {
        return offeringTypeService.get(id);
    }
    //========================================================================================================//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfferingType createOfferingType(@RequestBody OfferingType offeringType) {
        return offeringTypeService.create(offeringType);
    }
    //========================================================================================================//
    @PutMapping
    public OfferingType updateOfferingType(@PathVariable("id") Integer id, @RequestBody OfferingType offeringType) {
        return offeringTypeService.update(id, offeringType);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deleteOfferingType(@PathVariable("id") Integer id) {
        offeringTypeService.delete(id);
    }
    //========================================================================================================//

}
