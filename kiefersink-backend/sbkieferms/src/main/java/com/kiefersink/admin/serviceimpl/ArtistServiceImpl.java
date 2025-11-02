package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.ArtistContactData;
import com.kiefersink.admin.entity.ArtistData;
import com.kiefersink.admin.model.Artist;
import com.kiefersink.admin.model.ArtistContact;
import com.kiefersink.admin.repository.ArtistContactRepository;
import com.kiefersink.admin.repository.ArtistRepository;
import com.kiefersink.admin.service.ArtistService;
import com.kiefersink.admin.transform.TransformArtist;
import com.kiefersink.admin.transform.TransformArtistContact;
import com.kiefersink.admin.utils.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtistContactRepository artistContactRepository;
    @Autowired
    private ImageUtils imageUtils;

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
        @Transactional
        public Artist create(Artist artist, MultipartFile image) {
            try {
                if (image != null && !image.isEmpty()) {
                    String fileName = artist.getImageFileName();
                    String savedFileName = imageUtils.saveImage(image, "artist-images", fileName);
                    artist.setImageUrl(savedFileName);
                } else {
                    artist.setImageUrl("default-artist.png");
                }

                ArtistData artistData = transformArtist.toData(artist);
                ArtistData saved = artistRepository.save(artistData);

                if (artist.getContacts() != null) {
                    for (ArtistContact contact : artist.getContacts()) {
                        ArtistContactData contactData = transformArtistContact.toData(contact);
                        contactData.setArtist(saved);
                        artistContactRepository.save(contactData);
                    }
                }

                saved.setId(saved.getId());
                return transformArtist.toModel(saved);


            } catch( IOException e) {
                throw new RuntimeException("Failed to save image file", e);
            }
        }
    //========================================================================================================//
    @Override
    @Transactional
    public Artist update(Integer id, Artist artist, MultipartFile image) {
        ArtistData existing = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        try {
            if (image != null && !image.isEmpty()) {
                String fileName = artist.getImageFileName();
                String savedFileName = imageUtils.saveImage(image, "artist-images", fileName);
                artist.setImageUrl(savedFileName);
            } else {
                artist.setImageUrl(existing.getImageUrl());
            }

            existing.setName(artist.getName());
            existing.setImageUrl(artist.getImageUrl());
            ArtistData saved = artistRepository.save(existing);

            if (artist.getContacts() != null) {
                existing.getContacts().clear();

                for (ArtistContact contact : artist.getContacts()) {
                    ArtistContactData contactData = transformArtistContact.toData(contact);
                    contactData.setArtist(saved);
                    artistContactRepository.save(contactData);
                }
            }


            return transformArtist.toModel(saved);

        } catch (IOException e){
            throw new RuntimeException("Failed to save image file", e);
        }

    }
    //========================================================================================================//
    @Override
    public void delete(Integer id) {
        ArtistData artistData = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        String imageUrl = artistData.getImageUrl();
        if (imageUrl != null && !imageUrl.equals("default-artist.png")) {
            imageUtils.deleteImage(imageUrl, "artist-images");
        }

        artistRepository.deleteById(id);


    }

}
