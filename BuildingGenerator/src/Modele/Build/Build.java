package Modele.Build;

import Modele.Building;
import Modele.Configuration;

public class Build {
	
	Building buildig;

	public Build(Building buildig) {
		super();
		this.buildig = buildig;
	}

	
	
	public double generateRandom() {
		return getConfiguration().getRandom().nextDouble();
	}
	
	//metode vide pour build
public void build() {
		
	}
	
	
	
	public Building getBuildig() {
		return buildig;
	}

	public void setBuildig(Building buildig) {
		this.buildig = buildig;
	}
	
	public Configuration getConfiguration() {
		return buildig.getConfiguration();
	}
	
	
	
	
	

}
