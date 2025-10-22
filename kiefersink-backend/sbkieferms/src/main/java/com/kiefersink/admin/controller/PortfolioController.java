package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.Portfolio;
import com.kiefersink.admin.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Portfolio createPortfolio(@RequestBody Portfolio portfolio) {
        return service.create(portfolio);
    }
    //========================================================================================================//
    @PutMapping
    public Portfolio updatePortfolio(@PathVariable("id") Integer id, @RequestBody Portfolio portfolio) {
        return service.update(id, portfolio);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deletePortfolio(@PathVariable("id") Integer id) {
        service.delete(id);
    }
    //========================================================================================================//

}
