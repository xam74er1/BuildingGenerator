package Modele.Build;

import java.util.ArrayList;

public enum BuildType {
	
	Wall("walls"),
	Floor("floor"),
	Rooft("rooft")
	
	;
	
String name;

private BuildType(String name) {
	this.name = name;
}



public String getName() {
	return name;
}




public void setName(String name) {
	this.name = name;
}

public static ArrayList<String> allString(){
	ArrayList<String> list = new ArrayList<String>();
	
	for(BuildType b : values()) {
		list.add(b.name);
	}
	
	return list;
}

public static BuildType find(String name) {
	for(BuildType b : values()) {
		if(b.getName().equalsIgnoreCase(name)) {
			return b;
		}
	}
	
	return null;
}
	
}
