package com.kiefersink.admin.serviceimpl;

import com.kiefersink.admin.entity.OfferingData;
import com.kiefersink.admin.entity.SettingsData;
import com.kiefersink.admin.model.Offering;
import com.kiefersink.admin.model.Settings;
import com.kiefersink.admin.repository.SettingsRepository;
import com.kiefersink.admin.service.SettingsService;
import com.kiefersink.admin.transform.TransformSettings;
import com.kiefersink.admin.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SettingsServiceImpl implements SettingsService {
    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private ImageUtils imageUtils;
    private final TransformSettings transformSettings = new TransformSettings();
    //========================================================================================================//
    @Override
    public List<Settings> getAll() {
        List<SettingsData> settingsDataList = new ArrayList<>();
        settingsRepository.findAll().forEach(settingsDataList::add);

        List<Settings> settings = new ArrayList<>();
        for (SettingsData data : settingsDataList) {
            Settings setting = transformSettings.toModel(data);
            settings.add(setting);
        }
        return settings;
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
    public Settings update(String key, Settings settings, MultipartFile image) {
        SettingsData existing = settingsRepository.findById(key)
                .orElseThrow(() -> new RuntimeException("Settings not found"));
        if ("logoUrl".equals(key) ){
            try{
                if (image != null && !image.isEmpty()) {
                    String savedFileName = imageUtils.saveImage(image, "settings-images", "logo");
                    settings.setSettingValue(savedFileName);
                } else {
                    settings.setSettingValue(existing.getSettingValue());
                }
            } catch(IOException e){
                throw new RuntimeException("Failed to save image file", e);
            }

            existing.setSettingValue(settings.getSettingValue());
        } else {
            existing.setSettingValue(settings.getSettingValue());
        }
        SettingsData saved = settingsRepository.save(existing);
        return transformSettings.toModel(saved);
    }
    //========================================================================================================//
    @Override
    public void delete(String key) {

    }
}
