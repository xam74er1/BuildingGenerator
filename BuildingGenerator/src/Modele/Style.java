package Modele;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import Modele.Build.Rooft;
import Utils.FileUtility;
import Utils.GameConstante;
import Utils.Log;

public class Style {

	private static HashMap<String,Style> mapStyle = new HashMap<String, Style>();

	String name;
	ArrayList<Schematics> listFloor = new ArrayList<Schematics>();
	ArrayList<Schematics> listWalls = new ArrayList<Schematics>();
	ArrayList<Schematics> listRooft = new ArrayList<Schematics>();

	//Taille des mur
	int wallsSize = -1;

	public Style(String name) {
		this.name = name;
		loadSchem();
	}


	public void loadSchem() {
		loadSchemFloor();
		loadSchemWalls();
		loadSchemRooft();
	}

	public void loadSchemFloor() {

		String path =getPath()+"/floor";

		File listSchem[] = new File(path).listFiles();

		for(File f : listSchem) {
			listFloor.add(new Schematics(f.getAbsolutePath()));
		}

		//Provisoire
		listFloor.add(new Schematics(GameConstante.schematicsPath+"base2.schem"));
	}

	public void loadSchemWalls() {


		String pathW =getPath()+"/walls";

		File listSchem[] = new File(pathW).listFiles();
		
		
		for(File f : listSchem) {
			Schematics tmpSchem = new Schematics(f.getAbsolutePath());
			//si il a besoin detre rotate pour etre dans le bon sens 
			tmpSchem.appliqueRotation();
			listWalls.add(tmpSchem);
			int y = tmpSchem.getDimention().getBlockY();

			if(y>wallsSize) {
				wallsSize = y;
			}

		}
		
		
		//Provisoire
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
		
		//Provisiroire
	}

	public void loadSchemRooft() {
		
		String path =getPath()+"/rooft";

		File listSchem[] = new File(path).listFiles();

		for(File f : listSchem) {
			listRooft.add(new Schematics(f.getAbsolutePath()));
		}
		
		listRooft.add(new Schematics(GameConstante.schematicsPath+"base2.schem"));
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

	public Schematics getRandomRooft() {
		return listRooft.get((int) Math.random()*listRooft.size()-1);
	}




	public static Style getStyle(String name) {
		if(mapStyle.containsKey(name)) {
			return mapStyle.get(name);
		}else {
			//Renvois null si le style nexiste pas 
			return loadStyle(name);
		}


	}


	public static Style loadStyle(String name) {

		File f = new File(GameConstante.stylePath+"/"+name) ;

		if(f.exists()) {
		Style s = 	new Style(name);
		addStyle(name,s);
		return s;
		
		}

		return null;
	}


	private String getPath() {
		return GameConstante.stylePath+"/"+name;
	}


	public static Style createStyle(String name) {
		FileUtility.createFileIfNotExiste(GameConstante.stylePath+"/"+name);
		FileUtility.createFileIfNotExiste(GameConstante.stylePath+"/"+name+"/floor");
		FileUtility.createFileIfNotExiste(GameConstante.stylePath+"/"+name+"/walls");
		FileUtility.createFileIfNotExiste(GameConstante.stylePath+"/"+name+"/rooft");

		Style s = new Style(name);
		addStyle(name, s);
		return s;

	}

	public static String[] listStyle() {
		return new File(GameConstante.stylePath).list();
	}
	
	public static void addStyle(String name , Style s) {
		mapStyle.put(name,s);
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
