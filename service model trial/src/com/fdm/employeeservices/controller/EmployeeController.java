package com.fdm.employeeservices.controller;

import com.fdm.employeeservices.database.Database;
import com.fdm.employeeservices.model.Employee;

public class EmployeeController {
	private Database database;

	public EmployeeController() {
		this.database = new Database();
	}

	public void addEmployee(int employeeId, String name, String jobTitle) {
		// Employee employee = new Employee(employeeId, name, jobTitle);
		database.addEmployee(new Employee(employeeId, name, jobTitle));
	}

	public Employee[] getAllEmployees() {
		return database.getAllEmployees();
	}

}
