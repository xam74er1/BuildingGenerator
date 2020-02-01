package Modele;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;

import Modele.Build.Build;
import Modele.Build.Floor;
import Modele.Build.Walls;
import Utils.Log;

public class Building {
	GamePlayer gp;
	Floor start;
	//le terrin sert a savoir si il y a un build 
	//0 pas de build
	//1 il y a un build
	int terrin[][];
	int centreX;
	int centreY;

	int posX;
	int posY;
	int posZ;

	ArrayList<Floor> listFloor = new ArrayList<Floor>();
	ArrayList<Walls> listWalls = new ArrayList<Walls>();


	int indexGenerate = 1;


	WorldEditPlugin worldEdit;
	LocalSession localSession;
	EditSession editSession;

	ArrayList<Operation> listOperation = new ArrayList<Operation>();

	public Building(GamePlayer gp) {
		super();
		this.gp = gp;

		worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");


		centreX = getConfiguration().getMaxSize()/2;
		centreY = getConfiguration().getMaxSize()/2;

		terrin = new int[getConfiguration().getMaxSize()][getConfiguration().getMaxSize()];

		posX = gp.getP().getLocation().getBlockX();
		posY = gp.getP().getLocation().getBlockY();
		posZ = gp.getP().getLocation().getBlockZ();



		for(int i = 0;i<getConfiguration().getMaxSize();i++) {
			for(int j = 0;j<getConfiguration().getMaxSize();j++) {
				terrin[i][j] = 0;

			}
		}




		generate();
		build();
	}

	public void generate() {
		Log.print("Start generation ");
		start = new Floor(this, centreX, centreY);
		start.generate();

		//On genere toute la vase 
		while(indexGenerate<listFloor.size()) {
			System.out.println("---");
			Floor f = listFloor.get(indexGenerate);
			f.generate();
			indexGenerate++;
		}
		
		for(Floor f : listFloor) {
			f.generateParts();
		}
		Log.print("Fin generation");
	}

	public void build() {
		Log.print("Debut du build");
		//Initilisation
		localSession = worldEdit.getSession(gp.getP());
		editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(gp.getP().getWorld()), -1);

		//Action
		ArrayList<Build> listBuild = getAllBuild();

		for(Build b : listBuild) {
			b.build();

		}

		//Fin
		for(Operation op : listOperation) {
			try {

				Operations.complete(op);
			} catch (WorldEditException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		editSession.flushSession();
		localSession.remember(editSession);
		Log.print("Fin du build");
	}


	public Configuration getConfiguration() {
		return gp.getConfiguration();
	}


	public int getCase(int x,int y) {
		if(x<terrin[0].length&&x>=0&&y<terrin[0].length&&y>=0) {
			return terrin[x][y];
		}
		return -1;
	}


	public ArrayList<Build> getAllBuild(){

		ArrayList<Build> listBuild = new ArrayList<Build>();

		listBuild.addAll(listFloor);
		Log.print("Taille des walls"+listWalls.size());
		listBuild.addAll(listWalls);
		return listBuild;
	}

	public int setCase(int x,int y,int value) {
		if(x<terrin[0].length&&x>=0&&y<terrin[0].length&&y>=0) {
			terrin[x][y] = value;
			return 1;
		}
		return -1;
	}

	public void addFloor(Floor f) {
		listFloor.add(f);
	}

	public void addStage(Floor f) {
		addFloor(f);
	}


	public void addWalls(Walls w) {
		Log.print("Walls is add");
		listWalls.add(w);
	}

	public boolean addOperation(Operation e) {
		return listOperation.add(e);
	}

	//Getter and Setter


	public GamePlayer getGp() {
		return gp;
	}




	public void setGp(GamePlayer gp) {
		this.gp = gp;
	}




	public Floor getStart() {
		return start;
	}




	public void setStart(Floor start) {
		this.start = start;
	}




	public int[][] getTerrin() {
		return terrin;
	}




	public void setTerrin(int[][] terrin) {
		this.terrin = terrin;
	}




	public int getCentreX() {
		return centreX;
	}




	public void setCentreX(int centreX) {
		this.centreX = centreX;
	}




	public int getCentreY() {
		return centreY;
	}




	public void setCentreY(int centreY) {
		this.centreY = centreY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosZ() {
		return posZ;
	}

	public void setPosZ(int posZ) {
		this.posZ = posZ;
	}

	public ArrayList<Floor> getListGenerate() {
		return listFloor;
	}

	public void setListGenerate(ArrayList<Floor> listGenerate) {
		this.listFloor = listGenerate;
	}

	public int getIndexGenerate() {
		return indexGenerate;
	}

	public void setIndexGenerate(int indexGenerate) {
		this.indexGenerate = indexGenerate;
	}

	public LocalSession getLocalSession() {
		return localSession;
	}

	public void setLocalSession(LocalSession localSession) {
		this.localSession = localSession;
	}

	public EditSession getEditSession() {
		return editSession;
	}

	public void setEditSession(EditSession editSession) {
		this.editSession = editSession;
	}



}
