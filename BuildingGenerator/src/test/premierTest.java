package test;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.session.*;
import com.sk89q.worldedit.world.World;



public class premierTest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
			
			if(p.hasPermission("permitionExmple")||p.isOp()) {
				
				
		
				   LocalSession localSession = worldEdit.getSession(p);
				  
	                ClipboardHolder clipboardHolder;
					try {
					//	localSession.getSelectionWorld()
						//localSession.getSelection(world)
						RegionSelector rs = 	localSession.getRegionSelector(localSession.getSelectionWorld());
						
						
						/*
						clipboardHolder = localSession.getClipboard();
					
	                Clipboard clipboard = clipboardHolder.getClipboard();
	                Region region = clipboard.getRegion();
	                
	                BlockVector3 minP = region.getMinimumPoint();
					BlockVector3 maxP = region.getMaximumPoint();
					*/
						
						
						
						Region region = rs.getRegion();
		                
		                BlockVector3 minP = region.getMinimumPoint();
						BlockVector3 maxP = region.getMaximumPoint();
					
					p.sendMessage("min"+minP+" max "+maxP);
					} catch (IncompleteRegionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
		}
		// TODO Auto-generated method stub
		return false;
	}

}
