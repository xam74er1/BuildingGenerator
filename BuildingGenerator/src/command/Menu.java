package command;

import org.bukkit.command.Command;
import GUI.MenuDeBase;
import GUI.ItemConfig.ItemConfig;
import Modele.GamePlayer;
import fr.cr3art.spigot.Main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;

public class Menu implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (p.hasPermission("building.generator") || p.isOp()) {
				GamePlayer gp = Main.getPlayer(p);

				if(args.length>0) {
					gp.fillItemCOnfigByName(args[0]);

				}else {
				gp.sendMessageError("pas asse d'argument");
				}
			}
		
		}
		return false;
	}
}