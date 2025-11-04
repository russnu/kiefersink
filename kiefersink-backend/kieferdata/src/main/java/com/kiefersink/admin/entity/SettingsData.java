package com.kiefersink.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "settings")
public class SettingsData {
    @Id
    private String settingKey;
    private String settingValue;
}
