package com.kiefersink.admin.controller;

import com.kiefersink.admin.model.Settings;
import com.kiefersink.admin.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/settings")
@RestController
public class SettingsController {
    @Autowired
    private SettingsService service;
    //========================================================================================================//
    @GetMapping
    public List<Settings> getAllSettings() {
        return service.getAll();
    }
    //========================================================================================================//
    @GetMapping("/{setting_key}")
    public Settings getSettings(@PathVariable("setting_key") String settingKey) {
        return service.get(settingKey);
    }
    //========================================================================================================//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Settings createSettings(@RequestBody Settings settings) {
        return service.create(settings);
    }
    //========================================================================================================//
    @PutMapping
    public Settings updateSettings(@PathVariable("setting_key") String settingKey, @RequestBody Settings settings) {
        return service.update(settingKey, settings);
    }
    //========================================================================================================//
    @DeleteMapping("/{setting_key}")
    public void deleteSettings(@PathVariable("setting_key") String settingKey) {
        service.delete(settingKey);
    }
    //========================================================================================================//

}
