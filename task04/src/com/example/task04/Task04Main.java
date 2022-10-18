package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("TestLogger");
        logger.setLevel(Logger.Level.DEBUG);

        logger.addHandler(new ConsoleHandler());
        logger.addHandler(new FileHandler("application.log"));
        logger.addHandler(new RotationFileHandler("logs/app_{timestamp}.log", ChronoUnit.HOURS));

        MemoryHandler memoryHandler = new MemoryHandler(new FileHandler("buffered.log"), 3);
        logger.addHandler(memoryHandler);

        logger.debug("Отладочное сообщение");
        logger.info("Информационное сообщение");
        logger.warning("Предупреждение");
        logger.error("Ошибка в системе");

        logger.info("Пользователь {0} вошел в систему", "Иван");
        logger.error("Ошибка при обработке запроса {0}", "GET /api/data");

        System.out.println("Сообщений в буфере: " + memoryHandler.getBufferSize());
        memoryHandler.flush();
        System.out.println("Сообщений в буфере после flush: " + memoryHandler.getBufferSize());
    }
}