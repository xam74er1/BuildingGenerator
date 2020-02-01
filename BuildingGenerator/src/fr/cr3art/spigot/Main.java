package fr.cr3art.spigot;

import java.util.HashMap;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import Modele.GamePlayer;
import command.exmple;
import listener.*;

import command.Example;
import command.GeneratedCmd;
import command.Menu;
import listener.OnInventoryClick;


public class Main extends JavaPlugin {

	
static	HashMap<String,GamePlayer> playerMap ;
	
	@Override
	public void onEnable() {
		playerMap = new HashMap<String, GamePlayer>();

		//exmple a ajoute dans le plugin .yml sous le meme nom
		this.getCommand("exmple").setExecutor(new exmple());
	

		this.getCommand("Menu").setExecutor((CommandExecutor) new Menu());
		getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);



		
		//listener
				getServer().getPluginManager().registerEvents(new OnClick(), this);
				//getServer().getPluginManager().registerEvents(new OnJoin(), this);
				getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
		

				this.getCommand("generate").setExecutor(new GeneratedCmd());
				
				
			
				
		this.getCommand("Example").setExecutor(new Example());
		this.getCommand("Example").setTabCompleter(Example.tabCompleter);

	}
	
	public static GamePlayer getPlayer(Player p) {
		
		GamePlayer gp;
		if(playerMap.containsKey(p.getCustomName())) {
			gp = playerMap.get(p.getCustomName());
		}else {
			gp = new GamePlayer(p);
			playerMap.put(p.getCustomName(), gp);
		}
		return gp;
	}

}
