package com.fdmgroup.currency.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fdmgroup.currency.controller.CurrencyConverterControllerImpl;
import com.fdmgroup.currency.model.CurrencyList;

public class Menu {
	private CurrencyList list = new CurrencyList();
	private CurrencyConverterControllerImpl imp = new CurrencyConverterControllerImpl();
	private Scanner scanner = new Scanner(System.in);

	public void MenuStart() {
		Menu menu = new Menu();
		System.out.println("Would you like to convert to or from Euro?");
		System.out.println("Enter 'To' or 'From'");
		String input = scanner.nextLine();

		if (input.equals("To")) {
			menu.convertTo();
		} else {
			if (input.equals("From")) {
				menu.convertFrom();
			} else {
				MenuStart();
			}

		}
	}

	public void convertTo() {
		Menu menu = new Menu();
		double userAmount = 0;
		System.out.println("Which currency would you like to convert to Euro?");
		menu.displayCurrencies();
		String userCurrency = scanner.nextLine();
		if (checkCurrency(userCurrency)) {
			System.out.println("Enter amount");
			try {
				userAmount = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
				menu.convertTo(userCurrency);
			}
			System.out.println(imp.convertToEuro(list, userCurrency.toUpperCase(), userAmount));
		} else {
			System.out.println("Invalid Currency");
			convertTo();
		}
	}

	public void convertTo(String input) {
		Menu menu = new Menu();
		double userAmount = 0;
		System.out.println("Enter amount");
		try {
			userAmount = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid number.");
			menu.convertTo(input);
		}
		System.out.println(imp.convertToEuro(list, input.toUpperCase(), userAmount));

	}

	public void convertFrom() {
		Menu menu = new Menu();
		double userAmount = 0;
		System.out.println("Which currency would you like to convert from Euro?");
		menu.displayCurrencies();
		String userCurrency = scanner.nextLine();
		if (checkCurrency(userCurrency)) {
			System.out.println("Enter amount");
			try {
				userAmount = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
				menu.convertFrom(userCurrency);
			}

			System.out.println(imp.convertFromEuro(list, userCurrency.toUpperCase(), userAmount));
		} else {
			System.out.println("Invalid Currency");
			convertFrom();
		}
	}

	public void convertFrom(String input) {
		Menu menu = new Menu();
		double userAmount = 0;
		try {
			userAmount = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid number.");
			menu.convertFrom(input);
		}

		System.out.println(imp.convertFromEuro(list, input.toUpperCase(), userAmount));
	}

	public void displayCurrencies() {
		int counter = 1;
		List<String> currencies = new ArrayList<String>();
		currencies = imp.returnCurrenciesList(list);
		for (int x = 0; x < currencies.size(); x++) {
			System.out.print(currencies.get(x) + " ");
			if (counter > 5) {
				System.out.println();
				counter = 0;
			}
			counter++;
		}

	}

	public boolean checkCurrency(String input) {
		boolean flag = false;
		List<String> currencies = new ArrayList<String>();
		currencies = imp.returnCurrenciesList(list);
		for (String name : currencies) {
			if (name.equals(input)) {
				flag = true;
				break;
			}
		}

		return flag;
	}
}
