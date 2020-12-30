package fr.cr3art.spigot;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import Modele.GamePlayer;
import Modele.Style;
import Utils.GameConstante;
import Utils.I18nMessage;
import command.exmple;
import listener.*;

import command.Example;
import command.GeneratedCmd;
import command.GeneratedCustomCmd;
import command.Menu;
import command.ShowCmd;
import command.StyleCmd;
import command.cmdTestParticule;
import listener.OnInventoryClick;


public class Main extends JavaPlugin {


	static	HashMap<String,GamePlayer> playerMap ;
	
	Thread demon;

	@Override
	public void onEnable() {
		
		playerMap = new HashMap<String, GamePlayer>();
		
		
		ini();

		//exmple a ajoute dans le plugin .yml sous le meme nom
		this.getCommand("exmple").setExecutor(new exmple());


		this.getCommand("Menu").setExecutor((CommandExecutor) new Menu());
		



		this.getCommand("TestParicule").setExecutor((CommandExecutor) new cmdTestParticule());

		//listener
		OnClick lit = new OnClick();
		getServer().getPluginManager().registerEvents(lit, this);
		//getServer().getPluginManager().registerEvents(new OnJoin(), this);
		
		
		getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);


		this.getCommand("generate").setExecutor(new GeneratedCmd());
		
		this.getCommand("generateCustom").setExecutor(new GeneratedCustomCmd());


		this.getCommand("Style").setExecutor(new StyleCmd());
		this.getCommand("Style").setTabCompleter(StyleCmd.tabCompleter);


		this.getCommand("Example").setExecutor(new Example());
		this.getCommand("Example").setTabCompleter(Example.tabCompleter);
		
		

		this.getCommand("Show").setExecutor(new ShowCmd());
		this.getCommand("Show").setTabCompleter(ShowCmd.tabCompleter);
		
		
		//actionEvryTick();

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

	
	public void ini() {
		File root = new File(GameConstante.pluginPath);
		
		if(!root.exists()) {
			root.mkdir();
		}
		
		File style = new File(GameConstante.stylePath);
		
		if(!style.exists()) {
			style.mkdir();
		}
		
		generateDefaultStyle();
		
		new I18nMessage();
		
	}
	
	public void generateDefaultStyle() {
		Style.createStyle("default");
	}
	
	public void actionEvryTick() {
		demon = new Thread() {
			public void run() {
				System.out.println("Mon traitement");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		demon.setDaemon(true);
		
		demon.start();
	}

}


/*
 * Finir la zone en cube 
 * 
 */



