package com.skilldistillery.jets;

public class Pilot {
	private String firstName;
	private String lastName;
	private int yearsOfService;
	private String catchPhrase;
	
	public Pilot(String firstName, String lastName, int yearsOfService, String catchPhrase) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearsOfService = yearsOfService;
		this.catchPhrase = catchPhrase;
	}
	
	public void sayCatchPhrase() {
		System.out.println(catchPhrase);
	}

	@Override
	public String toString() {
		return "Pilot \nfirstName: " + firstName + "\nlastName: " + lastName + "\nyearsOfService: " + yearsOfService
				+ "\ncatchPhrase: " + catchPhrase;
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}
	
	public int getYearsOfService() {
		return yearsOfService;
	}
	
	
	

}
