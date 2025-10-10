package com.kiefersink.admin.service;

import com.kiefersink.admin.model.Settings;

import java.util.List;

public interface SettingsService {
    List<Settings> getAll();
    Settings get(String settingKey);
    Settings create(Settings settings);
    Settings update(String settingKey, Settings settings);
    void delete(String settingKey);
}
