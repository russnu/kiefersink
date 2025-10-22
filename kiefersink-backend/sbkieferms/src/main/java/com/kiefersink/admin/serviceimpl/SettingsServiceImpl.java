package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.SettingsData;
import com.kiefersink.admin.model.Settings;
import com.kiefersink.admin.repository.SettingsRepository;
import com.kiefersink.admin.service.SettingsService;
import com.kiefersink.admin.transform.TransformSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsServiceImpl implements SettingsService {
    @Autowired
    private SettingsRepository settingsRepository;
    private final TransformSettings transformSettings = new TransformSettings();
    //========================================================================================================//
    @Override
    public List<Settings> getAll() {
        return List.of();
    }
    //========================================================================================================//
    @Override
    public Settings get(String key) {
        SettingsData settingsData = settingsRepository.findById(key)
                .orElseThrow(() -> new RuntimeException(key + " settings not found"));

        return transformSettings.toModel(settingsData);
    }
    //========================================================================================================//
    @Override
    public Settings create(Settings settings) {
        SettingsData settingsData = new SettingsData();
        settingsData.setSettingKey(settings.getSettingKey());
        settingsData.setSettingValue(settings.getSettingValue());

        settingsRepository.save(settingsData);

        return settings;
    }
    //========================================================================================================//
    @Override
    public Settings update(String key, Settings settings) {
        SettingsData settingsData = settingsRepository.findById(key)
                .orElseThrow(() -> new RuntimeException("Settings not found"));

        settingsData.setSettingKey(settings.getSettingKey());
        settingsData.setSettingValue(settings.getSettingValue());

        settingsRepository.save(settingsData);
        return settings;
    }
    //========================================================================================================//
    @Override
    public void delete(String key) {

    }
}
