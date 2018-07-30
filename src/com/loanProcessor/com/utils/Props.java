package com.loanProcessor.com.utils;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public final class Props {

    private static final String PROPS_FILE = "conf/Jumoconfigs.xml";

    private final transient List<String> loadErrors;
    private transient String infoLogLevel = "INFO";
    private transient String errorLogLevel = "ERROR";
    private transient String infoLogFile;
    private transient String errorLogFile;

    private transient String loanCSVFile;
    private transient String loanOutPutFile;
    private transient String outPuTheaders;


    public Props() {
        this.loadErrors = new ArrayList(0);
        loadProperties(PROPS_FILE);
    }


    private void loadProperties(String propsFile) {
        try {
            FileInputStream propsStream = new FileInputStream(propsFile);
            Throwable localThrowable3 = null;
            try {
                Properties props = new Properties();
                props.loadFromXML(propsStream);
                String error1 = "ERROR: %s is <= 0 or may not have been set";
                String error2 = "ERROR: %s may not have been set";


                this.infoLogLevel = props.getProperty("InfoLogLevel");
                if (getInfoLogLevel().isEmpty()) {
                    this.loadErrors.add(String.format(error2,
                            new Object[]{"InfoLogLevel"}));
                }
                this.errorLogLevel = props.getProperty("ErrorLogLevel");
                if (getErrorLogLevel().isEmpty()) {
                    this.loadErrors.add(String.format(error2,
                            new Object[]{"ErrorLogLevel"}));
                }
                this.infoLogFile = props.getProperty("InfoLogFile");
                if (getInfoLogFile().isEmpty()) {
                    this.loadErrors.add(String.format(error2,
                            new Object[]{"InfoLogFile"}));
                }
                this.errorLogFile = props.getProperty("ErrorLogFile");
                if (getErrorLogFile().isEmpty()) {
                    this.loadErrors.add(String.format(error2,
                            new Object[]{"ErrorLogFile"}));
                }

                this.loanCSVFile = props.getProperty("loanCSVFile");
                if (getErrorLogFile().isEmpty()) {
                    this.loadErrors.add(String.format(error2,
                            new Object[]{"loanCSVFile"}));
                }

                this.loanOutPutFile = props.getProperty("loanOutPutFile");
                if (getErrorLogFile().isEmpty()) {
                    this.loadErrors.add(String.format(error2,
                            new Object[]{"loanOutPutFile"}));
                }
                this.outPuTheaders = props.getProperty("outPuTheader");
                if (this.outPuTheaders.isEmpty()) {
                    getLoadErrors().add(String.format(error1, new Object[]{"outPuTheader"}));
                }


            } catch (IOException | NumberFormatException localThrowable1) {
                localThrowable3 = localThrowable1;
                throw localThrowable1;
            } finally {
                if (propsStream != null) {
                    if (localThrowable3 != null) {
                        try {
                            propsStream.close();
                        } catch (IOException localThrowable2) {
                            localThrowable3.addSuppressed(localThrowable2);
                        }
                    } else {
                        propsStream.close();
                    }
                }
            }
        } catch (NumberFormatException ne) {
            System.err.println("Exiting. "
                    + "String value found, Integer is required: " + ne
                    .getMessage());
            System.exit(1);
        } catch (FileNotFoundException ne) {
            System.err.println("Exiting. "
                    + "Could not find the properties file: " + ne
                    .getMessage());
            System.exit(1);
        } catch (IOException ioe) {
            System.err.println("Exiting."
                    + " Failed to load system properties: " + ioe
                    .getMessage());
            System.exit(1);
        }
    }


    public String getInfoLogLevel() {
        return this.infoLogLevel;
    }

    public String getErrorLogLevel() {
        return this.errorLogLevel;
    }

    public String getInfoLogFile() {
        return this.infoLogFile;
    }

    public String getErrorLogFile() {
        return this.errorLogFile;
    }


    public String getLoanCSVFile() {
        return this.loanCSVFile;
    }

    public String getLoanOutPutFile() {
        return this.loanOutPutFile;
    }
    public List<String> getLoadErrors() {
        return Collections.unmodifiableList(this.loadErrors);
    }

    public String getOutpuHeader() {
        return this.outPuTheaders;
    }
}
