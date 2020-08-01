package Modele;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.EditSessionFactory;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extension.factory.parser.pattern.SingleBlockPatternParser;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.function.pattern.BlockPattern;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Polygonal2DRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;

import Modele.Build.BuildType;
import Utils.Log;

public class ShowBuild {
	WorldEditPlugin worldEdit;

	//game player
	GamePlayer gp;
	//style name
	Style style = null;
	//actual parts (walls , floor , rofts , ...)
	String actual = null;
	//index of the show 
	int index = 0;
	Schematics shem = null;
	Location loc = null;
	BuildType buildType = null;
	public ShowBuild(GamePlayer gp) {
		super();
		this.gp = gp;
		worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

		style = gp.getConfiguration().getStyle();
	}

	public int show(String name) {
		loc = gp.getPlayer().getLocation().add(1, 0, 1);
		return show(name,0);

	}

	public int show(String name,int nvxIndex) {

		LocalSession localSession;
		EditSession editSession;
		index = nvxIndex;


		localSession = worldEdit.getSession(gp.getPlayer());
		editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(gp.getPlayer().getWorld()), -1);



		//Si on a deja fait spawn un truc avant on le clear
		if(shem!=null) {

			try {

				BlockVector3 p2 = BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
				BlockVector3 p1 = BlockVector3.at(loc.getBlockX()+shem.getDimention().getBlockX(), loc.getBlockY()+shem.getDimention().getBlockY(), loc.getBlockZ()+shem.getDimention().getBlockZ());
				Region region = new CuboidRegion(p1 ,p2);
				Pattern fill = BlockTypes.AIR.getDefaultState();

				editSession.setBlocks(region,fill);
			} catch (MaxChangedBlocksException e) {
				// As of the blocks are unlimited this should not be called
			}
		}

		//on commnce a 0

		ArrayList<Schematics> list = getList(name);

		if(list!=null && list.size()!=0) {

			shem =	list.get(index);
			

			Operation op = shem.pastWithRotation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), 0, editSession);


			try {
				Operations.complete(op);
				editSession.flushSession();
				localSession.remember(editSession);


			} catch (WorldEditException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//erreur dans la stact trace
				return -3;

			}

		}else {
			Log.debug("la liste est vide ou null");
			return -2;
		}

		actual = name;


		return 0;
	}

	public int next() {
		Log.debug("loc : "+loc);
		if(actual==null) {
			Log.debug("no name");
			return -2;
		}
		ArrayList<Schematics> list = getList(actual);
		if(list!=null && list.size()>0) {
			int nextIndex = (index+1)%list.size();
			Log.debug("index : "+nextIndex+" / "+list.size());
			return show(actual,nextIndex);
		}

		return -2 ;//show()
	}


	public int prev() {
		Log.debug("loc : "+loc);
	
		if(actual==null) {
			return -2;
		}
		ArrayList<Schematics> list = getList(actual);
		if(list!=null && list.size()>0) {
			int nextIndex = (index-1);

			if(nextIndex<0) {
				nextIndex = list.size()-1;
			}
			return show(actual,nextIndex);
		}

		return -2 ;//show()
	}

	public ArrayList<Schematics> getList(String name) {


		if(name.equalsIgnoreCase("floor")) {
			this.buildType = BuildType.Floor;
			return style.getListFloor();
		}else if(name.contentEquals("walls")) {
			this.buildType = BuildType.Wall;
			return style.getListWalls();
		}else if(name.equalsIgnoreCase("rooft")) {
			this.buildType = BuildType.Rooft;
			return style.getListRooft();
		}else {
			return null;
		}
	}

	public int update() {

		LocalSession localSession;
		EditSession editSession;

Log.debug("loc : "+loc);
		localSession = worldEdit.getSession(gp.getPlayer());
		editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(gp.getPlayer().getWorld()), -1);
		if(shem!=null) {
			BlockVector3 p2 = BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			BlockVector3 p1 = BlockVector3.at(loc.getBlockX()+shem.getDimention().getBlockX()-1, loc.getBlockY()+shem.getDimention().getBlockY()-1, loc.getBlockZ()+shem.getDimention().getBlockZ()-1);
			Region region = new CuboidRegion(p2 ,p1);
			Log.debug("P1 :"+p1+" P2 "+p2);

			BukkitWorld world = new BukkitWorld(gp.getPlayer().getWorld());
			boolean res =	style.update(shem, region, world,buildType);

			if( ! res ) {
				//qq chose cest mal passe duran l'update
				return -1;
			}

		}else {

			//le schem n'eite pas
			return -4;
		}
		//Tout cest bien passe
		return 0;
	}
	
	public int delete() {
		
	ArrayList<Schematics> list =	getList(actual);
		
	Schematics schem = list.get(index);
	
	list.remove(index);
	
	File f = new File(schem.getPath());
	
	if(!f.delete()) {
		return -1;
	}
		
		return 0;
	}
}
