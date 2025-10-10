package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.ArtistContactData;
import com.kiefersink.admin.entity.ArtistData;
import com.kiefersink.admin.model.Artist;
import com.kiefersink.admin.model.ArtistContact;
import com.kiefersink.admin.repository.ArtistContactRepository;
import com.kiefersink.admin.service.ArtistContactService;
import com.kiefersink.admin.transform.TransformArtist;
import com.kiefersink.admin.transform.TransformArtistContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistContactServiceImpl implements ArtistContactService {
    @Autowired
    private ArtistContactRepository artistContactRepository;
    private final TransformArtistContact transformArtistContact = new TransformArtistContact();
    private final TransformArtist transformArtist = new TransformArtist();
    //========================================================================================================//
    @Override
    public List<ArtistContact> getByArtist(Integer artistId) {

        List<ArtistContactData> artistContactDataList = artistContactRepository.findByArtist_Id(artistId);

        List<ArtistContact> artistContacts = new ArrayList<>();
        for (ArtistContactData data : artistContactDataList) {
            ArtistContact artistContact = transformArtistContact.toModel(data);
            artistContacts.add(artistContact);
        }
        return artistContacts;
    }
    //========================================================================================================//
    @Override
    public ArtistContact create(ArtistContact artistContact) {
        ArtistContactData artistContactData = new ArtistContactData();
        artistContactData.setArtist(transformArtist.toData(artistContact.getArtist()));
        artistContactData.setPlatform(artistContact.getPlatform());
        artistContactData.setValue(artistContact.getValue());

        ArtistContactData saved = artistContactRepository.save(artistContactData);
        artistContact.setId(saved.getId());

        return artistContact;
    }
    //========================================================================================================//
    @Override
    public ArtistContact update(Integer id, ArtistContact artistContact) {
        ArtistContactData artistContactData = artistContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist contact not found"));

        artistContactData.setArtist(transformArtist.toData(artistContact.getArtist()));
        artistContactData.setPlatform(artistContact.getPlatform());
        artistContactData.setValue(artistContact.getValue());

        artistContactRepository.save(artistContactData);
        return artistContact;
    }
    //========================================================================================================//
    @Override
    public void delete(Integer id) {
        artistContactRepository.deleteById(id);
    }
}
