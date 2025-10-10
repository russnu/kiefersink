package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.ArtistData;
import com.kiefersink.admin.model.Artist;
import com.kiefersink.admin.repository.ArtistRepository;
import com.kiefersink.admin.service.ArtistService;
import com.kiefersink.admin.transform.TransformArtist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    private final TransformArtist transformArtist = new TransformArtist();
    //========================================================================================================//
    @Override
    public List<Artist> getAll() {
        List<ArtistData> artistDataList = new ArrayList<>();
        artistRepository.findAll().forEach(artistDataList::add);

        List<Artist> artists = new ArrayList<>();
        for (ArtistData data : artistDataList) {
            Artist artist = transformArtist.toModel(data);
            artists.add(artist);
        }
        return artists;
    }
    //========================================================================================================//
    @Override
    public Artist get(Integer id) {
        ArtistData artistData = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        return transformArtist.toModel(artistData);
    }
    //========================================================================================================//
    @Override
    public Artist create(Artist artist) {
        ArtistData artistData = new ArtistData();
        artistData.setName(artist.getName());
        artistData.setImageUrl(artist.getImageUrl());

        ArtistData saved = artistRepository.save(artistData);
        artist.setId(saved.getId());

        return artist;
    }
    //========================================================================================================//
    @Override
    public Artist update(Integer id, Artist artist) {
        ArtistData artistData = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        artistData.setName(artist.getName());
        artistData.setImageUrl(artist.getImageUrl());

        artistRepository.save(artistData);
        return artist;
    }
    //========================================================================================================//
    @Override
    public void delete(Integer id) {
        artistRepository.deleteById(id);
    }

}
