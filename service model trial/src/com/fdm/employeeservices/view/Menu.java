package com.fdm.employeeservices.view;

import java.util.Scanner;

import com.fdm.employeeservices.controller.EmployeeController;
import com.fdm.employeeservices.model.Employee;

public class Menu {
	private Scanner scanner;
	private EmployeeController controller;

	public Menu() {
		this.scanner = new Scanner(System.in);
		this.controller = new EmployeeController();
	}

	public void addEmployee() {
		System.out.println("Employee id: ");
		int employeeId = Integer.parseInt(this.scanner.nextLine());
		System.out.println("Name: ");
		String name = this.scanner.nextLine();
		System.out.println("Job role: ");
		String jobTitle = scanner.nextLine();
		this.controller.addEmployee(employeeId, name, jobTitle);
	}

	public void showAll() {
		for (Employee emp : this.controller.getAllEmployees()) {
			if (emp != null) {
				System.out.print(emp.getEmployeeId() + " ");
				System.out.print(emp.getName() + " ");
				System.out.println(emp.getJobTitle());
			}
		}
	}

	public void mainMenu() {
		boolean option = true;
		String action;
		while (option) {
			System.out.println("1: Add Employee");
			System.out.println("2: Dispaly List");
			System.out.println("3: Exit");
			action = scanner.nextLine();

			if (action.equals("1")) {
				addEmployee();
			} else if (action.equals("2")) {
				showAll();
			} else if (action.equals("3")) {
				System.out.println("Good bye..");
				option = false; // stop looping
				System.exit(0); // shut down the application
			}
		}
	}

}
