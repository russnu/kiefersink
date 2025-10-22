package com.kiefersink.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Settings")
public class SettingsData {
    @Id
    private String settingKey;
    private String settingValue;
}
