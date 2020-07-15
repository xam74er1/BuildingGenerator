package command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import Modele.GamePlayer;
import Modele.Build.BuildType;
import Utils.Log;
import fr.cr3art.spigot.Main;




public class ShowCmd implements CommandExecutor, ListArguments {
	


	static ArrayList<Argument> localListArg = new ArrayList<Argument>();

	public ShowCmd() {
		// TODO Auto-generated constructor stub
		add(0,"help");
		add(0,"list");
		add(0,"next");
		add(0,"prev");
		add(0,"type");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub

		if (sender instanceof Player) {
			Player p = (Player) sender;
			GamePlayer gp = Main.getPlayer(p);


			if(p.isOp()) {
				int pos = 0;
				if(isCorect(pos, "type" ,args)) {
					if(args.length>pos+1) {
							type(gp,args[pos+1]);
					}else {
						typeError(gp);
					}

				}

			}
		}

		return false;
	}


	public void typeError(GamePlayer gp) {

		String error = "show type <";
		for(String t : BuildType.allString()) {
			error += t+"|";
		}
		error+=">";
		String[] tmp = {error};
		gp.sendMessage("show.type.error",ChatColor.RED,tmp);
	
	}
	
	public void type(GamePlayer gp,String name) {
		
		if(BuildType.allString().contains(name)) {
			
			gp.getShowBuild().show(name, 0);
		}else {
			typeError(gp);
		}
		
	}
	
	
	public static TabCompleter tabCompleter = new TabCompleter() {



		@Override
		public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] args) {
			List<String> result = new ArrayList<String>();
			Log.print("onTabComplete called");

			if(args.length==2&&args[0].equalsIgnoreCase("type")) {
				result.addAll(BuildType.allString());
			}else {
				result.addAll(ListArguments.getAllForIndex(args.length-1,localListArg));
			}

			return result;
		}
	};
	@Override
	public void add(int pos, String value) {
		// TODO Auto-generated method stub
		Argument a = new Argument(pos, value);
		listArg.add(a);
		localListArg .add(a);

	}

	public void help(GamePlayer gp) {


		String str = "Show <";

		for(Argument s : localListArg) {
			str+=" "+s.getValue()+" |";
		}
		str+=">";

		gp.sendMessageError("show.help");
		gp.sendMessageError(str);
	}

}
