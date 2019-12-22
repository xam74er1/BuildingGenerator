package fr.cr3art.spigot;

import org.bukkit.plugin.java.JavaPlugin;

import command.exmple;



public class Main extends JavaPlugin{

	@Override
	public void onEnable() {

		//exmple a ajoute dans le plugin .yml sous le meme nom
		this.getCommand("exmple").setExecutor(new exmple());

	}

}
