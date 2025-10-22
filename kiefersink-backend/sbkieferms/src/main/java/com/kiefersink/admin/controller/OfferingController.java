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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Offering createOffering(@RequestBody Offering offering) {
        return service.create(offering);
    }
    //========================================================================================================//
    @PutMapping
    public Offering updateOffering(@PathVariable("id") Integer id, @RequestBody Offering offering) {
        return service.update(id, offering);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deleteOffering(@PathVariable("id") Integer id) {
        service.delete(id);
    }
    //========================================================================================================//

}
