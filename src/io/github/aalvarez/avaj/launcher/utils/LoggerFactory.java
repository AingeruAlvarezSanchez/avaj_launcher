package io.github.aalvarez.avaj.launcher.utils;

import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
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
        Logger logger = Logger.getLogger(clazz.getName());

        Handler[] handlers = logger.getHandlers();
        Arrays.stream(handlers).forEach(logger::removeHandler);
        ConsoleHandler handler = new ConsoleHandler() {
            @Override
            protected synchronized void setOutputStream(java.io.OutputStream out) throws SecurityException {
                super.setOutputStream(System.out);
            }
        };

        logger.addHandler(handler);
        logger.setUseParentHandlers(false);
        return logger;
    }
}
