package com.kiefersink.admin.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUtils {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveImage(MultipartFile image, String folder, String fileName) throws IOException {
        if (image == null || image.isEmpty()) {
            return null;
        }

        Path dirPath = Paths.get(uploadDir, folder);

        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        String extension = GetFileExtension.fromFilename(image.getOriginalFilename());
        String finalFileName = fileName + extension;

        Path filePath = dirPath.resolve(finalFileName);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return finalFileName;
    }
    public void deleteImage(String fileName, String folder) {
        try {
            Path filePath = Paths.get(uploadDir, folder, fileName);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            System.err.println("Failed to delete image file: " + e.getMessage());
        }
    }

}
