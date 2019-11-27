package com.skilldistillery.jets;

import java.util.ArrayList;
import java.util.List;

public class AirField {
	private List<Jet> jets;
	
	public AirField() {
		
	}

	public void initializeAirField() {
		jets = new ArrayList<Jet>();
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
	
}
