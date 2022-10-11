package com.techelevator;

import java.text.DecimalFormat;
import java.util.Scanner;
public class PurchaseSubMenuMoneyFeed {
//since we made it static I had to use a method that converted my decimal into 2 decimal places that was also static

    static double doubleMoneyInput = 0;
    static double totalTendered = 0;
    //beginning of setters and getters
    public static double getTotalTendered() {
        return totalTendered;
    }

    public static void setTotalTendered(double amount) {
        totalTendered -= amount;
    }
    //*****************************************************
    //this was done for testing purposes Didn't want to change the whole method from void to string
    public static String returnChangeStr;
    //******************************************************
//end of setters and getters
    public static void tenderMoney() {
        // Asks for customer input regarding banknote tendered, verifies that it is a valid denomination, and adds value of banknote to stored tendered total.
        Scanner userInput = new Scanner(System.in);
        System.out.println("\nPlease input a valid dollar note denomination: ");
        String moneyInput = userInput.nextLine();
        try {
            doubleMoneyInput = Double.parseDouble(moneyInput);
            if (doubleMoneyInput == 1 || doubleMoneyInput == 2 || doubleMoneyInput == 5 || doubleMoneyInput == 10 || doubleMoneyInput == 20 || doubleMoneyInput == 100)
            {
                totalTendered += doubleMoneyInput;
            } else {
                System.err.println("Denomination invalid or too large.");
            }
        }
        catch (NumberFormatException e) {
            System.err.println("Invalid input format.");
        }
    }

    public static void returnChange() {
        // Returns any remaining tendered money in change.
        if (totalTendered > 0) {

            int totalTenderedInteger = (int) (Math.round(totalTendered * 100));

            int numberOfQuarters = totalTenderedInteger / 25;
            int remainderAfterQuarters = totalTenderedInteger - (numberOfQuarters * 25);

            int numberOfDimes = remainderAfterQuarters / 10;
            int remainderAfterDimes = remainderAfterQuarters - (numberOfDimes * 10);

            int numberOfNickels = remainderAfterDimes / 5;
            returnChangeStr = "\nQuarters Returned: " + numberOfQuarters + "\nDimes Returned: " + numberOfDimes + "\nNickels Returned: " + numberOfNickels;
            System.out.println(returnChangeStr);

            totalTendered = 0;
        }
    }

    //This was only use for testing purposes, since it was hard to call a void and we didn't want to change all our
    //code to fit String methods, we decided to add this method so we can test our code
    public static void setTotalTenderedForTest(double amount){
        totalTendered = amount;
    }
}