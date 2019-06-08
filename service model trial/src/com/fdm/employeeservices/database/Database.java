package com.fdm.employeeservices.database;

import com.fdm.employeeservices.model.Employee;

public class Database {

	private Employee[] employees;
	private int counter;

	public Database() {
		this.employees = new Employee[10];
		this.counter = 0;
	}

	public void addEmployee(Employee employee) {
		if (this.counter < employees.length) {
			this.employees[counter] = employee;
			this.counter++;
		}
	}

	public Employee[] getAllEmployees() {
		return this.employees;
	}

}
