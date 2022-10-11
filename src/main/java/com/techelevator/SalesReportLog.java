package com.techelevator;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesReportLog {
//   static String pathToSalesLogDir = "capstone/src/main/java/com/techelevator/saleslogs";
   static String pathToSalesLogDir = "src/main/java/com/techelevator/saleslogs";

    static File salesLogDir = new File(pathToSalesLogDir);

    public static void log(String message) throws IOException {
        // Create the saleslogs directory if it does not exist
        if (!salesLogDir.exists()) {
            salesLogDir.mkdirs();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_h_mm_ss_a");
        Date date = new Date();

        // Create a new log file with the date and time as its name and print relevant sales log entries.
        String stringDate = pathToSalesLogDir + "/" + formatter.format(date) +".log";
        String auditPath = stringDate;
        File logFile = new File(auditPath);
        try (PrintWriter log = new PrintWriter(new FileOutputStream(logFile,true))) {
            log.println(message);
        }  catch (FileNotFoundException e) {
            System.err.println("Cannot open the file for writing.");
        }
    }
}