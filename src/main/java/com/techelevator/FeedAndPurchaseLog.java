package com.techelevator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FeedAndPurchaseLog {

//    static String logfilePath = "capstone/src/main/java/com/techelevator/Log.txt";
      static String logfilePath = "src/main/java/com/techelevator/Log.txt";
      //This is where the application log is created
    public static void logFeedMoney() throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
        Date date = new Date();
        File logFile = new File(logfilePath);
        boolean isFileCreated = logFile.createNewFile();
        try (PrintWriter logDataOutput = new PrintWriter(new FileOutputStream(logFile, true))) {

            logDataOutput.println(formatter.format(date) + " FEED MONEY: " + PurchaseSubMenuMoneyFeed.doubleMoneyInput + " " + PurchaseSubMenuMoneyFeed.totalTendered  );
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open the log file for writing.");
        }
    }

    public static void logGiveChange(double message ) throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
        Date date = new Date();
        File logFile = new File(logfilePath);
        boolean isFileCreated = logFile.createNewFile();
        try (PrintWriter logDataOutput = new PrintWriter(new FileOutputStream(logFile, true))) {
            logDataOutput.println(formatter.format(date) + " GIVE CHANGE: " + message + " " + "0.00" );
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open the log file for writing.");
        }
    }

    public static void logPurchase(HashMap<String, List<String>> hash, String userInput, double current ) throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
        Date date = new Date();
        File logFile = new File(logfilePath);
        boolean isFileCreated = logFile.createNewFile();
        try (PrintWriter logDataOutput = new PrintWriter(new FileOutputStream(logFile, true))) {
            int convertDouble = (int)Math.round(current * 100);
            double convertIntToDoub = convertDouble / 100;
             convertDouble = (int)Math.round(PurchaseSubMenuMoneyFeed.totalTendered * 100);
            double convertPurchase = convertDouble / 100;
            logDataOutput.println(formatter.format(date) + " " + hash.get(userInput).get(0) + " " + userInput + " " +  convertIntToDoub + " " + convertPurchase );
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open the log file for writing.");
        }
    }

    public static void logExitProgram() throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
        Date date = new Date();
        File logFile = new File(logfilePath);
        boolean isFileCreated = logFile.createNewFile();
        try (PrintWriter logDataOutput = new PrintWriter(new FileOutputStream(logFile, true))) {
            logDataOutput.println(formatter.format(date) + " Program has been terminated.");
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open the log file for writing.");
        }
    }
}