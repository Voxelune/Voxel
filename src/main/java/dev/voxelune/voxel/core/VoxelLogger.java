package dev.voxelune.voxel.core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enhanced logging utility for the Voxel Framework.
 * 
 * Provides structured logging with different levels and developer-friendly
 * output formatting.
 */
public class VoxelLogger {
    
    private final Logger bukkitLogger;
    private Level currentLevel = Level.INFO;
    private boolean developerMode = false;

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
    
    public void setDeveloperMode(boolean developerMode) {
        this.developerMode = developerMode;
    }

    public void trace(String message) {
        log(Level.FINEST, "[TRACE] " + message);
    }

    public void debug(String message) {
        if (developerMode) {
            log(Level.FINE, "[DEBUG] " + message);
        }
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warning(String message) {
        log(Level.WARNING, "[WARN] " + message);
    }

    public void error(String message) {
        log(Level.SEVERE, "[ERROR] " + message);
    }

    public void error(String message, Throwable throwable) {
        bukkitLogger.log(Level.SEVERE, "[ERROR] " + message, throwable);
    }
    
    public void framework(String message) {
        log(Level.INFO, "[FRAMEWORK] " + message);
    }
    
    public void developer(String message) {
        if (developerMode) {
            log(Level.INFO, "[DEV] " + message);
        }
    }
    
    public void performance(String message) {
        if (developerMode) {
            log(Level.INFO, "[PERF] " + message);
        }
    }

    private void log(Level level, String message) {
        if (bukkitLogger.isLoggable(level)) {
            bukkitLogger.log(level, message);
        }
    }

    public boolean isDebugEnabled() {
        return developerMode && bukkitLogger.isLoggable(Level.FINE);
    }

    public boolean isTraceEnabled() {
        return developerMode && bukkitLogger.isLoggable(Level.FINEST);
    }
    
    public boolean isDeveloperMode() {
        return developerMode;
    }
}