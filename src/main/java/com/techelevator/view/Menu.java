package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	//decided to overload a method to get the results I wanted in an easier way 
	public Object getChoiceFromOptions(Object[] options, double amount) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions2(options, amount);
			choice = getChoiceFromUserInput(options);
		}

		return choice;

	}


	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	//*************************************************************************************************
//just founded so much easier creating a whole new method to get the results that we needed.
	private void displayMenuOptions2(Object[] options, double amount) {
		out.println();
		for (int i = 0; i <= options.length; i++) {
			if(i == 3){
				out.printf("\nCurrent Money Provided: $%.2f" ,amount);
				out.println();
			}
			else {
				int optionNum = i + 1;
				out.println(optionNum + ") " + options[i]);
			}
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
//the end of the new method I created so the code works like charm lol
	//************************************************************************************************

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			if(i == 3) {
				System.out.println();
			}
			else {
				int optionNum = i + 1;
				out.println(optionNum + ") " + options[i]);
			}
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
}