package GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Utils.HeadList;

//Prendre exple sur cette classe la 
public class LigneDuBas {
	Player p;
	int size = 45;
	Inventory inv = null;
	static String name = "�7�lMenu";

	public LigneDuBas(Player p) {

		this.p = p;

	}

	public void ini() {
		p.sendMessage("test");
		inv = Bukkit.createInventory(p, size, name);
		genereLigneDuBas(inv);

		p.openInventory(inv);
	}

	public void genereLigneDuBas(Inventory inv) {
		System.out.println("Genere ligne du bas");
		ItemStack gray_stained_glass_pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
		ItemMeta im = gray_stained_glass_pane.getItemMeta();
		im.setDisplayName("�0---");

		for (int i = 0; i < 9; i++) {
			inv.setItem(i, gray_stained_glass_pane);
		}
		for (int i = 36; i < 45; i++) {
			inv.setItem(i, gray_stained_glass_pane);
		}

		ItemStack writable_book = new ItemStack(Material.WRITABLE_BOOK, 1);
		ItemMeta it8 = writable_book.getItemMeta();
		it8.setDisplayName("�6BuildGenerator/main");
		writable_book.setItemMeta(it8);
		ItemStack house = HeadList.getHead("HOUSE");
		ItemStack left = HeadList.getHead("LEFT");
		ItemStack planet = HeadList.getHead("PLANET");
		ItemStack twitter = HeadList.getHead("TWITTER");
		ItemStack youtube = HeadList.getHead("YOUTUBE");
		ItemMeta it = house.getItemMeta();
		ItemMeta it1 = youtube.getItemMeta();

		ItemMeta it5 = left.getItemMeta();
		ItemMeta it6 = planet.getItemMeta();
		ItemMeta it7 = twitter.getItemMeta();

		it.setDisplayName("�c�lMenu principale");
		house.setItemMeta(it);
		it1.setDisplayName("�r�lYou�c�lTube");
		youtube.setItemMeta(it1);

		it5.setDisplayName("�c�lRetour en arri�re");
		left.setItemMeta(it5);
		it6.setDisplayName("�c�lPlanetMinecraft");
		planet.setItemMeta(it6);
		it7.setDisplayName("�3�lTwitter");
		twitter.setItemMeta(it7);

		inv.setItem(4, writable_book);
		inv.setItem(36, left);
		inv.setItem(40, house);
		inv.setItem(42, planet);
		inv.setItem(43, twitter);
		inv.setItem(44, youtube);
		gray_stained_glass_pane.setItemMeta(im);

	}

	public static void onInventoryClick(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		ItemStack it = e.getCurrentItem();

		e.setCancelled(true);
		if (it != null) {
			if (it.getType() == Material.BLUE_STAINED_GLASS_PANE) {

				p.sendMessage(ChatColor.BLUE + "");

			}
		}
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		LigneDuBas.name = name;
	}

}
