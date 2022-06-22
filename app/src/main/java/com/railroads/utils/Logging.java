package com.railroads.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
    static Logger logger;

    public Logging() throws IOException {

//        LogManager.getLogManager().readConfiguration(Logging.class.getResourceAsStream("logging.properties"));
        logger = Logger.getLogger(Logging.class.getName());
    }

    private static Logger getLogger() throws IOException {
        if (logger == null) {
            new Logging();
        }

        return logger;
    }

    public static void log(Level logLevel, String msg) throws IOException {
        getLogger().log(logLevel, msg);
    }
}
