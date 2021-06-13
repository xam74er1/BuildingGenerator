package Modele;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

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
import Modele.Build.Rooft;
import Modele.Build.Walls;
import Utils.Log;
import fr.cr3art.spigot.Main;

public class Building {
	GamePlayer gp;
	Floor start;
	//le terrin sert a savoir si il y a un build 
	//0 pas de build
	//1 il y a un build
	int terrin[][][];
	int centreX;
	int centreY;

	int posX;
	int posY;
	int posZ;

	ArrayList<Floor> listFloor = new ArrayList<Floor>();
	ArrayList<Walls> listWalls = new ArrayList<Walls>();
	ArrayList<Rooft> listRooft = new ArrayList<Rooft>();


	int indexGenerate = 1;


	WorldEditPlugin worldEdit;
	LocalSession localSession;
	EditSession editSession;

	ArrayList<Operation> listOperation = new ArrayList<Operation>();
	
	boolean noPbr = true;

	//COnstruteur 
	public Building(GamePlayer gp) {
		super();
		this.gp = gp;

		//On inilise WE
		worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");


		//On cree un centre
		centreX = getConfiguration().getMaxSize()/2;
		centreY = getConfiguration().getMaxSize()/2;

		//On genere virutllment un terrin
		terrin = new int[getConfiguration().getMaxSize()][getConfiguration().getMaxSize()][getConfiguration().getMaxSize()];

		//On recupre les postion du player
		posX = gp.getPlayer().getLocation().getBlockX();
		posY = gp.getPlayer().getLocation().getBlockY();
		posZ = gp.getPlayer().getLocation().getBlockZ();


//On met tt les case du terrin a 0
		for(int i = 0;i<getConfiguration().getMaxSize();i++) {
			for(int j = 0;j<getConfiguration().getMaxSize();j++) {
				for(int k = 0;k<getConfiguration().getMaxSize();k++) {
					terrin[i][j][k] = 0;
				}
				

			}
		}




	}


	public void run() {
		//Et cest partis 
				//Pour du debug
		Floor.cnt = 0;
		   BukkitScheduler scheduler = Bukkit.getScheduler();
	        scheduler.scheduleSyncDelayedTask(Main.me, new Runnable() {
	            @Override
	            public void run() {
	            	generate();
					build();
					gp.sendMessageSucesse("building end");
	            }
	        }, 0L);
				
		
	}
	
	//on genere vrituelllment tout le battiment 
	public void generate() {
		Log.print("Start generation ");
		start = new Floor(this, centreX, centreY);
		start.generate();

//Pour evite un bug ou cela tourne en round , on verifie quil ne fait pas plus de tour que le nbr max de case possible		
		int max = getConfiguration().getMaxSize();
		//dimMax^3
		max = max*max*max;
		
		//On genere toute la vase 
		while(indexGenerate<listFloor.size()&&indexGenerate<max) {
			System.out.println("---"+indexGenerate);
			Floor f = listFloor.get(indexGenerate);
			f.generate();
			
			indexGenerate++;
		}
		
		for(Floor f : listFloor) {
			f.generateParts();
		}
		Log.print("Fin generation");
	}

	//Puis on constuis le battiment genere
	public void build() {
		Log.print("Debut du build");
		//Initilisation
		localSession = worldEdit.getSession(gp.getPlayer());
		editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(gp.getPlayer().getWorld()), -1);

		//Action
		ArrayList<Build> listBuild = getAllBuild();
//on paste tout les schematics
		for(Build b : listBuild) {
			try {
			b.build();
			}catch(Exception e) {
				e.printStackTrace();
			noPbr = false;
			}

		}

	//On execute tout les operation 
		for(Operation op : listOperation) {
			try {

				Operations.complete(op);
			} catch (WorldEditException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				noPbr = false;
			}
		}
//On fini la setion et on save pour le undo
		editSession.flushSession();
		localSession.remember(editSession);
		Log.print("Fin du build");
	}


	public Configuration getConfiguration() {
		return gp.getConfiguration();
	}


	public int getCase(int x,int y,int z) {
		
		if(x<terrin[0].length&&x>=0&&y<terrin[0].length&&y>=0&&z<terrin[0].length&&z>=0) {
			return terrin[x][z][y];
		}
		return -1;
	}


	public ArrayList<Build> getAllBuild(){

		ArrayList<Build> listBuild = new ArrayList<Build>();

		listBuild.addAll(listFloor);
		listBuild.addAll(listWalls);
		listBuild.addAll(listRooft);
		return listBuild;
	}

	public int setCase(int x,int y,int z,int value) {
		//if(x<terrin[0].length&&x>=0&&y<terrin[0].length&&y>=0) {
		if(x<terrin[0].length&&x>=0&&y<terrin[0].length&&y>=0&&z<terrin[0].length&&z>=0) {
			terrin[x][z][y] = value+1;
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
	
		listWalls.add(w);
	}

	
	public void addRooft(Rooft r) {
		listRooft.add(r);
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




	public int[][][] getTerrin() {
		return terrin;
	}




	public void setTerrin(int[][][] terrin) {
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
