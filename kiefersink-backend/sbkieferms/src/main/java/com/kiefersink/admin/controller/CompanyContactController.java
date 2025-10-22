package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.Artist;
import com.kiefersink.admin.model.CompanyContact;
import com.kiefersink.admin.service.CompanyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/company_contact")
@RestController
public class CompanyContactController {
    @Autowired
    private CompanyContactService service;
    //========================================================================================================//
    @GetMapping
    public List<CompanyContact> getAllCompanyContacts() {
        return service.getAll();
    }
    //========================================================================================================//
    @GetMapping("/{id}")
    public CompanyContact getCompanyContact(@PathVariable("id") Integer id) {
        return service.get(id);
    }
    //========================================================================================================//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyContact createCompanyContact(@RequestBody CompanyContact companyContact) {
        return service.create(companyContact);
    }
    //========================================================================================================//
    @PutMapping("/{id}")
    public CompanyContact updateCompanyContact(@PathVariable("id") Integer id, @RequestBody CompanyContact companyContact) {
        return service.update(id, companyContact);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deleteCompanyContact(@PathVariable("id") Integer id) {
        service.delete(id);
    }
    //========================================================================================================//
}
