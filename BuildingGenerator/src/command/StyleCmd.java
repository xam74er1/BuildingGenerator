package command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import Modele.GamePlayer;
import Modele.Style;
import Utils.Log;
import Utils.StyleExeption;
import fr.cr3art.spigot.Main;


public class StyleCmd implements CommandExecutor , ListArguments{

	static ArrayList<Argument> localListArg = new ArrayList<Argument>();
	public StyleCmd() {
		add(0,"help");
		add(0,"list");
		add(0,"create");
		add(0,"load");

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


		if (sender instanceof Player) {
			Player p = (Player) sender;
			GamePlayer gp = Main.getPlayer(p);

			for(String s : args) {
				Log.debug(s);
			}
			
			if(p.isOp()) {
				int pos = 0;
				if(isCorect(pos, "list" ,args)) {
					list(gp);
				}else if(isCorect(pos, "create" ,args)) {
					
					if(args.length>pos+1) {
						create(gp, args[pos+1]);
					}else {
						gp.sendMessageError("style.create.error");
					}
					
				}else if(isCorect(pos, "load" ,args)) {
					if(args.length>pos+1) {
						create(gp, args[pos+1]);
					}else {
						gp.sendMessageError("style.create.error");
					}
				}else {
					help(gp);
				}
			}

		}
		return false;
	}

	public static TabCompleter tabCompleter = new TabCompleter() {



		@Override
		public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
			List<String> result = new ArrayList<String>();
			Log.print("onTabComplete called");

			result.addAll(ListArguments.getAllForIndex(arg3.length-1,localListArg));

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
	
	public void load(GamePlayer gp,String name) {
		Style s = Style.getStyle(name);
		
	
			try {
				gp.getConfiguration().setStyle(name);
				gp.sendMessageSucesse("style.load.sucesse");
			} catch (StyleExeption e) {
				// TODO Auto-generated catch block
				String [] arg = {name};
				gp.sendMessage("style.load.nofound",ChatColor.RED,arg);
			
			}
		
	}
	
	public void help(GamePlayer gp) {
		
		
		String str = "Style <";
		
		for(Argument s : localListArg) {
		str+=" "+s.getValue()+" |";
		}
		str+=">";
		
		gp.sendMessageError("style.help");
		gp.sendMessageError(str);
	}
	
	public void create(GamePlayer gp,String name) {
		Style.createStyle(name);
		gp.sendMessageSucesse("style.cerate.sucesse");
	}


	public void list(GamePlayer gp ) {

		gp.sendMessage("style.list",ChatColor.DARK_GREEN);

		boolean ligth = true;
		ChatColor c;

		for(String s : Style.listStyle()) {

			if(ligth) {
				c = ChatColor.GREEN;
			}else {
				c = ChatColor.DARK_GREEN;
			}

			ligth = ! ligth;

			//On affiche tout 
			gp.getP().sendMessage(c+"-"+s);

		}

	}


}
