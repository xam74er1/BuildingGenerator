package Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class GameConstante {

	public final static String schematicsPath = ((WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit")).getDataFolder().getAbsolutePath() + "\\schematics\\";
	public final static String folderName = "BuildingGenerator";
	public final static String pluginPath = "plugins/"+folderName;
	public final static String stylePath = pluginPath+"/Style";
	public final static String pluginName = "BuildingGenerator";
	public final static String invMenuName = "Building Generator Menu";

}
