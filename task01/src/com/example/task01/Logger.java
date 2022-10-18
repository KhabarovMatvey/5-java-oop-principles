package com.example.task01;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class Logger {
    private final String name;
    private Level level;

    private static final Map<String, Logger> loggers = new HashMap<>();

    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private Logger(String name) {
        this.name = name;
        this.level = Level.INFO;
    }

    public static Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, Logger::new);
    }

    public String getName() {
        return name;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    private boolean shouldLog(Level messageLevel) {
        return messageLevel.ordinal() >= level.ordinal();
    }

    private void formatAndPrint(Level messageLevel, String message) {
        if (!shouldLog(messageLevel)) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String date = now.format(dateFormatter);
        String time = now.format(timeFormatter);

        String formattedMessage = String.format("[%s] %s %s %s - %s",
                messageLevel, date, time, name, message);

        System.out.println(formattedMessage);
    }

    public void debug(String message) {
        formatAndPrint(Level.DEBUG, message);
    }

    public void debug(String template, Object... args) {
        String message = MessageFormat.format(template, args);
        formatAndPrint(Level.DEBUG, message);
    }

    public void info(String message) {
        formatAndPrint(Level.INFO, message);
    }

    public void info(String template, Object... args) {
        String message = MessageFormat.format(template, args);
        formatAndPrint(Level.INFO, message);
    }

    public void warning(String message) {
        formatAndPrint(Level.WARNING, message);
    }

    public void warning(String template, Object... args) {
        String message = MessageFormat.format(template, args);
        formatAndPrint(Level.WARNING, message);
    }

    public void error(String message) {
        formatAndPrint(Level.ERROR, message);
    }

    public void error(String template, Object... args) {
        String message = MessageFormat.format(template, args);
        formatAndPrint(Level.ERROR, message);
    }

    public void log(Level messageLevel, String message) {
        formatAndPrint(messageLevel, message);
    }

    public void log(Level messageLevel, String template, Object... args) {
        String message = MessageFormat.format(template, args);
        formatAndPrint(messageLevel, message);
    }
}