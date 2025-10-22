package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Portfolio;

import java.util.List;

public interface PortfolioService {
    List<Portfolio> getAll(boolean featured);
    Portfolio get(Integer id);
    Portfolio create(Portfolio portfolio);
    Portfolio update(Integer id, Portfolio portfolio);
    void delete(Integer id);
}
