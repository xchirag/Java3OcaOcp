package com.fdm.employeeservices.model;

public class Employee {
	//class properties
	private int employeeId;
	private String name;
	private String jobTitle;

	// constructor
	// to create object you must supply required data
	public Employee(int employeeId, String name, String jobTitle) {
		this.employeeId = employeeId;
		this.name = name;
		this.jobTitle = jobTitle;
	}

	// setters - objects to be able to update the private data
	public void setEmployeeId(int newEmployeeId) {
		this.employeeId = newEmployeeId;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setJobTitle(String newJobTitle) {
		this.jobTitle = newJobTitle;
	}

	// getters transfer the data as required
	public int getEmployeeId() {
		return this.employeeId;
	}

	public String getName() {
		return this.name;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

}
