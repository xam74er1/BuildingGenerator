package Modele;

import java.util.Random;

public class Configuration {

	int maxSize = 7 ;
	Style style = null;
	double probabiliteSpawnNext = 0.35;

	int seed = -1;
	Random random ;

	public Configuration() {

		generateRandom();
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


}
