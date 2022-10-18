package com.example.task04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String basePath;
    private final ChronoUnit rotationUnit;
    private String currentFilePath;
    private LocalDateTime currentRotationTime;

    public RotationFileHandler(String basePath, ChronoUnit rotationUnit) {
        this.basePath = basePath;
        this.rotationUnit = rotationUnit;
        updateRotationTime();
    }

    private void updateRotationTime() {
        currentRotationTime = LocalDateTime.now().truncatedTo(rotationUnit);
        String timestamp = currentRotationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        currentFilePath = basePath.replace("{timestamp}", timestamp);

        File file = new File(currentFilePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    private boolean needsRotation() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(rotationUnit);
        return !now.equals(currentRotationTime);
    }

    @Override
    public void handle(String message) {
        if (needsRotation()) {
            updateRotationTime();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(currentFilePath, true))) {
            writer.println(message);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл " + currentFilePath + ": " + e.getMessage());
        }
    }
}