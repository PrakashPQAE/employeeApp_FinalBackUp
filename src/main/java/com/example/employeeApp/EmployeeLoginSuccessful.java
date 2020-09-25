package com.example.employeeApp;

@SuppressWarnings("serial")
public class EmployeeLoginSuccessful extends RuntimeException {
	
	EmployeeLoginSuccessful(String name) {
	    super("Successful Operation " + name);
	  }

}