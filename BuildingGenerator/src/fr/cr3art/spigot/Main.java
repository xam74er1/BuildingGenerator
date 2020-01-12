package fr.cr3art.spigot;

import org.bukkit.plugin.java.JavaPlugin;


import command.exmple;
import listener.*;



public class Main extends JavaPlugin{

	@Override
	public void onEnable() {

		//exmple a ajoute dans le plugin .yml sous le meme nom
		this.getCommand("exmple").setExecutor(new exmple());
	

		
		//listener
				getServer().getPluginManager().registerEvents(new OnClick(), this);
				//getServer().getPluginManager().registerEvents(new OnJoin(), this);
				getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
		
	}

}
