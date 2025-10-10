package com.kiefersink.admin.transform;

import com.kiefersink.admin.entity.PortfolioData;
import com.kiefersink.admin.model.Portfolio;

public class TransformPortfolio implements Transform<Portfolio, PortfolioData>{
    private final TransformArtist transformArtist = new TransformArtist();
    @Override
    public Portfolio toModel(PortfolioData data) {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(data.getId());
        portfolio.setArtist(transformArtist.toModel(data.getArtist()));
        portfolio.setTitle(data.getTitle());
        portfolio.setDescription(data.getDescription());
        portfolio.setImageUrl(data.getImageUrl());

        return portfolio;
    }

    @Override
    public PortfolioData toData(Portfolio model) {
        PortfolioData portfolioData = new PortfolioData();
        portfolioData.setId(model.getId());
        portfolioData.setArtist(transformArtist.toData(model.getArtist()));
        portfolioData.setTitle(model.getTitle());
        portfolioData.setDescription(model.getDescription());
        portfolioData.setImageUrl(model.getImageUrl());

        return portfolioData;
    }
}
