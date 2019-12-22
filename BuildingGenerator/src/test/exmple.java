package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.session.*;
import com.sk89q.worldedit.world.World;



public class exmple implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			Player p = (Player) sender;
			WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

			if(p.hasPermission("permitionExmple")||p.isOp()) {

				System.out.println("start");
				try {
					LocalSession localSession = worldEdit.getSession(p);

					//	LocalSession localSession  = new LocalSession();



					ClipboardHolder clipboardHolder;
					String path = worldEdit.getDataFolder().getAbsolutePath()+"\\schematics\\base.schem";
					System.out.println("path : "+path);
					File file =new File(path) ;
					Clipboard clipboard;

					int x = p.getLocation().getBlockX();
					int y = p.getLocation().getBlockY();
					int z = p.getLocation().getBlockZ();

					ClipboardFormat format = ClipboardFormats.findByFile(file);

					ClipboardReader reader = format.getReader(new FileInputStream(file)) ;
					clipboard = reader.read();
					System.out.println("clipbard = "+clipboard.getDimensions());



					EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(p.getWorld()), -1);

					Operation operation = new ClipboardHolder(clipboard)
							.createPaste(editSession)
							.to(BlockVector3.at(x, y, z))
							.ignoreAirBlocks(false)
							.build();
					Operations.complete(operation);


					editSession.undo(editSession);


					p.sendMessage(" x :"+x+" y: "+y+" z "+z+" ");

					System.out.println("end");

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WorldEditException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		// TODO Auto-generated method stub
		return false;
	}

}
