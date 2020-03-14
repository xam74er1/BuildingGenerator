package command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_14_R1.block.CraftCommandBlock;
import org.bukkit.craftbukkit.v1_14_R1.command.CraftBlockCommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.Vector3;

import Utils.Log;
import Utils.ParticuleDraw;

public class cmdTestParticule implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
			LocalSession localSession = worldEdit.getSession(p);

			localSession.getRegionSelector(new BukkitWorld(p.getWorld()));

			BlockVector3 min;
			try {
				min = localSession.getRegionSelector(new BukkitWorld(p.getWorld())).getRegion().getMinimumPoint();
				BlockVector3 max =	localSession.getRegionSelector(new BukkitWorld(p.getWorld())).getRegion().getMaximumPoint();

				Log.print("min "+min+"max"+max);

				Location start = new Location(p.getWorld(),min.getX(),min.getY(),min.getZ());
				Location end = new Location(p.getWorld(),max.getX(),max.getY(),max.getZ());
				ParticuleDraw.drawCube(start, end);

			} catch (IncompleteRegionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//			


		}else if(sender instanceof CraftBlockCommandSender){
			CraftBlockCommandSender b = (CraftBlockCommandSender) sender;


			Location start = new Location(b.getBlock().getWorld(),106,82,-1273);
			Location end = start.clone();
			//106,82,-1273
			end= end.add(10, 15, 5);


			ParticuleDraw.drawCube(start, end);

		}
		// TODO Auto-generated method stub
		return false;
	}

}
