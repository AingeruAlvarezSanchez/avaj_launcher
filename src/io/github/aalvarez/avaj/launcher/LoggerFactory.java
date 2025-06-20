package io.github.aalvarez.avaj.launcher;

import java.util.logging.Logger;

public class LoggerFactory {
    private static final LoggerFactory factory = new LoggerFactory();

    private LoggerFactory() {
    }

    public static LoggerFactory
    getInstance() {
        return factory;
    }

    public Logger
    getLogger(Class<?> clazz) {
        return Logger.getLogger(clazz.getName());
    }
}
