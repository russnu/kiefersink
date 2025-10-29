package com.kiefersink.admin.repository;

import com.kiefersink.admin.entity.ArtistContactData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistContactRepository extends CrudRepository<ArtistContactData, Integer> {
    List<ArtistContactData> findByArtist_Id(Integer artistId);
    void deleteAllByArtistId(Integer artistId);
}
