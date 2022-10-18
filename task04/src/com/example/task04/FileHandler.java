package com.example.task04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler implements MessageHandler {
    private final String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;

        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    @Override
    public void handle(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(message);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл " + filePath + ": " + e.getMessage());
        }
    }
}