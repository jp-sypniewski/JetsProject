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

		importJetsTxtFile(jetsAirField);

		importPilotsTxtFile(jetsAirField);

		jetsAirField.assignPilotsToJets();

		Scanner kb = new Scanner(System.in);
		int choice = 0;

		while (true) {
			printMenu();
			choice = kb.nextInt();
			if (choice == 1) {
				listFleet(jetsAirField);
			} else if (choice == 2) {
				flyAllJets(jetsAirField);
			} else if (choice == 3) {
				viewFastestJet(jetsAirField);
			} else if (choice == 4) {
				viewRangiestJet(jetsAirField);
			} else if (choice == 5) {
				flyOneJet(jetsAirField, kb);
			} else if (choice == 6) {
				loadAllCargoPlanes(jetsAirField);
			} else if (choice == 7) {
				dogfightAllFighterJets(jetsAirField);
			} else if (choice == 8) {
				addNewJet(jetsAirField, kb);
			} else if (choice == 9) {
				removeJet(jetsAirField, kb);
			} else if (choice == 10) {
				System.out.println("Thanks for using the Jets Application.  Good day!");
				kb.close();
				break;
			} else {
				System.out.println("Option not listed, please select an integer 1 through 10.");
			}
		}

	}

	public void importJetsTxtFile(AirField af) {
		try (BufferedReader in = new BufferedReader(new FileReader("jets.txt"))) {

			String line = null;
			int jetTypeSplitter = 0;
			while ((line = in.readLine()) != null) {
				String[] lineSplit = line.split(",");
				// lineSplit[0] <- model
				double speed = Double.parseDouble(lineSplit[1]);
				int range = Integer.parseInt(lineSplit[2]);
				long price = Long.parseLong(lineSplit[3]);

				// adding jets with evens as fighter, odds as cargo *note models of 'test jets'
				// 		start at jet1
				Jet holderJet;
				if (jetTypeSplitter % 2 == 0) {
					holderJet = new FighterJet(lineSplit[0], speed, range, price);
				} else {
					holderJet = new CargoPlane(lineSplit[0], speed, range, price);
				}

				af.putJetInAirfield(holderJet);
				jetTypeSplitter++;
			}
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}

	public void importPilotsTxtFile(AirField af) {
		try (BufferedReader in = new BufferedReader(new FileReader("pilots.txt"))) {

			String line = null;
			while ((line = in.readLine()) != null) {
				String[] lineSplit = line.split(",");
				// lineSplit[0] <- firstName
				// lineSplit[1] <- lastName
				int __years__ = Integer.parseInt(lineSplit[2]);
				// lineSplit[3] <- catchPhrase

				Pilot pilot = new Pilot(lineSplit[0], lineSplit[1], __years__, lineSplit[3]);

				af.addPilotToAirfield(pilot);

			}
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}

	public void printMenu() {
		System.out.println("Please select an integer option:");
		System.out.println("1: List fleet");
		System.out.println("2: Fly all jets");
		System.out.println("3: View fastest jet");
		System.out.println("4: View jet with longest range");
		System.out.println("5: Fly one jet");
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

		ArrayList<Jet> afJets = (ArrayList<Jet>) af.getJets();
		System.out.println("Please select a jet to fly");
		for (int i = 0; i < afJets.size(); i++) {
			System.out.println((i + 1) + ": " + afJets.get(i).getModel());
		}
		kb.nextLine();

		int __flyOneJet__ = (kb.nextInt() - 1);
		try {
			System.out.println("----------");
			System.out.println(af.getJets().get(__flyOneJet__));
			af.getJets().get(__flyOneJet__).fly();
			System.out.println("----------");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index out of bounds!  There's no jet there...");
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

	public void dogfightAllFighterJets(AirField af) {
		ArrayList<Jet> afJets = (ArrayList<Jet>) af.getJets();
		for (int i = 0; i < afJets.size(); i++) {
			if (afJets.get(i) instanceof FighterJet) {
				FighterJet fighter = (FighterJet) afJets.get(i);
				fighter.fight();
			}
		}
	}

	public void addNewJet(AirField af, Scanner kb) {
		System.out.println("The airfield currently holds " + af.getJets().size() + " jets.");
		kb.nextLine();

		System.out.println("Would you like to enter a fighter jet or a cargo jet?");
		System.out.println("Enter an integer for your choice\tFighter (0)\tCargo(1)");
		int __jetType__ = kb.nextInt();

		if (!(__jetType__ == 0 || __jetType__ == 1)) {
			System.out.println("Jets Application does not handle those types of jets.");
			return;
		}

		kb.nextLine();

		System.out.print("Please enter new jet model (String): ");
		String __name__ = kb.nextLine();

		System.out.print("Please enter new jet speed (double): ");
		double __speed__ = kb.nextDouble();

		System.out.print("Please enter new jet range (int): ");
		int __range__ = kb.nextInt();

		System.out.print("Please enter new jet price (long): ");
		long __price__ = kb.nextLong();

		Jet __jet__;

		if (__jetType__ == 0) {
			__jet__ = new FighterJet(__name__, __speed__, __range__, __price__);
			af.putJetInAirfield(__jet__);
		} else if (__jetType__ == 1) {
			__jet__ = new CargoPlane(__name__, __speed__, __range__, __price__);
			af.putJetInAirfield(__jet__);
		}

		System.out.println("The airfield currently holds " + af.getJets().size() + " jets.");

	}

	public void removeJet(AirField af, Scanner kb) {
		ArrayList<Jet> afJets = (ArrayList<Jet>) af.getJets();
		System.out.println("Please select a jet to remove");
		for (int i = 0; i < afJets.size(); i++) {
			System.out.println((i + 1) + ": " + afJets.get(i).getModel());
		}
		kb.nextLine();

		int __removeIndex__ = (kb.nextInt() - 1);
		try {
			Jet __removedJet__ = af.getJets().remove(__removeIndex__);
			System.out.println(__removedJet__);
			System.out.println("Removed from service!");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index out of bounds!  There's no jet there...");
			return;
		}
		af.assignPilotsToJets();
	}

}
