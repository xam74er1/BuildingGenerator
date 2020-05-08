package Modele.Build;



import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;

import Modele.Building;
import Modele.Cardinaux;
import Modele.Schematics;
import Utils.Log;
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

	Stage etage = null;
	
	Rooft rooft = null;

	int virtualX=0,virtualZ=0;
	int x = 0;int y =0;int z = 0;

	int level = 1;

	Schematics schem;
	
	public Floor(Building buildig,int x,int level,int z) {
		super(buildig);
		this.virtualX = x;
		this.virtualZ = z;

		schem = getStyle().getRandomFloor();

		this.x = (virtualX-getConfiguration().getMaxSize()/2) * schem.getDimention().getBlockX()+buildig.getPosX();
		this.z = (virtualZ-getConfiguration().getMaxSize()/2)*schem.getDimention().getBlockZ()+buildig.getPosZ();

		this.level = level;

		ini();

		Log.print("End of constructor");
	}
	

	public Floor(Building buildig,int x,int z) {
		super(buildig);
		this.virtualX = x;
		this.virtualZ = z;

		schem = getStyle().getRandomFloor();

		this.x = (virtualX-getConfiguration().getMaxSize()/2) * schem.getDimention().getBlockX()+buildig.getPosX();
		this.z = (virtualZ-getConfiguration().getMaxSize()/2)*schem.getDimention().getBlockZ()+buildig.getPosZ();

		ini();

		Log.print("End of constructor");
	}

	public Floor(Building building,Floor parent,Cardinaux card) {
		super(building);
		setFloor(parent, card);
		schem = getStyle().getRandomFloor();

		this.virtualX = parent.getvirutalX()+card.getX();
		this.virtualZ = parent.getvirtualY()+card.getZ();

		ini();



	}

//Principalment pour l'etage
	public Floor(Building building,Floor parent) {
		super(building);

		schem = getStyle().getRandomFloor();

		this.virtualX = parent.getvirutalX();
		this.virtualZ = parent.getvirtualY();

		ini();



	}

	//Initilation pour factorise
	public void ini() {

		Log.print("Stage : "+getStage()+"\n\n");
		this.x = (virtualX-getConfiguration().getMaxSize()/2) * schem.getDimention().getBlockX()+buildig.getPosX();
		this.z = (virtualZ-getConfiguration().getMaxSize()/2)*schem.getDimention().getBlockZ()+buildig.getPosZ();
		this.y = buildig.getPosY()+level*getStyle().getWallsSize();

		buildig.setCase(this.virtualX, getStage(),this.virtualZ,getStage());
		buildig.addFloor(this);
	}


	@Override
	public void generate() {
		Log.print("Generate start floor");

		for(Cardinaux card : Cardinaux.values()) {
			//Si on a aucun sol qui a ete genere 
			if(getFloor(card)==null) {
				//on tente de genere les un sol si non on met un mur 
				if(isValid(card)) {
					Floor f = getFloor(card);
					f = new Floor(buildig,this,card);

				}

			}
		}


		generateStage();
	}

	@Override
	public void generateParts() {

		for(Cardinaux card : Cardinaux.values()) {
			int nx = virtualX+card.getX();
			int nz = virtualZ + card.getZ();
			//if(buildig.getCase(nx, level,nz)<=0) {
			if(buildig.getCase(nx, getStage(),nz)<=0) {
			
				Walls w = getWalls(card);
				w = new Walls(buildig, card, this);
			}
		}
		generateRooft();
	}

	@Override
	public void build() {



		ClipboardHolder ch2 = new ClipboardHolder(schem.getCliboard());
		Operation operation = ch2.createPaste(buildig.getEditSession()).to(schem.centreAtPosition(x, y, z, schem.getCliboard()))
				.ignoreAirBlocks(false).build();

		buildig.addOperation(operation);

	}


	public void generateStage() {
		Log.print("Start stage");
		double rmd =  generateRandom();
		if(level<getConfiguration().getMaxLevel()&&rmd<getConfiguration().getProbabiliteSpawnLevel()) {
			etage = new Stage(buildig, this);
			Log.print("Stage  generated !! "+level);
		}else {
			Log.print("Stage NOT generated");
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
		int nx = virtualX+card.getX();
		int nz = virtualZ + card.getZ();


		//on regade si la case ou lon veux construire notre nouvelle case est libre
		if(buildig.getCase(nx,0, nz)==0) {
			double rmd =  generateRandom();
			if(rmd>(1-getConfiguration().getProbabiliteSpawnNext())) {
				return true;
			}


		}

		return false;
	}
	
	
	public void generateRooft(){
		
		if(this.etage==null) {
			rooft = new Rooft(buildig,this);
		}
		
	}
	
	
	
	
	
	

	public int getvirutalX() {
		return virtualX;
	}



	public int getvirtualY() {
		return virtualZ;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}


	public BlockVector3 getDimention() {
		return schem.getDimention();
	}

	public BlockVector3 getCentre() {
		return schem.getCentre();
	}

	public int getStage() {
		return level;
	}

	public void setStage(int stage) {
		this.level = stage;
	}



	//Getter and Setter



}
