package command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Modele.Building;
import Modele.Building_Custom;
import Modele.GamePlayer;
import fr.cr3art.spigot.Main;

public class GeneratedCustomCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
		
			try {
			
				if (p.hasPermission("permitionExmple") || p.isOp()) {
					
				GamePlayer gp =	Main.getPlayer(p);
				p.sendMessage("Start");
				Building_Custom bdc =	new Building_Custom(gp);
				bdc.run();
			
					p.sendMessage("End Pense a mettre un message avec un try and catch au cas ou on a pas de selection WE");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		// TODO Auto-generated method stub
		return false;
	}

}
