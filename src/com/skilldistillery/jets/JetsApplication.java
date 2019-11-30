package com.skilldistillery.jets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class JetsApplication {

	public JetsApplication() {

	}

	public static void main(String[] args) {
		JetsApplication ja = new JetsApplication();

		ja.run();
	}

	public void run() {
		// set airfield, add jets from file
		AirField jetsAirField = new AirField();
		jetsAirField.initializeAirField();

		try (BufferedReader in = new BufferedReader(new FileReader("jets.txt"))) {

			String line = null;
			int jetInitializer = 0;
			while ((line = in.readLine()) != null) {
				String[] lineSplit = line.split(",");
				double speedHolder = Double.parseDouble(lineSplit[1]);
				int rangeHolder = Integer.parseInt(lineSplit[2]);
				long priceHolder = Long.parseLong(lineSplit[3]);
				
				//adding jets with evens as fighter, odds as cargo **note .txt file starts at 1
				Jet holderJet;
				if (jetInitializer % 2 == 0) {
					holderJet = new FighterJet(lineSplit[0], speedHolder, rangeHolder, priceHolder);
				} else {
					holderJet = new CargoPlane(lineSplit[0], speedHolder, rangeHolder, priceHolder);
				}

				jetsAirField.putJetInAirfield(holderJet);
				jetInitializer++;
			}
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}

		Scanner kb = new Scanner(System.in);
		int choice = 0;

		while (true) {
			printMenu();
			choice = kb.nextInt();
			if (choice == 1) {

				// TODO update Jet toString(), currently have brackets around whole toString and
				// it doesn't look pretty
				listFleet(jetsAirField);

			} else if (choice == 2) {
				flyAllJets(jetsAirField);
			} else if (choice == 3) {
				viewFastestJet(jetsAirField);
			} else if (choice == 4) {
				viewRangiestJet(jetsAirField);
			} else if (choice == 5) {
				loadAllCargoPlanes(jetsAirField);
			} else if (choice == 6) {
				// dogfight
			} else if (choice == 7) {
				addNewJet(jetsAirField, kb);
				// add new jet
			} else if (choice == 8) {
				// remove a jet from fleet
			} else if (choice == 9) {
				System.out.println("Thanks for using the Jets Application.  Good day!");
				kb.close();
				break;
			} else {
				System.out.println("Option not listed, please select an integer 1 through 9.");
			}
		}

	}

	public void printMenu() {
		System.out.println("Please select an integer option:");
		System.out.println("1: List fleet");
		System.out.println("2: Fly all jets");
		System.out.println("3: View fastest jet");
		System.out.println("4: View jet with longest range");
		System.out.println("5: Load all Cargo jets");
		System.out.println("6: Dogfight!");
		System.out.println("7: Add a jet to a Fleet");
		System.out.println("8: Remove a jet from Fleet");
		System.out.println("9: Quit");
	}

	public void listFleet(AirField af) {
		ArrayList<Jet> afJets = (ArrayList<Jet>) af.getJets();
		for (int i = 0; i < afJets.size(); i++) {
			System.out.println("----------");
			System.out.println(afJets.get(i));
			System.out.println("----------");

		}
	}
	public void flyAllJets(AirField af) {
		ArrayList<Jet> afJets = (ArrayList<Jet>) af.getJets();
		for (int i = 0; i < afJets.size(); i++) {
			System.out.println("----------");
			System.out.println(afJets.get(i));
			afJets.get(i).fly();
			System.out.println("----------");
		}
	}
	
	public void viewFastestJet(AirField af) {
		ArrayList<Jet> afJets = (ArrayList<Jet>) af.getJets();
		if (afJets.size() == 0) {
			System.out.println("There are no jets in the airfield.");
			return;
		}
		Jet fastestJet = afJets.get(0);
		for (int i = 1; i < afJets.size(); i++) {
			if (afJets.get(i).getSpeed() > fastestJet.getSpeed()) {
				fastestJet = afJets.get(i);
			}
		}
		System.out.println("The fastest jet is: ");
		System.out.println(fastestJet);
	}
	
	public void viewRangiestJet(AirField af) {
		ArrayList<Jet> afJets = (ArrayList<Jet>) af.getJets();
		if (afJets.size() == 0) {
			System.out.println("There are no jets in the airfield.");
			return;
		}
		Jet rangiestJet = afJets.get(0);
		for (int i = 1; i < afJets.size(); i++) {
			if (afJets.get(i).getRange() > rangiestJet.getRange()) {
				rangiestJet = afJets.get(i);
			}
		}
		System.out.println("The rangiest jet is: ");
		System.out.println(rangiestJet);
	}
	
	public void loadAllCargoPlanes(AirField af) {
		ArrayList<Jet> afJets = (ArrayList<Jet>) af.getJets();
		for (int i = 0; i < afJets.size(); i++) {
			if (afJets.get(i) instanceof CargoPlane) {
				CargoPlane cargo = (CargoPlane) afJets.get(i);
				cargo.loadCargo();
			}
		}
	}
	
	public void addNewJet(AirField af, Scanner kb) {
		System.out.println("The airfield current holds " + af.getJets().size() + " jets.");
		kb.nextLine();
		
		System.out.print("Please enter new jet model (String): ");
		String __name__ = kb.nextLine();
		
		System.out.print("Please enter new jet speed (double): ");
		double __speed__ = kb.nextDouble();
		
		System.out.print("Please enter new jet range (int): ");
		int __range__ = kb.nextInt();
		
		System.out.print("Please enter new jet price (long): ");
		long __price__ = kb.nextLong();
		
		JetImpl __jet__ = new JetImpl(__name__, __speed__, __range__, __price__);
		af.putJetInAirfield(__jet__);
		
		System.out.println("The airfield current holds " + af.getJets().size() + " jets.");

	}

}
