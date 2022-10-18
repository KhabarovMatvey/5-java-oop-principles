package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("main");

        logger.setLevel(Logger.Level.DEBUG);

        logger.debug("Это отладочное сообщение");
        logger.info("Приложение запущено");
        logger.warning("Что-то пошло не так, но это не критично");
        logger.error("Произошла критическая ошибка!");

        String userName = "Иван";
        int userId = 123;
        logger.info("Пользователь {0} (ID: {1}) вошел в систему", userName, userId);

        Logger sameLogger = Logger.getLogger("main");
        System.out.println("Один и тот же логгер: " + (logger == sameLogger));
    }
}