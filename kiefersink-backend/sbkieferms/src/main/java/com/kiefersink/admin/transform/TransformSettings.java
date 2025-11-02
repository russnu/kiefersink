package com.kiefersink.admin.transform;

import com.kiefersink.admin.entity.SettingsData;
import com.kiefersink.admin.model.Settings;

public class TransformSettings implements Transform<Settings, SettingsData>{

    @Override
    public Settings toModel(SettingsData data) {
        Settings settings = new Settings();
        settings.setSettingKey(data.getSettingKey());

        if ("logoUrl".equals(settings.getSettingKey())){
            settings.setSettingValue("http://localhost:8080/uploads/settings-images/" + data.getSettingValue());
        } else {
            settings.setSettingValue(data.getSettingValue());
        }

        return settings;
    }

    @Override
    public SettingsData toData(Settings model) {
        SettingsData settingsData = new SettingsData();
        settingsData.setSettingKey(model.getSettingKey());
        settingsData.setSettingValue(model.getSettingValue());

        return settingsData;
    }
}
