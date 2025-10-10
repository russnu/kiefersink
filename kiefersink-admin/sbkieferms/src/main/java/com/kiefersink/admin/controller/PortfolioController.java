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
    private PortfolioService portfolioService;
    //========================================================================================================//
    @GetMapping
    public List<Portfolio> getAllPortfolios() {
        return portfolioService.getAll();
    }
    //========================================================================================================//
    @GetMapping("/{id}")
    public Portfolio getPortfolio(@PathVariable("id") Integer id) {
        return portfolioService.get(id);
    }
    //========================================================================================================//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Portfolio createPortfolio(@RequestBody Portfolio portfolio) {
        return portfolioService.create(portfolio);
    }
    //========================================================================================================//
    @PutMapping
    public Portfolio updatePortfolio(@PathVariable("id") Integer id, @RequestBody Portfolio portfolio) {
        return portfolioService.update(id, portfolio);
    }
    //========================================================================================================//
    @DeleteMapping("/{id}")
    public void deletePortfolio(@PathVariable("id") Integer id) {
        portfolioService.delete(id);
    }
    //========================================================================================================//

}
