package Modele;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import GUI.MenuConfiguration;
import GUI.ItemConfig.ItemConfig;
import GUI.ItemConfig.ItemConfigProbaility;
import GUI.ItemConfig.ItemType;
import Utils.GameConstante;
import Utils.I18nMessage;
import Utils.Log;

public class GamePlayer {
	Player p ;
	Configuration configuration;
	I18nMessage message;
	MenuConfiguration menuConfig;
	ShowBuild showBuild;

	public GamePlayer(Player p) {
		super();
		this.p = p;
		this.configuration = new Configuration();
		message = new I18nMessage();
		menuConfig = new MenuConfiguration(this);
		showBuild = new ShowBuild(this);
	}


	public boolean saveSechematics(String category) {
		return getConfiguration().getStyle().save(category, p);
	}



	public void back() {
		p.closeInventory();
		p.openInventory(menuConfig.getMenuInv());
	}

	///////////////////////////::::
	public void sendMessage(String key) {
		p.sendMessage(message.getMessage(key));
	}

	public void sendMessage(String key,ChatColor color) {
		p.sendMessage(color+message.getMessage(key));
	}

	public void sendMessage(String key,ChatColor color,String [] args) {

		String str = message.getMessage(key);

		Log.debug("length"+args.length+" 0 "+args[0]);

		for(int i =0;i<args.length;i++) {
			str = str.replace("{"+i+"}", args[i]);
		}

		p.sendMessage(color+str);
	}


	public void sendMessageError(String key) {
		p.sendMessage(ChatColor.RED+message.getMessage(key));
	}

	public void sendMessageSucesse(String key) {
		p.sendMessage(ChatColor.GREEN+message.getMessage(key));
	}

	public ItemConfig getInventoryByName(String str) {

		return menuConfig.getMapItemConfig().get(str);
	}

	public boolean fillItemCOnfigByName(String name) {
		ItemConfig it=  menuConfig.getMapItemConfig().get(name);
		if(it!=null) {
			it.fill(this);
			return true;
		}
		return false;
	}

	///////////

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}


	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}


	public I18nMessage getMessage() {
		return message;
	}


	public void setMessage(I18nMessage message) {
		this.message = message;
	}


	public HashMap<String, ItemConfig> getMapItemConfig() {
		return menuConfig.getMapItemConfig();
	}


	public void setMapItemConfig(HashMap<String, ItemConfig> mapItemConfig) {
		this.menuConfig.setMapItemConfig(mapItemConfig);
	}


	public Inventory getMenuInv() {
		return menuConfig.getMenuInv();
	}


	public void setMenuInv(Inventory menuInv) {
		menuConfig.setMenuInv(menuInv);
	}


	public MenuConfiguration getMenuConfig() {
		return menuConfig;
	}


	public void setMenuConfig(MenuConfiguration menuConfig) {
		this.menuConfig = menuConfig;
	}


	public ShowBuild getShowBuild() {
		return showBuild;
	}


	public void setShowBuild(ShowBuild showBuild) {
		this.showBuild = showBuild;
	}


}
