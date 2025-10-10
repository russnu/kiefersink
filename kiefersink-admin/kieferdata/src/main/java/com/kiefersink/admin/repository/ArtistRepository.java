package com.kiefersink.admin.repository;

import com.kiefersink.admin.entity.ArtistData;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<ArtistData, Integer> {
}
