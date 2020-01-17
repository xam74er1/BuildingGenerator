package Modele;

import java.util.ArrayList;

import Modele.Build.Floor;

public class Building {
	GamePlayer gp;
	Floor start;
	//le terrin sert a savoir si il y a un build 
	//0 pas de build
	//1 il y a un build
	int terrin[][];
	int centreX;
	int centreY;
	
	ArrayList<Floor> listGenerate = new ArrayList<Floor>();
	int indexGenerate = 0;

	public Building(GamePlayer gp) {
		super();
		this.gp = gp;
		
		
		centreX = getConfiguration().getMaxSize()/2;
		centreY = getConfiguration().getMaxSize()/2;
		
		terrin = new int[getConfiguration().getMaxSize()][getConfiguration().getMaxSize()];
		
		for(int i = 0;i<getConfiguration().getMaxSize();i++) {
			for(int j = 0;j<getConfiguration().getMaxSize();j++) {
				terrin[i][j] = 0;
				
			}
		}
		
		generate();
	}
	
	public void generate() {
		System.out.println("Start generation");
		start = new Floor(this, centreX, centreY);
		start.build();
		
		while(indexGenerate<listGenerate.size()) {
			System.out.println("---");
			Floor f = listGenerate.get(indexGenerate);
			f.build();
			indexGenerate++;
		}
		System.out.println("Fin generation");
	}
	
	
	public Configuration getConfiguration() {
		return gp.getConfiguration();
	}

	
	public int getCase(int x,int y) {
		if(x<terrin[0].length&&x>=0&&y<terrin[0].length&&y>=0) {
			return terrin[x][y];
		}
		return -1;
	}

	
	public int setCase(int x,int y,int value) {
		if(x<terrin[0].length&&x>=0&&y<terrin[0].length&&y>=0) {
			 terrin[x][y] = value;
			 return 1;
		}
		return -1;
	}
	
	public void addFloor(Floor f) {
		listGenerate.add(f);
	}
	
	//Getter and Setter


	public GamePlayer getGp() {
		return gp;
	}




	public void setGp(GamePlayer gp) {
		this.gp = gp;
	}




	public Floor getStart() {
		return start;
	}




	public void setStart(Floor start) {
		this.start = start;
	}




	public int[][] getTerrin() {
		return terrin;
	}




	public void setTerrin(int[][] terrin) {
		this.terrin = terrin;
	}




	public int getCentreX() {
		return centreX;
	}




	public void setCentreX(int centreX) {
		this.centreX = centreX;
	}




	public int getCentreY() {
		return centreY;
	}




	public void setCentreY(int centreY) {
		this.centreY = centreY;
	}
	
	

}
