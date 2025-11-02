package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.Portfolio;
import com.kiefersink.admin.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/portfolio")
@RestController
public class PortfolioController {
    @Autowired
    private PortfolioService service;
    //========================================================================================================//
    @GetMapping
    public List<Portfolio> getAllPortfolios(@RequestParam(name = "featured", defaultValue = "false") boolean featured) {
        return service.getAll(featured);
    }
    //========================================================================================================//
    @GetMapping("/{id}")
    public Portfolio getPortfolio(@PathVariable("id") Integer id) {
        return service.get(id);
    }
    //========================================================================================================//
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Portfolio createPortfolio(@RequestPart("portfolio") Portfolio portfolio, @RequestPart(value = "image", required = false) MultipartFile image) {
        return service.create(portfolio, image);
    }
    //========================================================================================================//
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Portfolio updatePortfolio(@PathVariable("id") Integer id, @RequestPart("portfolio") Portfolio portfolio, @RequestPart(value = "image", required = false) MultipartFile image) {
        return service.update(id, portfolio, image);
    }
    //========================================================================================================//
    @PatchMapping("/{id}/featured")
    public Portfolio toggleFeatured(@PathVariable("id") Integer id, @RequestParam(name = "featured", defaultValue = "false") boolean featured) {
        return service.updateFeatured(id, featured);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deletePortfolio(@PathVariable("id") Integer id) {
        service.delete(id);
    }
    //========================================================================================================//

}
