package com.loanProcessor.com.utils;



import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public final class Logging {

    private static Logger infoLog;
    private static Logger errorLog;
    private final transient Props props;
    private final String classNameString;

    public Logging(Props properties) {
        this.props = properties;
        classNameString = getClass().getCanonicalName();
        initializeLoggers();
    }

    private void initializeLoggers() {
        BasicConfigurator.configure();

        infoLog = Logger.getLogger("infoLog");
        errorLog = Logger.getLogger("errorLog");

        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("%d{yyyy MMM dd HH:mm:ss,SSS}: %p : %m%n");
        try {
            RollingFileAppender rfaInfoLog = new RollingFileAppender(layout, this.props.getInfoLogFile(), true);

            rfaInfoLog.setMaxFileSize("1024MB");
            rfaInfoLog.setMaxBackupIndex(4);

            RollingFileAppender rfaErrorLog = new RollingFileAppender(layout, this.props.getErrorLogFile(), true);

            rfaErrorLog.setMaxFileSize("1024MB");
            rfaErrorLog.setMaxBackupIndex(4);

            infoLog.addAppender(rfaInfoLog);
            errorLog.addAppender(rfaErrorLog);
        } catch (IOException ex) {
            System.err.println("Failed to initialize loggers... EXITING: " + ex.getMessage());

            System.exit(1);
        }
        infoLog.setLevel(Level.toLevel(this.props.getInfoLogLevel()));
        errorLog.setLevel(Level.toLevel(this.props.getErrorLogLevel()));

        info(Utilities.getLogPreString(classNameString) + "Loggers initialized...");
    }

    public void info(String message) {
        infoLog.info(message);
    }

    public void debug(String message) {
        infoLog.debug(message);
    }

    public void error(String message) {
        errorLog.error(message);
    }

    public void fatal(String message) {
        errorLog.fatal(message);
    }

    public static Logger getInfoLog() {
        return infoLog;
    }

    public static Logger getErrorLog() {
        return errorLog;
    }
}


