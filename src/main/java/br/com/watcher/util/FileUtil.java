package br.com.watcher.util;

import java.io.File;

public class FileUtil {

    public static void createDirectory(String folder) {
        File file = new File(folder);
        if (!file.exists())
            file.mkdir();
    }

    public static boolean validExtension(String fileName, String expectedExtension) {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        return expectedExtension.equalsIgnoreCase(extension);
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists())
            file.delete();
    }

    public static String generateOutputFileName(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        return fileName.replace(extension, ".done".concat(extension));
    }
}
