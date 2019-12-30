package command;
import org.bukkit.command.Command;
import GUI.LigneDuBas;
import GUI.MenuDeBase;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;


public class Menu implements CommandExecutor {

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			Player p = (Player) sender;


			if(p.hasPermission("building.generator")||p.isOp()) {
				new MenuDeBase(p);

			}
		}
		return false;


	}
}






