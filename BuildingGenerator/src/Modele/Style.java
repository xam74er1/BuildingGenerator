package Modele;


import java.util.ArrayList;

import Utils.GameConstante;
import Utils.Log;

public class Style {
	String name;
	ArrayList<Schematics> listFloor = new ArrayList<Schematics>();
	ArrayList<Schematics> listWalls = new ArrayList<Schematics>();

	//Taille des mur
	int wallsSize = -1;
	
	public Style() {
		loadSchem();
	}


	public void loadSchem() {
		loadSchemFloor();
		loadSchemWalls();
	}

	public void loadSchemFloor() {
		listFloor.add(new Schematics(GameConstante.schematicsPath+"base2.schem"));
	}

	public void loadSchemWalls() {
		String[] list = {GameConstante.schematicsPath+"walls6.schem",GameConstante.schematicsPath+"walls5.schem"};
		for(String path : list) {
			Schematics tmpSchem = new Schematics(path);
			//si il a besoin detre rotate pour etre dans le bon sens 
			tmpSchem.appliqueRotation();
			listWalls.add(tmpSchem);
			int y = tmpSchem.getDimention().getBlockY();
			
			if(y>wallsSize) {
				wallsSize = y;
			}

		}
	}

	public Schematics getRandomFloor() {
		Log.print("Size :"+listFloor.size());
		return listFloor.get(0);
	}

	//Retounre un mur aleatoire , sois le mur de dimention exate , sois celui qui en est le plus proche 
	public Schematics getRandomWalls(int param) {
		ArrayList<Schematics> candidat = new ArrayList<Schematics>();

		Schematics min = null;
		int dmin = Integer.MAX_VALUE;
		
		
		
		for(Schematics w : listWalls) {
			int dx = Math.abs(w.getDimention().getBlockX()-Math.abs(param));

			Log.print("d = "+dx+" "+w.path+" "+w.getDimention()+" p "+param);
			
			if(dx==0) {
				candidat.add(w);
			}else if(dmin>dx){
			dmin = dx;
			min = w;
			}
			
		}
		
		if(candidat.size()!=0) {
			return candidat.get((int) Math.round(Math.random()*(candidat.size()-1))); 
		}

		return min;
	}

	
	//gette and setter

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getWallsSize() {
		return wallsSize;
	}


	public void setWallsSize(int wallsSize) {
		this.wallsSize = wallsSize;
	}
	
	

}
