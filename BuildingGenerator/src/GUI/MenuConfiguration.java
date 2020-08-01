package GUI;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import GUI.ItemConfig.ItemConfig;
import GUI.ItemConfig.ItemConfigInteger;
import GUI.ItemConfig.ItemConfigProbaility;
import GUI.ItemConfig.ItemType;
import Modele.Configuration;
import Modele.GamePlayer;
import Utils.GameConstante;
import Utils.I18nMessage;

public class MenuConfiguration  {
	Inventory menuInv ;
	HashMap<String,ItemConfig> mapItemConfig = new HashMap<String, ItemConfig>();
	Configuration configuration;
	I18nMessage message;
	GamePlayer gp;

	public MenuConfiguration(GamePlayer gp) {
		this.gp = gp;
		this.configuration = gp.getConfiguration();
		this.message = gp.getMessage();
		fillInvConfig();

	}

	public void init(Inventory inv) {
		// TODO Auto-generated method stub

	}

	public void fillInvConfig() {

		menuInv = Bukkit.createInventory(gp.getPlayer(), 27,GameConstante.invMenuName);
		//on cree nos item

		ItemConfig it = new ItemConfigProbaility(ItemType.PERCENT,"pStage", Material.STONE, 0, configuration.getObjectProbabiliteSpawnLevel(),message.getMessage("Config.item.pStage"));
		mapItemConfig.put(it.getName(), it);


		it = new ItemConfigProbaility(ItemType.PERCENT,"pNext", Material.ACACIA_PLANKS, 1, configuration.getObjectProbabiliteSpawnNext(),message.getMessage("Config.item.pNext"));
		mapItemConfig.put(it.getName(), it);

		it = new ItemConfigInteger(ItemType.INT, "maxLvl", Material.GOLD_BLOCK, 2, configuration.getObjectMaxLevel(),message.getMessage("Config.item.maxLvl"));
		mapItemConfig.put(it.getName(), it);

		it = new ItemConfigInteger(ItemType.INT, "maxSize", Material.IRON_BLOCK, 3, configuration.getObjectMaxSize(),message.getMessage("Config.item.maxSize"));
		mapItemConfig.put(it.getName(), it);
		//on remplis de glasse noir

		fillBlackGlass(menuInv);

		//on le place rectment 

		int l = 1;
		int c = 3;
		int i = 0;
		int place = 4;
		for(ItemConfig icp : mapItemConfig.values()) {

			int index = (l+(i/place))*9+c+(i%place);
			menuInv.setItem(index,icp.getItem());
			i++;
		}
	}


	public void fillBlackGlass(Inventory inv) {
		for(int i =0;i<inv.getSize();i++) {
			inv.setItem(i,new ItemStack(Material.BLACK_STAINED_GLASS_PANE) );
		}
	}

	public static void onInventoryClick(InventoryClickEvent e) {

	}

	public Inventory getMenuInv() {
		return menuInv;
	}

	public void setMenuInv(Inventory menuInv) {
		this.menuInv = menuInv;
	}

	public HashMap<String, ItemConfig> getMapItemConfig() {
		return mapItemConfig;
	}

	public void setMapItemConfig(HashMap<String, ItemConfig> mapItemConfig) {
		this.mapItemConfig = mapItemConfig;
	}


}
