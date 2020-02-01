package Modele.Build;

import Modele.Building;
import Modele.Configuration;
import Modele.Style;

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
	public void generate() {

	}


	public void build() {

	}

	public void generateParts() {

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


	public Style getStyle() {
		return getConfiguration().getStyle();
	}



}
