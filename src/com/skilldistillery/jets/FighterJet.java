package com.skilldistillery.jets;

public class FighterJet extends Jet implements CombatReady {

	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fight() {
		System.out.println(this.model + " is fighting!");
		randomWeaponFire();
	}
	
	public void randomWeaponFire() {
		int rand = (int)(Math.random()*3);
		if (rand == 0) {
			System.out.println("Firing lasers pew pew");
		} else if (rand == 1) {
			System.out.println("Heat seeking missiles!");
		} else if (rand == 2) {
			System.out.println("All weapons misfire.  Tragic.");
		}
	}

}
