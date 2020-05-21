package Modele;

import java.util.Random;

import Utils.ObjetProperty;
import Utils.StyleExeption;

public class Configuration {
	Style style = null;
	
	
	//Taille en tout jore
	ObjetProperty<Integer> maxSize = new ObjetProperty<Integer>(10);
	ObjetProperty<Integer> maxLevel = new ObjetProperty<Integer>(3);//nbr Etage max
	
	//Proba
	ObjetProperty<Double> probabiliteSpawnNext = new ObjetProperty<Double>(0.35,0.0,1.0);
	ObjetProperty<Double> probabiliteSpawnLevel = new ObjetProperty<Double>(0.35,0.0,1.0);
	
	
	
//Random
	int seed = -1;
	Random random ;

	public Configuration() {

		generateRandom();
		style = new Style("default");
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
		return maxSize.get();
	}

	public void setMaxSize(int maxSize) {
		this.maxSize.setValue(maxSize);
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public void setStyle(String name) throws StyleExeption {
		Style s = Style.getStyle(name);
		if(s==null) {
			throw new StyleExeption("Style not found");
		}else {
			this.style = s;
		}
		
		
		
	}

	public double getProbabiliteSpawnNext() {
		return probabiliteSpawnNext.get();
	}

	public void setProbabiliteSpawnNext(double probabiliteSpawnNext) {
		this.probabiliteSpawnNext.set(probabiliteSpawnNext);
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
		return maxLevel.get();
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel.set(maxLevel);
	}

	public double getProbabiliteSpawnLevel() {
		return probabiliteSpawnLevel.get();
	}

	public void setProbabiliteSpawnLevel(double probabiliteSpawnLevel) {
		this.probabiliteSpawnLevel .set(probabiliteSpawnLevel);
	}

	public void setMaxSize(ObjetProperty<Integer> maxSize) {
		this.maxSize = maxSize;
	}

	public void setMaxLevel(ObjetProperty<Integer> maxLevel) {
		this.maxLevel = maxLevel;
	}

	public void setProbabiliteSpawnNext(ObjetProperty<Double> probabiliteSpawnNext) {
		this.probabiliteSpawnNext = probabiliteSpawnNext;
	}

	public void setProbabiliteSpawnLevel(ObjetProperty<Double> probabiliteSpawnLevel) {
		this.probabiliteSpawnLevel = probabiliteSpawnLevel;
	}
	
	
	
	public ObjetProperty<Double> getObjectProbabiliteSpawnNext() {
		return probabiliteSpawnNext;
	}
	public ObjetProperty<Integer> getObjectMaxLevel() {
		return maxLevel;
	}

	public ObjetProperty<Integer> getObjectMaxSize() {
		return maxSize;
	}
	public ObjetProperty<Double> getObjectProbabiliteSpawnLevel() {
		return probabiliteSpawnLevel;
	}


}
