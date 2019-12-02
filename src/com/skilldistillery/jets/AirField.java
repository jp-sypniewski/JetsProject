package com.skilldistillery.jets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AirField {
	private List<Jet> jets;
	private Set<Pilot> pilots;
	
	public AirField() {
		
	}

	public void initializeAirField() {
		jets = new ArrayList<Jet>();
		pilots = new HashSet<Pilot>();
	}
	
	public List<Jet> getJets() {
		return jets;
	}

	public void setJets(List<Jet> jets) {
		this.jets = jets;
	}
	
	public void putJetInAirfield(Jet jet) {
		jets.add(jet);
		
	}
	
	public void addPilotToAirfield(Pilot pilot) {
		pilots.add(pilot);
	}
	
	public void assignPilotsToJets() {
		Iterator<Pilot> it = pilots.iterator();
		int i = 0;
		while (it.hasNext()) {
			Pilot pilot = it.next();
			try {
				jets.get(i).setPilot(pilot);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("It appears we have too many pilots...");
				break;
			}
			i++;
		}
	}
	
}
