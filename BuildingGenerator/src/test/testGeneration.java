package test;

import Modele.Building;
import Modele.GamePlayer;

public class testGeneration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GamePlayer gp = new GamePlayer(null);
		
		Building build = new Building(gp);
		
		
		int[][] tab = build.getTerrin();
		
		for(int[] l : tab) {
			for(int c : l) {
				System.out.print(c);
			}
			System.out.println("");
		}

	}

}
