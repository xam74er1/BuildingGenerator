package Modele;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BlockState;

import Modele.Build.Floor;
import Utils.Log;
import net.minecraft.server.v1_14_R1.Material;

public class Building_Custom extends Building{

	public Building_Custom(GamePlayer gp) {


		super(gp);
		// TODO Auto-generated constructor stub



	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		annalyse();
		build();
	}


	public void annalyse() {

		Player p = gp.getP();
		int oldCongiuration = gp.getConfiguration().getMaxSize();
		WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		LocalSession localSession = worldEdit.getSession(p);

		try {
			Region region =	localSession.getRegionSelector(new BukkitWorld(p.getWorld())).getRegion();

			centreX = region.getLength()/2;
			centreY = region.getWidth()/2;

			//	terrin = new int[region.getLength()][region.getHeight()][region.getWidth()];




			//On set un nvx max
			gp.getConfiguration().setMaxSize(Math.max(Math.max(region.getLength(), region.getHeight()),region.getWidth())+1);
			terrin = new int[getConfiguration().getMaxSize()][getConfiguration().getMaxSize()][getConfiguration().getMaxSize()];



			int vy = 0;

			Log.debug("min"+region.getMinimumPoint()+" max "+region.getMaximumPoint());
			for(int y = region.getMinimumPoint().getBlockY();y<=region.getMaximumPoint().getBlockY();y++) {
				int vx = 0;

				for(int x = region.getMinimumPoint().getBlockX();x<=region.getMaximumPoint().getBlockX();x++) {

					int vz = 0;
					for(int z = region.getMinimumPoint().getBlockZ();z<=region.getMaximumPoint().getBlockZ();z++) {

						BlockState bs =	region.getWorld().getBlock(BlockVector3.at(x, y, z));

						if(!bs.getBlockType().getMaterial().isAir() ) {
							//	terrin[vx][vy][vz] = vy+1;
							new Floor(this,vx,vy,vz);
						}else {
							terrin[vx][vy][vz] = 0;
						}
						vz++;
					}
					vx++;
				}

				vy++;
			}


			String str ="";
			for(int x = 0;x<getConfiguration().getMaxSize();x++) {
				str+="\n";
				for(int z = 0;z<getConfiguration().getMaxSize();z++) {
					str +=terrin[x][0][z]+" ";

				}

			}
			System.out.println(str);

			for(Floor f : listFloor) {
				f.generateParts();
			}

		} catch (IncompleteRegionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gp.getConfiguration().setMaxLevel(oldCongiuration);

	}

}
