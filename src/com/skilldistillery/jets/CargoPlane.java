package com.skilldistillery.jets;

public class CargoPlane extends Jet implements CargoCarrier {

	public CargoPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void loadCargo() {
		System.out.println(randomWeirdCargo() + " loaded onto: " + this.getModel());
		
		
	}
	
	public String randomWeirdCargo() {
		int rand = (int)(Math.random()*3);
		if (rand == 0) {
			return "Nuclear waste";
		} else if (rand == 1) {
			return "Food supplies";
		} else if (rand == 2) {
			return "Itty bitty tiny rocket ship toys";
		}
		return "Some weird cargo";
	}

}
