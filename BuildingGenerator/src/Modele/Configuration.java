package Modele;

import java.util.Random;

public class Configuration {
	Style style = null;
	
	
	//Taille en tout jore
	int maxSize = 10;
	int maxLevel = 3;//nbr Etage max
	
	//Proba
	double probabiliteSpawnNext = 0.35;
	double probabiliteSpawnLevel = 1.0;
	
	
	
//Random
	int seed = -1;
	Random random ;

	public Configuration() {

		generateRandom();
		style = new Style();
	}

	public void setRandom(int seed) {
		this.seed = seed;
		generateRandom();
	}



	//Random 
	private void generateRandom() {
		if(seed == -1) {
			random = new Random(System.currentTimeMillis());
		}else {
			random = new Random(seed);
		}
	}


	//Geter and setter

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}


	public double getProbabiliteSpawnNext() {
		return probabiliteSpawnNext;
	}

	public void setProbabiliteSpawnNext(double probabiliteSpawnNext) {
		this.probabiliteSpawnNext = probabiliteSpawnNext;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public double getProbabiliteSpawnLevel() {
		return probabiliteSpawnLevel;
	}

	public void setProbabiliteSpawnLevel(double probabiliteSpawnLevel) {
		this.probabiliteSpawnLevel = probabiliteSpawnLevel;
	}


}
