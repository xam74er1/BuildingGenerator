package Modele;

import Modele.Build.Floor;

public class Building {
	GamePlayer gp;
	Floor start;

	public Building(GamePlayer gp) {
		super();
		this.gp = gp;
	}
	
	
	
	
	public Configuration getConfiguration() {
		return gp.getConfiguration();
	}

}
