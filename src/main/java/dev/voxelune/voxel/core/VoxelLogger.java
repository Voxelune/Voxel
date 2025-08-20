package dev.voxelune.voxel.core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Centralized logging utility for Voxel.
 */
public class VoxelLogger {
    
    private final Logger bukkitLogger;
    private Level currentLevel = Level.INFO;

    public VoxelLogger(Logger bukkitLogger) {
        this.bukkitLogger = bukkitLogger;
    }

    public void setLevel(String level) {
        try {
            this.currentLevel = Level.parse(level.toUpperCase());
            bukkitLogger.setLevel(currentLevel);
        } catch (IllegalArgumentException e) {
            warning("Invalid log level: " + level + ", using INFO");
            this.currentLevel = Level.INFO;
        }
    }

    public void trace(String message) {
        log(Level.FINEST, message);
    }

    public void debug(String message) {
        log(Level.FINE, message);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void error(String message) {
        log(Level.SEVERE, message);
    }

    public void error(String message, Throwable throwable) {
        bukkitLogger.log(Level.SEVERE, message, throwable);
    }

    private void log(Level level, String message) {
        if (bukkitLogger.isLoggable(level)) {
            bukkitLogger.log(level, message);
        }
    }

    public boolean isDebugEnabled() {
        return bukkitLogger.isLoggable(Level.FINE);
    }

    public boolean isTraceEnabled() {
        return bukkitLogger.isLoggable(Level.FINEST);
    }
}