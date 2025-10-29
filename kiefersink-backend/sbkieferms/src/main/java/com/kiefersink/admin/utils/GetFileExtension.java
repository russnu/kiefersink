package com.kiefersink.admin.utils;

public class GetFileExtension {
    public static String fromFilename(String filename) {
        if (filename != null && filename.lastIndexOf('.') != -1 && filename.lastIndexOf('.') < filename.length() - 1) {
            return filename.substring(filename.lastIndexOf('.')).toLowerCase();
        }
        return "";
    }
}
