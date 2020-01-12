package fr.cr3art.spigot;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import command.Example;
import command.Menu;
import listener.OnInventoryClick;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		this.getCommand("Menu").setExecutor((CommandExecutor) new Menu());
		getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);

		this.getCommand("Example").setExecutor(new Example());
		this.getCommand("Example").setTabCompleter(Example.tabCompleter);
	}

}
