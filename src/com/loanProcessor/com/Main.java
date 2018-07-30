package com.loanProcessor.com;

import com.loanProcessor.com.utils.Logging;
import com.loanProcessor.com.utils.Props;
import com.loanProcessor.com.utils.Utilities;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.util.*;


public class Main {

    private static transient Props props;
    private static transient Logging log;
    private static String classNameString;

    public static void main(String[] args) {

        //Instantiate Properties Files
        props = new Props();
        log = new Logging(props);
        classNameString = new Object(){}.getClass().getEnclosingMethod().getName();


        log.info(" ############# BEGIN PROCESSIONG ################# : " + props.getLoanCSVFile());

        log.info(" Retrieve File Name : " + props.getLoanCSVFile());

        log.info(Utilities.getLogPreString( classNameString) + "  " + props.getLoanCSVFile());

        List<Loan> loans = readLoansFromCSV(props.getLoanCSVFile());


        HashMap<Object, Object> orderedHashMap = new HashMap<Object, Object>();

        for (Loan ln : loans) {
            groupByTuple(orderedHashMap, ln);
        }

        log.info(Utilities.getLogPreString(classNameString) + " Begin Writing To File " );


        writeToOutputCSVfile(orderedHashMap);


        log.info(Utilities.getLogPreString(classNameString) + "  ############### END PROCESSING ############### " );


    }

    private static void writeToOutputCSVfile(HashMap<Object, Object> orderedHashMap) {

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(props.getLoanOutPutFile()))) {

            log.info(Utilities.getLogPreString(classNameString) + " Adding Headers " + props.getOutpuHeader() );
            String[] header = (props.getOutpuHeader().split(","));
            csvWriter.writeNext(header);

            log.info(Utilities.getLogPreString(classNameString) + " Writing to File:  " + props.getLoanOutPutFile());
            for (Object key : orderedHashMap.keySet()) {

                Tuple tuple = (Tuple) orderedHashMap.get(key);
                String[] keyParts = String.valueOf(key).split(":");
                String[] row = new String[]{keyParts[0], keyParts[1], keyParts[2], String.valueOf(tuple.getCount()), String.valueOf(tuple.getSum())};

                csvWriter.writeNext(row);

                log.info(Utilities.getLogPreString(classNameString) + " Added new row with values : [  " + Arrays.asList(row).toString() +  " ]");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Main() {


    }

    private static HashMap<Object, Object> groupByTuple(HashMap<Object, Object> orderedHashMap, Loan ln) {
        String mapKey = "";
        String splitMonth = splitDate(ln.getPeriod());


        int count = 0;
        double sum;
        double amount = ln.getAmount();


        mapKey = ln.network + ":" + splitMonth + ":" + ln.getProduct();


        log.info(Utilities.getLogPreString(classNameString) + " Tuple Key Pair [ " + mapKey + " ]");

        if (orderedHashMap.get(mapKey) == null) {
            count++;
            orderedHashMap.put(mapKey, new Tuple(count, amount));

            log.info(Utilities.getLogPreString(classNameString) + " Added New Tuple to Hash Map [ " + orderedHashMap.toString()  + " ]");


        } else {
            log.info(Utilities.getLogPreString(classNameString) + " Found Existing Key in Hash Map With Key [ " + mapKey + " ]");
            Tuple tuple = (Tuple) (orderedHashMap.get(mapKey));

            count = tuple.getCount();
            log.info(Utilities.getLogPreString(classNameString) + " New Count: [" + tuple.getCount() + " ] New Sum [ " + tuple.sum + " ]");

            count++;
            sum = tuple.getSum() + amount;

            orderedHashMap.put(mapKey, new Tuple(count, sum));


        }

        return orderedHashMap;
    }


    private static List<Loan> readLoansFromCSV(String resourcePath) {
        List<Loan> loans = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(resourcePath), ',', '\'', 1)) {
            //Parse CSV
            String[] line;
            log.info(Utilities.getLogPreString(classNameString) + " Parsing  Loans ");

            while ((line = csvReader.readNext()) != null) {

                Loan loan = createLoan(line);
                loans.add(loan);
            }

        } catch (FileNotFoundException e) {
            log.error(Utilities.getLogPreString(classNameString) + " File Not Found Exception  : ###### EXITING ########## " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {

            log.error(Utilities.getLogPreString(classNameString)  + "IO Exception  : ######  EXITING  ######" + e.getMessage());
            System.exit(1);
        }
        return loans;
    }

    private static Loan createLoan(String[] values) {
        String msisdn = values[0];
        String network = values[1];
        String period = values[2];
        String product = values[3];
        double amount = Double.valueOf(values[4]);

        return new Loan(msisdn, network, period, product, amount);

    }


    private static String splitDate(String s) {

        String[] parts = s.split("-");

        s = parts[1];

        return s;
    }

    static class Tuple {

        int count;
        double sum;

        public Tuple(int count, double sum) {
            this.count = count;
            this.sum = sum;

        }

        public Tuple(Object s) {


        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getSum() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }


        @Override
        public String toString() {
            return " [count=" + count + ", sum=" + sum + "]";
        }


    }
}









































