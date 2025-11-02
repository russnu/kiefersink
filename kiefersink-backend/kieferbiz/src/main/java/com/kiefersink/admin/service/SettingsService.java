package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Settings;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SettingsService {
    List<Settings> getAll();
    Settings get(String settingKey);
    Settings create(Settings settings);
    Settings update(String settingKey, Settings settings, MultipartFile image);
    void delete(String settingKey);
}
