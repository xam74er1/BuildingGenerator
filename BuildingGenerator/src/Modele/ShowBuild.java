package Modele;

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

public class ShowBuild {
	WorldEditPlugin worldEdit;

	GamePlayer gp;
	Style style = null;
	String actual = null;
	int index = 0;
	Schematics shem = null;
	Location loc = null;
	public ShowBuild(GamePlayer gp) {
		super();
		this.gp = gp;
		worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
	
		style = gp.getConfiguration().getStyle();
	}
	
	public int show(String name,int pos) {
		return show(name,pos,0);
	}
	
	public int show(String name,int pos,int nvxIndex) {
		
		LocalSession localSession;
		EditSession editSession;
		index = nvxIndex;
	
		localSession = worldEdit.getSession(gp.getP());
		editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(gp.getP().getWorld()), -1);
		
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

		ArrayList<Schematics> list;

		if(name.equalsIgnoreCase("floor")) {
			list = style.getListFloor();
		}else if(name.contentEquals("walls")) {
			list = style.getListWalls();
		}else if(name.equalsIgnoreCase("rooft")) {
			list = style.getListRooft();
		}else {
			return -1;
		}
		
		if(list.size()!=0) {
			
		shem =	list.get(0);
		 loc = gp.getP().getLocation();
		
		Operation op = shem.pastWithRotation(loc.getBlockX()+1, loc.getBlockY(), loc.getBlockZ()+1, 0, editSession);
		
	
		
		try {
			Operations.complete(op);
			editSession.flushSession();
			localSession.remember(editSession);

			
		} catch (WorldEditException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		
		}
		
		}else {
			return -2;
		}
		
		actual = name;
		
		
		return 0;
	}
	
}
