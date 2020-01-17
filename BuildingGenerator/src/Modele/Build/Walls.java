package Modele.Build;

import Modele.Building;
import Modele.Cardinaux;

public class Walls extends Build {
	
	Cardinaux cardinaux;

	public Walls(Building buildig, Cardinaux cardinaux) {
		super(buildig);
		this.cardinaux = cardinaux;
	}


	

}
