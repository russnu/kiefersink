package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.ArtistData;
import com.kiefersink.admin.entity.PortfolioData;
import com.kiefersink.admin.model.Portfolio;
import com.kiefersink.admin.repository.PortfolioRepository;
import com.kiefersink.admin.service.PortfolioService;
import com.kiefersink.admin.transform.TransformArtist;
import com.kiefersink.admin.transform.TransformPortfolio;
import com.kiefersink.admin.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private ImageUtils imageUtils;
    private final TransformPortfolio transformPortfolio = new TransformPortfolio();
    private final TransformArtist transformArtist = new TransformArtist();
    //========================================================================================================//
    @Override
    public List<Portfolio> getAll(boolean featured) {
        List<PortfolioData> portfolioDataList;

        if (featured) {
            portfolioDataList = portfolioRepository.findByFeaturedTrue();
        } else {
            portfolioDataList = portfolioRepository.findAll();
        }

        List<Portfolio> portfolios = new ArrayList<>();
        for (PortfolioData data : portfolioDataList) {
            Portfolio portfolio = transformPortfolio.toModel(data);
            portfolio.getArtist().setImageUrl(null);
            portfolio.getOffering().setDescription(null);

            portfolios.add(portfolio);
        }
        return portfolios;
    }
    //========================================================================================================//
    @Override
    public Portfolio get(Integer id) {
        PortfolioData portfolioData = portfolioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        Portfolio portfolio = transformPortfolio.toModel(portfolioData);
        portfolio.getOffering().setDescription(null);

        return portfolio;
    }

    //========================================================================================================//
    @Override
    public Portfolio create(Portfolio portfolio, MultipartFile image) {
        try{
            if (image != null && !image.isEmpty()) {
                String fileName = portfolio.getImageFileName();
                String savedFileName = imageUtils.saveImage(image, "gallery", fileName);
                portfolio.setImageUrl(savedFileName);
            } else {
                portfolio.setImageUrl("default-artist.png");
            }

            PortfolioData portfolioData = transformPortfolio.toData(portfolio);
            PortfolioData saved = portfolioRepository.save(portfolioData);
            return transformPortfolio.toModel(saved);

        } catch(IOException e){
            throw new RuntimeException("Failed to save image file", e);
        }
    }
    //========================================================================================================//
    @Override
    public Portfolio update(Integer id, Portfolio portfolio, MultipartFile image) {
        PortfolioData existing = portfolioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        try{
            if (image != null && !image.isEmpty()) {
                String fileName = portfolio.getImageFileName();
                String savedFileName = imageUtils.saveImage(image, "gallery", fileName);
                portfolio.setImageUrl(savedFileName);
            } else {
                portfolio.setImageUrl(existing.getImageUrl());
            }

            existing.setTitle(portfolio.getTitle());
            existing.setDescription(portfolio.getDescription());
            existing.setImageUrl(portfolio.getImageUrl());
            existing.setArtist(transformArtist.toData(portfolio.getArtist()));

            PortfolioData saved = portfolioRepository.save(existing);
            return transformPortfolio.toModel(saved);
        } catch(IOException e){
            throw new RuntimeException("Failed to save image file", e);
        }
    }
    //========================================================================================================//
    @Override
    public void delete(Integer id) {
        PortfolioData portfolioData = portfolioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        String imageUrl = portfolioData.getImageUrl();
        if (imageUrl != null && !imageUrl.equals("default-artist.png")) {
            imageUtils.deleteImage(imageUrl, "gallery");
        }
        portfolioRepository.deleteById(id);
    }
    //========================================================================================================//
    @Override
    public Portfolio updateFeatured(Integer id, boolean featured) {
        PortfolioData portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        portfolio.setFeatured(featured);
        PortfolioData updated = portfolioRepository.save(portfolio);

        return transformPortfolio.toModel(updated);
    }
}
