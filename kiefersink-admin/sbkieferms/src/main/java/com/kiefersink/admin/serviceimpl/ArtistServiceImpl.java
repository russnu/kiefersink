package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.ArtistData;
import com.kiefersink.admin.model.Artist;
import com.kiefersink.admin.model.Category;
import com.kiefersink.admin.repository.ArtistRepository;
import com.kiefersink.admin.service.ArtistService;
import com.kiefersink.admin.transform.TransformArtist;
import com.kiefersink.admin.transform.TransformArtistContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    private final TransformArtist transformArtist = new TransformArtist();
    private final TransformArtistContact transformArtistContact =  new TransformArtistContact();
    //========================================================================================================//
    @Override
    public List<Artist> getAll(boolean includeContacts) {
        List<ArtistData> artistDataList = new ArrayList<>();
        artistRepository.findAll().forEach(artistDataList::add);

        List<Artist> artists = new ArrayList<>();
        for (ArtistData data : artistDataList) {
            Artist artist = transformArtist.toModel(data);
            if (includeContacts) {
                artist.setContacts(
                        data.getContacts().stream()
                                .map(transformArtistContact::toModel)
                                .toList()
                );
            }
            artists.add(artist);
        }
        return artists;
    }
    //========================================================================================================//
    @Override
    public Artist get(Integer id, boolean includeContacts) {
        ArtistData artistData = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        Artist artist = transformArtist.toModel(artistData);
        if (includeContacts) {
            artist.setContacts(
                    artistData.getContacts().stream()
                            .map(transformArtistContact::toModel)
                            .toList()
            );
        }

        return artist;
    }
    //========================================================================================================//
    @Override
    public Artist create(Artist artist) {
        ArtistData artistData = transformArtist.toData(artist);
        ArtistData saved = artistRepository.save(artistData);
        saved.setId(saved.getId());

        return transformArtist.toModel(saved);
    }
    //========================================================================================================//
    @Override
    public Artist update(Integer id, Artist artist) {
        ArtistData existing = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        existing.setName(artist.getName());
        existing.setImageUrl(artist.getImageUrl());

        artistRepository.save(existing);
        return artist;
    }
    //========================================================================================================//
    @Override
    public void delete(Integer id) {
        artistRepository.deleteById(id);
    }

}
