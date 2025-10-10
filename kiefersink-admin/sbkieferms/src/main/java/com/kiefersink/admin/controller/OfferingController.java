package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.Offering;
import com.kiefersink.admin.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/offering")
@RestController
public class OfferingController {
    @Autowired
    private OfferingService offeringService;
    //========================================================================================================//
    @GetMapping
    public List<Offering> getAllOfferings() {
        return offeringService.getAll();
    }
    //========================================================================================================//
    @GetMapping("/{id}")
    public Offering getOffering(@PathVariable("id") Integer id) {
        return offeringService.get(id);
    }
    //========================================================================================================//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Offering createOffering(@RequestBody Offering offering) {
        return offeringService.create(offering);
    }
    //========================================================================================================//
    @PutMapping
    public Offering updateOffering(@PathVariable("id") Integer id, @RequestBody Offering offering) {
        return offeringService.update(id, offering);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deleteOffering(@PathVariable("id") Integer id) {
        offeringService.delete(id);
    }
    //========================================================================================================//

}
