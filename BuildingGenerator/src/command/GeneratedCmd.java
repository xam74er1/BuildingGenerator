package command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import Modele.Building;
import Modele.GamePlayer;
import fr.cr3art.spigot.Main;

public class GeneratedCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
		
			try {
				if (p.hasPermission("permitionExmple") || p.isOp()) {
					
				GamePlayer gp =	Main.getPlayer(p);
				p.sendMessage("Start");
				Building bd =	new Building(gp);
				bd.run();
			
					
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		// TODO Auto-generated method stub
		return false;
	}

}
