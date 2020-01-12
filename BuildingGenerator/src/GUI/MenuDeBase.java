package GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Utils.HeadList;
import net.md_5.bungee.api.chat.ClickEvent;

//Le menu en bas qui reste tout le temps le meme

public class MenuDeBase extends LigneDuBas {
	int size = 45;
	Inventory inv = null;
	static String name = "�7�lMenu";

	Player p;

	public MenuDeBase(Player p) {
		super(p);
		this.p = p;
		init(inv);
	}

	public void init(Inventory inv) {
		// TODO Auto-generated method stub
		inv = Bukkit.createInventory(p, size, name);
		fillInventory(inv);
		super.genereLigneDuBas(inv);

		p.openInventory(inv);

	}

	public void fillInventory(Inventory inv) {

		ItemStack france = HeadList.getHead("FRANCE");
		ItemStack commandblock = HeadList.getHead("COMMANDBLOCK");
		ItemStack book = HeadList.getHead("BOOK");

		ItemMeta it2 = france.getItemMeta();
		ItemMeta it3 = commandblock.getItemMeta();
		ItemMeta it4 = book.getItemMeta();
		it2.setDisplayName("�c�lLangue");
		france.setItemMeta(it2);
		it3.setDisplayName("�c�lConfiguration");
		commandblock.setItemMeta(it3);
		it4.setDisplayName("�c�lTutoriels");
		book.setItemMeta(it4);

		inv.setItem(20, france);
		inv.setItem(22, commandblock);
		inv.setItem(24, book);

	}

	public static void onInventoryClick(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		ItemStack it = e.getCurrentItem();

		e.setCancelled(true);

		if (it != null) {
			if (it.getType() == Material.PLAYER_HEAD) {
				String name = it.getItemMeta().getDisplayName();
				if (name.equalsIgnoreCase("�c�lMenu principale")) {
					p.sendMessage("�cErreur: Vous vous trouver d�j� au Menu Principale :c");
				}
				if (name.equalsIgnoreCase("�c�lPlanetMinecraft")) {
					p.sendMessage("�c�lMettre un lien clickable ici");
				}
				if (name.equalsIgnoreCase("�3�lTwitter")) {
					p.sendMessage("�3�lMettre un lien clickable ici");
				}
				if (name.equalsIgnoreCase("�r�lYou�c�lTube")) {
					p.sendMessage("�r�lMettre un lien clickable ici");
				}
				if (name.equalsIgnoreCase("�c�lConfiguration")) {
					new MenuConfiguration(p);
				}
			}
		}
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		MenuDeBase.name = name;
	}

}
