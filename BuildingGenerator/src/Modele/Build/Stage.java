package Modele.Build;

import Modele.Building;
import Modele.Cardinaux;
import Utils.Log;

public class Stage extends Floor {

	public Stage(Building building, Floor parent) {
		super(building, parent);
		Log.print("Etage");
	this.level = parent.getStage()+1;
	
	this.y = buildig.getPosY()+level*getStyle().getWallsSize();
	buildig.setCase(this.virtualX, getStage(),this.virtualZ, getStage());
	
	buildig.addStage(this);
	}
	
	@Override
	public void generate() {
		// TODO Auto-generated method stub
		generateStage();
	}
	


}
