package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Portfolio;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PortfolioService {
    List<Portfolio> getAll(boolean featured);
    Portfolio get(Integer id);
    Portfolio create(Portfolio portfolio, MultipartFile image);
    Portfolio update(Integer id, Portfolio portfolio, MultipartFile image);
    void delete(Integer id);
    //========================================================================================================//
    Portfolio updateFeatured(Integer id, boolean featured);
}
