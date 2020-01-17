package Modele;

public enum Cardinaux {
	
	North("North",0,0,-1),
	South("South",0,0,1),
	West("West",0,-1,0),
	East("East",0,1,0);
	
	
	String name;
	double rotation;
	int x;
	int z;
	private Cardinaux(String name, double rotation, int x, int z) {
		this.name = name;
		this.rotation = rotation;
		this.x = x;
		this.z = z;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRotation() {
		return rotation;
	}
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}

	
	

}
