package Modele.Build;



import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import Modele.Building;
import Modele.Cardinaux;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.EntityPlayer;
import net.minecraft.server.v1_14_R1.PacketPlayOutOpenSignEditor;

public class Floor extends Build {




	//Les mur des 4 cotte
	Walls wNord = null;
	Walls wSouth= null;
	Walls wEast= null;
	Walls wWest= null;

	//Les bas des 4 cotte
	Floor fNord= null;
	Floor fSouth= null;
	Floor fEast= null;
	Floor fWest= null;

	int x=0,z=0;

	public Floor(Building buildig,int x,int y) {
		super(buildig);
		this.x = x;
		this.z = y;
		buildig.setCase(this.x, this.z, 2);
		System.out.println("New floor at "+x+" "+y);
	}

	public Floor(Building building,Floor parent,Cardinaux card) {
		super(building);
		setFloor(parent, card);
		this.x = parent.getX()+card.getX();
		this.z = parent.getY()+card.getZ();
		building.setCase(this.x, this.z, 1);
		System.out.println("New floor at "+x+" "+z);
	}


	@Override
	public void build() {


		for(Cardinaux card : Cardinaux.values()) {
			//on tente de genere les 4 mure
			if(isValid(card)) {
				Floor f = getFloor(card);
				f = new Floor(buildig,this,card);
				buildig.addFloor(f);
			}else {
				Walls w = getWalls(card);
				w = new Walls(buildig, card);
			}
		}



	}



	//sert a place un mur , la corrdone est celle du parent ex : si le parent genere un enfant au nort on passe le parametre north (donc le parent sera au sud de l'enfant)
	public void setFloor(Floor parent,Cardinaux card) {
		switch(card) {
		case North:
			fSouth = parent;
			wSouth = null;
			break;
		case South:
			fNord = parent;
			wNord = null;
			break;
		case West:
			fEast = parent;
			wEast = null;
			break;
		case East:
			fWest = parent;
			wWest = null;
			break;
		default:break;

		}
	}
	
	public Floor getFloor(Cardinaux card) {
		switch(card) {
		case North:
			return fNord; 
			
		
		case South:
			return fSouth;
			
	
		case West:
			return fWest;
			
	
		case East:
			return fEast;
		
			
		default:return null ;

		}
	}
	
	
	public Walls getWalls(Cardinaux card) {
		switch(card) {
		case North:
			return wNord; 
			
		
		case South:
			return wSouth;
			
	
		case West:
			return wWest;
			
	
		case East:
			return wEast;
		
			
		default:return null ;

		}
	}


	public boolean isValid(Cardinaux card) {
		int nx = x+card.getX();
		int ny = z + card.getZ();
		//on regade si la case ou lon veux construire notre nouvelle case est libre
		if(buildig.getCase(nx, ny)==0) {
			double rmd =  generateRandom();
			if(rmd>(1-getConfiguration().getProbabiliteSpawnNext())) {
				return true;
			}


		}

		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return z;
	}

	public void setY(int y) {
		this.z = y;
	}


	//Getter and Setter



}
