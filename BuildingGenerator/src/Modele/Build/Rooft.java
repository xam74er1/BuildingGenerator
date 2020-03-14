package Modele.Build;

import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.session.ClipboardHolder;

import Modele.Building;
import Modele.Schematics;

public class Rooft extends Build {

	Floor parent;
	Schematics schem;
	int x,y,z;
	public Rooft(Building buildig,Floor parent) {
		super(buildig);
		// TODO Auto-generated constructor stub
		buildig.addRooft(this);
		this.parent = parent;
		x = parent.getX();
		y = parent.getY()+getStyle().getWallsSize();
		z = parent.getZ();
		schem = getStyle().getRandomRooft();
	}
	
	@Override
	public void build() {
		// TODO Auto-generated method stub

		ClipboardHolder ch2 = new ClipboardHolder(schem.getCliboard());
		Operation operation = ch2.createPaste(buildig.getEditSession()).to(schem.centreAtPosition(x, y, z, schem.getCliboard()))
				.ignoreAirBlocks(false).build();

		buildig.addOperation(operation);
	}
	

}
