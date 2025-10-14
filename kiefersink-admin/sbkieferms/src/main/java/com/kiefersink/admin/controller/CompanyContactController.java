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
    private CompanyContactService companyContactService;
    //========================================================================================================//
    @GetMapping
    public List<CompanyContact> getAllCompanyContacts() {
        return companyContactService.getAll();
    }
    //========================================================================================================//
    @GetMapping("/{platform}")
    public CompanyContact getCompanyContact(@PathVariable("platform") String platform) {
        return companyContactService.get(platform);
    }
    //========================================================================================================//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyContact createCompanyContact(@RequestBody CompanyContact companyContact) {
        return companyContactService.create(companyContact);
    }
    //========================================================================================================//
    @PutMapping("/{platform}")
    public CompanyContact updateCompanyContact(@PathVariable("platform") String platform, @RequestBody CompanyContact companyContact) {
        return companyContactService.update(platform, companyContact);
    }
    //========================================================================================================//
    @DeleteMapping("/{platform}")
    public void deleteCompanyContact(@PathVariable("platform") String platform) {
        companyContactService.delete(platform);
    }
    //========================================================================================================//
}
