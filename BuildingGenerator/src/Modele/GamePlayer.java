package Modele;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import GUI.ItemConfig.ItemConfig;
import GUI.ItemConfig.ItemConfigProbaility;
import GUI.ItemConfig.ItemType;
import Utils.I18nMessage;
import Utils.Log;

public class GamePlayer {
	Player p ;
	Configuration configuration;
	I18nMessage message;

	HashMap<String,ItemConfig> mapItemConfig = new HashMap<String, ItemConfig>();

	public GamePlayer(Player p) {
		super();
		this.p = p;
		this.configuration = new Configuration();
		message = new I18nMessage();
		fillInvConfig();
	}


	public boolean saveSechematics(String category) {
		return getConfiguration().getStyle().save(category, p);
	}


	public void fillInvConfig() {
		ItemConfigProbaility it = new ItemConfigProbaility(ItemType.PERCENT,"pStage", Material.STONE, 0, configuration.getObjectProbabiliteSpawnLevel(),message.getMessage("Config.item.pStage"));
		mapItemConfig.put(it.getName(), it);

		it = new ItemConfigProbaility(ItemType.PERCENT,"pNext", Material.ACACIA_PLANKS, 0, configuration.getObjectProbabiliteSpawnNext(),message.getMessage("Config.item.pNext"));
		mapItemConfig.put(it.getName(), it);
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
		return mapItemConfig.get(str);
	}
	
	public boolean fillItemCOnfigByName(String name) {
		ItemConfig it=  mapItemConfig.get(name);
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


}
