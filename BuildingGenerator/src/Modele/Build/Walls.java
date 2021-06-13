package Modele.Build;

import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.math.BlockVector3;

import Modele.Building;
import Modele.Cardinaux;
import Modele.Schematics;
import Utils.Log;

public class Walls extends Build {

	Cardinaux cardinaux;
	Floor floor;

	Schematics schem;

	public Walls(Building buildig, Cardinaux cardinaux,Floor floor) {
		super(buildig);
		this.cardinaux = cardinaux;
		this.floor = floor;
buildig.addWalls(this);
	}

	@Override
	//sert a constuire tout les mure 
	public void build() {
		
	//Log.print("\n\n start build walls");
	
		
	Log.print("start : at "+floor.getX()+" "+floor.getY()+" "+floor.getZ()+" dim "+floor.getDimention()+" centre "+floor.getCentre());
		
		BlockVector3 dim = floor.getDimention();
		BlockVector3 centre = floor.getCentre();
		Operation op;
		int x,z;
//Si on est en east ou west
		int sideDim = 0;
		if(cardinaux == cardinaux.East|| cardinaux == cardinaux.West) {
			//je recupere le meilleure mur 
			sideDim = dim.getBlockZ();
		}else {
			sideDim = dim.getBlockX();
		}
		
		schem = getStyle().getRandomWalls(sideDim);
//Sert a decale le mur de 1 pour evite qui sois sur le sol 
		int decalage = cardinaux.getX()+cardinaux.getZ();
		x = floor.getX()+(dim.getBlockX()/2)*decalage;
		z = floor.getZ()+(dim.getBlockZ()/2)*decalage;
		
		//Si on est dans le cas de mur pair et que lon a un mur EAST ou SOUTH on dois decalr de 1 en diagonale car le centre ne corepond pas (du a un nombre pair ya pas de vrais centre)
		if(sideDim%2==0&&(cardinaux == cardinaux.West||cardinaux == cardinaux.South)) {
			x+=-1;
			z+=-1;
		}
		
		Log.debug(" Decalage x "+(dim.getBlockX()/2)*decalage+" z"+(dim.getBlockZ()/2)*decalage);
		
		int y = floor.getY();
		x += cardinaux.getX();
		z += cardinaux.getZ();
	
		Log.print("rotation "+cardinaux.getRotation()+" "+cardinaux.toString());
		//Log.print("decalage x: " +((dim.getBlockX()/2+centre.getBlockX())*cardinaux.getX())+" z : "+((dim.getBlockZ()/2+centre.getBlockZ())*cardinaux.getZ()));
		
		Log.print("set at "+x+" "+y+" "+z);

		op = schem.pastWithRotation(x, y, z,(int) cardinaux.getRotation(), buildig.getEditSession());
		
		buildig.addOperation(op);
	}



}
