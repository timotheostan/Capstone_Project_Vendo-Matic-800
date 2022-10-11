package com.techelevator;

import com.techelevator.view.Menu;

import java.beans.FeatureDescriptor;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_HIDDEN = "HIDDEN";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_HIDDEN };
	//DECIDED TO ADD THIS TO THE VENDINGMACHINECLI SO THAT IT IS ALL IN ONE PLACE INSTEAD OF REFERENCING THE SAME THING BUT IN DIFFERENT FILES
	private static final String PURCHASE_SUB_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_SUB_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_SUB_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_SUB_MENU_OPTIONS = { PURCHASE_SUB_MENU_OPTION_FEED_MONEY, PURCHASE_SUB_MENU_OPTION_SELECT_PRODUCT, PURCHASE_SUB_MENU_OPTION_FINISH_TRANSACTION };
public	MainMenuDisplayItems mainMenuDisplayItems = new MainMenuDisplayItems();

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws IOException {

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			StringBuilder blankTilFilled = new StringBuilder();
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				mainMenuDisplayItems.printList();
			}
			//Prompts up a selection to purchase, put more money or finalize a transaction.
			else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchaseSubMenuOption();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("\nThank you for your patronage.");
			//log
				FeedAndPurchaseLog.logExitProgram();
				System.exit(1);
			}


			//hidden step 10
			else if(choice.equals(MAIN_MENU_OPTION_HIDDEN)){
				double totalsoldAmount = 0;
				for(Map.Entry<String, List<String>> x : mainMenuDisplayItems.getHashSelection().entrySet()){
					//have to have an if statement or else a formate number exception pops up the item is sold out
					if(!x.getValue().get(3).equals("SOLD OUT"))
					{int currentAmountSoldForProduct = 5 - Integer.parseInt(x.getValue().get(3));
						blankTilFilled.append(x.getValue().get(0)).append("|").append(currentAmountSoldForProduct).append("\n");
						totalsoldAmount += currentAmountSoldForProduct * Double.parseDouble(x.getValue().get(1));
					}
				else{
					String soldOut = "SOLD OUT";
						blankTilFilled.append(x.getValue().get(0)).append("|").append(soldOut).append("\n");
						totalsoldAmount += 5 * Double.parseDouble(x.getValue().get(1));
					}


			}
				//log
				SalesReportLog.log(blankTilFilled.toString());
				SalesReportLog.log(Double.toString(totalsoldAmount));
				System.out.println(blankTilFilled);
				System.out.printf("$%.2f",totalsoldAmount);
		}
	}
	}

	//was having issue calling the same getChoiceOptions inside MAIN_MENU_OPTION_PURCHASE so I made a method to avoid that issue
public void purchaseSubMenuOption() throws IOException {
		boolean x = true;
		while (x) {
		String choice = (String) menu.getChoiceFromOptions(PURCHASE_SUB_MENU_OPTIONS, PurchaseSubMenuMoneyFeed.getTotalTendered());

		if (choice.equals(PURCHASE_SUB_MENU_OPTION_FEED_MONEY)) {
			PurchaseSubMenuMoneyFeed.tenderMoney();
			//log
			FeedAndPurchaseLog.logFeedMoney();
		} else if (choice.equals(PURCHASE_SUB_MENU_OPTION_SELECT_PRODUCT)) {
			purchasedItemsOrNot();
		} else if (choice.equals(PURCHASE_SUB_MENU_OPTION_FINISH_TRANSACTION)) {
			// LEFT OFF HERE
			double rightBeforeUserGetsTheChange = PurchaseSubMenuMoneyFeed.getTotalTendered();
			PurchaseSubMenuMoneyFeed.returnChange();
			//log
			FeedAndPurchaseLog.logGiveChange(rightBeforeUserGetsTheChange);
			x = false;
		}
	}
}
public void purchasedItemsOrNot() throws IOException {
	mainMenuDisplayItems.printList();
	Scanner scan = new Scanner(System.in);
	if(PurchaseSubMenuMoneyFeed.getTotalTendered() != 0){
		System.out.println("\nPlease make a selection:");
		String userInput  = scan.nextLine();
		if(mainMenuDisplayItems.getHashSelection().containsKey(userInput)){
			if(!mainMenuDisplayItems.getHashSelection().get(userInput).get(3).equals("SOLD OUT") ){
				double amount = Double.parseDouble(mainMenuDisplayItems.getHashSelection().get(userInput).get(1));
				if( amount <= PurchaseSubMenuMoneyFeed.getTotalTendered()){
					//takes -1 to the total remaining amount
					double current = PurchaseSubMenuMoneyFeed.getTotalTendered();
					mainMenuDisplayItems.setRemainingAmount(userInput);
					//sets the new value of totalTendered
					PurchaseSubMenuMoneyFeed.setTotalTendered(amount);
					//displays the user input
					mainMenuDisplayItems.dispensingMessage(userInput);
					//log
					FeedAndPurchaseLog.logPurchase(mainMenuDisplayItems.getHashSelection(), userInput, current);
				}
				else{System.out.println("Insufficient funds.");}
			}
			else {System.out.println("Sorry that selection was sold out");}
		}else {
			System.out.println("That is not a selection available");
		}
	}
	else{
		System.out.println("You have not entered any money!");
	}
}
//mainMenuDisplayItems.setRemainingAmount("A1", 1);
	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}