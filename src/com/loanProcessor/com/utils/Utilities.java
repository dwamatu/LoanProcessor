package com.loanProcessor.com.utils;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Utilities {

    private static transient Props props;
    private static String classNameString;

    /**
     * Utilities
     *
     * @param properties
     */
    public Utilities(Props properties) {
        props = properties;
        classNameString = getClass().getCanonicalName();
    }

    public static String getXMLProperty(Logging logging, String path, String key) {
        String response = "";
        try {
            Properties properties = new Properties();
            FileInputStream propsStream = new FileInputStream(path);
            properties.loadFromXML(propsStream);

            response = properties.getProperty(key);

            propsStream.close();
        } catch (IOException e) {
            logging.fatal(getLogPreString(classNameString) + " getXMLProperty: Error " + e.getMessage()
            );
        }
        return response;
    }




    public static String getLogPreString(String className) {
        return "Jumo Loan Processor | " + className + " | ";
    }
}
