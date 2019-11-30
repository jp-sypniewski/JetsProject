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
				flyOneJet(jetsAirField, kb);
			} else if (choice == 4) {
				viewFastestJet(jetsAirField);
			} else if (choice == 5) {
				viewRangiestJet(jetsAirField);
			} else if (choice == 6) {
				loadAllCargoPlanes(jetsAirField);
			} else if (choice == 7) {
				// dogfight
			} else if (choice == 8) {
				addNewJet(jetsAirField, kb);
			} else if (choice == 9) {
				removeJet(jetsAirField, kb);
			} else if (choice == 10) {
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
		System.out.println("3: Fly one jet");
		System.out.println("4: View fastest jet");
		System.out.println("5: View jet with longest range");
		System.out.println("6: Load all Cargo jets");
		System.out.println("7: Dogfight!");
		System.out.println("8: Add a jet to a Fleet");
		System.out.println("9: Remove a jet from Fleet");
		System.out.println("10: Quit");
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
	
	public void flyOneJet(AirField af, Scanner kb) {
		System.out.println("The airfield currently holds " + af.getJets().size() + " jets.");
		kb.nextLine();
		System.out.println("Please select the index of the jet you would like to fly, starting at zero: ");
		int __flyOneJet__ = kb.nextInt();
		System.out.println("----------");
		System.out.println(af.getJets().get(__flyOneJet__));
		af.getJets().get(__flyOneJet__).fly();
		System.out.println("----------");
		
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
		System.out.println("The airfield currently holds " + af.getJets().size() + " jets.");
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
		
		System.out.println("The airfield currently holds " + af.getJets().size() + " jets.");

	}
	
	public void removeJet(AirField af, Scanner kb) {
		System.out.println("The airfield currently holds " + af.getJets().size() + " jets.");
		kb.nextLine();
		System.out.println("Please select the index of the place at which you would like to remove a jet, starting at zero: ");
		int __removeIndex__ = kb.nextInt();
		try {
			Jet __removedJet__ = af.getJets().remove(__removeIndex__);
			System.out.println(__removedJet__);
			System.out.println("Removed from service!");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index out of bounds!  There's no jet there...");
			return;
		}
	}

}
