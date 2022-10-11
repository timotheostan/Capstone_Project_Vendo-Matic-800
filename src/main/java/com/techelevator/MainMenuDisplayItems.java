package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainMenuDisplayItems {
private HashMap<String, List<String>> hashSelection = new HashMap<>();
private String remainingAmount ="5" ;
    //allows us to call for the hashmap using a getter
    public HashMap<String, List<String>> getHashSelection() {
        return hashSelection;
    }
//end of getter
    /*
 Decided to create a constructor that way there we can refer to the hashmap at any point and don't have to worry about calling the file, since it's
 called when the class is instantiated
     */
    //********************************
    //this was used for testing
    public String dispenseMessageStr;
    //********************************

    public MainMenuDisplayItems() {
//        String itemOptionsPath = "F:\\MA_Programming_Jan_2022_Cohort\\repos\\module-1-capstone\\capstone\\vendingmachine.csv";
        String itemOptionsPath = "C:\\Users\\hermm\\Desktop\\pair_programming\\module-1-capstone\\capstone\\vendingmachine.csv";
        File itemOptionsFile = new File(itemOptionsPath);
        try (Scanner itemOptionsInput = new Scanner(itemOptionsFile)) {
            while (itemOptionsInput.hasNextLine()) {
                String[] array = itemOptionsInput.nextLine().split("\\|");
                List<String> list = new ArrayList<>();
                //converts the remainder of the list into an array list.
                list = Arrays.asList(array[1], array[2], array[3], getRemainingAmount());
                //we want to use a hash so it's easier to select a selection with just the keys
                hashSelection.put(array[0], list);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file does not exist.");
        }
    }
    //allows yous to print what the list currently has in the hash
    public void printList(){
        System.out.println("\n------------------------------------------------------");
        System.out.println("****Hermmans*** Vending Machine Items ***Timotheos****");
        System.out.println("------------------------------------------------------");

        System.out.printf("|%-4s|%-20s|%-4s|%-7s|%-12s|" ," key"   , "   product name " ,"value", " type" ,"    Amount"    );

        System.out.println("\n------------------------------------------------------");
        for(Map.Entry<String, List<String> > x : hashSelection.entrySet()){
     System.out.printf("|%-4s|%-20s|%-4s|%-7s|%-12s|" ," " + x.getKey()  , "  " + x.getValue().get(0)  ,"$" + x.getValue().get(1) ," "+ x.getValue().get(2) ,"   "+ x.getValue().get(3)    );
            System.out.println("\n______________________________________________________");

        }
        System.out.println("\n--- End of Item List ---");
    }
    //allows you to return the speech that you want to get the dispensing message
    public void dispensingMessage(String key){

        String typeOfSnack = getHashSelection().get(key).get(2);
        if(typeOfSnack.equals("Chip")){ dispenseMessageStr = "Crunch Crunch, Yum!";
            System.out.println(dispenseMessageStr);}
        else if(typeOfSnack.equals("Candy")){dispenseMessageStr ="Munch Munch, Yum!";
            System.out.println(dispenseMessageStr);}
        else if(typeOfSnack.equals("Drink")){
            dispenseMessageStr = "Glug Glug, Yum!";
            System.out.println(dispenseMessageStr);}
        else if(typeOfSnack.equals("Gum")){dispenseMessageStr = "Chew Chew, Yum!";
            System.out.println(dispenseMessageStr);}
    }


    // Find a way to retrieve remaining quantities of each item from PurchaseSubMenuProductSelection.
public String getRemainingAmount(){
        return remainingAmount;
}

//this allows us to take items away 1 item at a time
    public  void setRemainingAmount(String keySelect) {
        String amountInString = getHashSelection().get(keySelect).get(3);
        try {
            int amount = Integer.parseInt(amountInString);
            if (amount -1 > 0) {
                remainingAmount = String.valueOf(amount - 1);
                getHashSelection().get(keySelect).set(3, remainingAmount) ;
            } else {
                getHashSelection().get(keySelect).set(3, "SOLD OUT");
            }
        }catch (NumberFormatException e){
            getHashSelection().get(keySelect).set(3, "SOLD OUT");
            System.out.println("We already sold out of that!");
        }

    }
}