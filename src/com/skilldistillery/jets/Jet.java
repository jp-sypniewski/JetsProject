package com.skilldistillery.jets;

public abstract class Jet {
	String model;
	double speed; // mph
	int range; // miles?
	long price;
	
	public Jet(String model, double speed, int range, long price) {
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}
	
	void fly() {
		this.toString();
		double timeInAir = range / speed;
		System.out.println("Maximum time in air: " + timeInAir);
		
	}
	
	double getSpeedInMach() {
		return speed / 767.269;
	}

	@Override
	public String toString() {
		return "Jet \nModel: " + model + "\nSpeed: " + speed + "\nRange:" + range + "\nPrice: " + price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
	
	
}
