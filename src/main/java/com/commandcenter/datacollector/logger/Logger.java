/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commandcenter.datacollector.logger;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

/**
 * This class is just logger, that logs using log4j library with the class name
 *
 * @author rmw
 */
public class Logger {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();

    public static void LogInfo(String obj, String name) {
        // logger = Logger.getLogger(obj);
        logger.info(name);
    }

    public static void LogWarn(String obj, String name) {
        logger = org.apache.log4j.Logger.getLogger(obj);
        logger.warn(name);
    }

    public static void LogDebug(String obj, String name) {
        logger = org.apache.log4j.Logger.getLogger(obj);
        logger.debug(name);
    }

    public static void LogTrace(String obj, String name) {
        logger = org.apache.log4j.Logger.getLogger(obj);
        logger.trace(name);
    }

    public static void LogError(String obj, String name) {
        logger = org.apache.log4j.Logger.getLogger(obj);
        logger.error(name);
    }

    public static void LogException(String obj, String name, Exception ex) {
        logger = org.apache.log4j.Logger.getLogger(obj);
        logger.error(name, ex);
    }

    public static void LogFatal(String obj, String name) {
        logger = org.apache.log4j.Logger.getLogger(obj);
        logger.fatal(name);
    }

    public static void setLogLevel(String logLevel) {
        Level level = Level.toLevel(logLevel, LogManager.getRootLogger().getLevel());
        LogManager.getRootLogger().setLevel(level);
    }
}