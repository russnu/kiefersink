package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.PortfolioData;
import com.kiefersink.admin.model.Portfolio;
import com.kiefersink.admin.repository.PortfolioRepository;
import com.kiefersink.admin.service.PortfolioService;
import com.kiefersink.admin.transform.TransformArtist;
import com.kiefersink.admin.transform.TransformPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;
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
    public Portfolio create(Portfolio portfolio) {
        PortfolioData portfolioData = new PortfolioData();
        portfolioData.setId(portfolio.getId());
        portfolioData.setArtist(transformArtist.toData(portfolio.getArtist()));
        portfolioData.setTitle(portfolio.getTitle());
        portfolioData.setDescription(portfolio.getDescription());
        portfolioData.setImageUrl(portfolio.getImageUrl());

        PortfolioData saved = portfolioRepository.save(portfolioData);
        portfolio.setId(saved.getId());

        return portfolio;
    }
    //========================================================================================================//
    @Override
    public Portfolio update(Integer id, Portfolio portfolio) {
        PortfolioData portfolioData = portfolioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        portfolioData.setId(portfolio.getId());
        portfolioData.setArtist(transformArtist.toData(portfolio.getArtist()));
        portfolioData.setTitle(portfolio.getTitle());
        portfolioData.setDescription(portfolio.getDescription());
        portfolioData.setImageUrl(portfolio.getImageUrl());

        portfolioRepository.save(portfolioData);
        return portfolio;
    }
    //========================================================================================================//
    @Override
    public void delete(Integer id) {
        portfolioRepository.deleteById(id);
    }
}
