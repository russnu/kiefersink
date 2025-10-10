package com.kiefersink.admin.repository;

import com.kiefersink.admin.entity.SettingsData;
import org.springframework.data.repository.CrudRepository;

public interface SettingsRepository extends CrudRepository<SettingsData, String> {
}
