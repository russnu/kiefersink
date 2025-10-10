package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.Inquiry;
import com.kiefersink.admin.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/inquiry")
@RestController
public class InquiryController {
    @Autowired
    private InquiryService inquiryService;
    //========================================================================================================//
    @GetMapping
    public List<Inquiry> getAllInquiries() {
        return inquiryService.getAll();
    }
    //========================================================================================================//
    @GetMapping("/{id}")
    public Inquiry getInquiry(@PathVariable("id") Integer id) {
        return inquiryService.get(id);
    }
    //========================================================================================================//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inquiry createInquiry(@RequestBody Inquiry inquiry) {
        return inquiryService.create(inquiry);
    }
    //========================================================================================================//
    @PutMapping
    public Inquiry updateInquiry(@PathVariable("id") Integer id, @RequestBody Inquiry inquiry) {
        return inquiryService.update(id, inquiry);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deleteInquiry(@PathVariable("id") Integer id) {
        inquiryService.delete(id);
    }
    //========================================================================================================//

}
