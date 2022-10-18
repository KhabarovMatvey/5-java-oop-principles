package com.example.task04;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {
    private final String name;
    private Level level;
    private final List<MessageHandler> handlers = new ArrayList<>();

    private static final Map<String, Logger> loggers = new HashMap<>();

    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private Logger(String name) {
        this.name = name;
        this.level = Level.INFO; // Уровень по умолчанию
    }

    public static Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, Logger::new);
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler) {
        handlers.remove(handler);
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

    private String formatMessage(Level messageLevel, String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String date = now.format(dateFormatter);
        String time = now.format(timeFormatter);

        return String.format("%s %s %s %s - %s",
                messageLevel, date, time, name, message);
    }

    private void sendToHandlers(String formattedMessage) {
        for (MessageHandler handler : handlers) {
            handler.handle(formattedMessage);
        }
    }

    public void debug(String message) {
        if (shouldLog(Level.DEBUG)) {
            String formattedMessage = formatMessage(Level.DEBUG, message);
            sendToHandlers(formattedMessage);
        }
    }

    public void debug(String template, Object... args) {
        if (shouldLog(Level.DEBUG)) {
            String message = MessageFormat.format(template, args);
            String formattedMessage = formatMessage(Level.DEBUG, message);
            sendToHandlers(formattedMessage);
        }
    }

    public void info(String message) {
        if (shouldLog(Level.INFO)) {
            String formattedMessage = formatMessage(Level.INFO, message);
            sendToHandlers(formattedMessage);
        }
    }

    public void info(String template, Object... args) {
        if (shouldLog(Level.INFO)) {
            String message = MessageFormat.format(template, args);
            String formattedMessage = formatMessage(Level.INFO, message);
            sendToHandlers(formattedMessage);
        }
    }

    public void warning(String message) {
        if (shouldLog(Level.WARNING)) {
            String formattedMessage = formatMessage(Level.WARNING, message);
            sendToHandlers(formattedMessage);
        }
    }

    public void warning(String template, Object... args) {
        if (shouldLog(Level.WARNING)) {
            String message = MessageFormat.format(template, args);
            String formattedMessage = formatMessage(Level.WARNING, message);
            sendToHandlers(formattedMessage);
        }
    }

    public void error(String message) {
        if (shouldLog(Level.ERROR)) {
            String formattedMessage = formatMessage(Level.ERROR, message);
            sendToHandlers(formattedMessage);
        }
    }

    public void error(String template, Object... args) {
        if (shouldLog(Level.ERROR)) {
            String message = MessageFormat.format(template, args);
            String formattedMessage = formatMessage(Level.ERROR, message);
            sendToHandlers(formattedMessage);
        }
    }

    public void log(Level messageLevel, String message) {
        if (shouldLog(messageLevel)) {
            String formattedMessage = formatMessage(messageLevel, message);
            sendToHandlers(formattedMessage);
        }
    }

    public void log(Level messageLevel, String template, Object... args) {
        if (shouldLog(messageLevel)) {
            String message = MessageFormat.format(template, args);
            String formattedMessage = formatMessage(messageLevel, message);
            sendToHandlers(formattedMessage);
        }
    }
}