package listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import GUI.MenuConfiguration;
import GUI.MenuDeBase;
import GUI.ItemConfig.ItemConfig;
import Modele.GamePlayer;
import Utils.GameConstante;
import Utils.Log;
import fr.cr3art.spigot.Main;
import GUI.LigneDuBas;

public class OnInventoryClick implements Listener {

	public OnInventoryClick() {
		// TODO Auto-generated constructor stub
		Log.debug("On inetory click listener");
	}



	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {

		

			Player p = (Player) e.getWhoClicked();
			ItemStack it = e.getCurrentItem();

			Inventory inv = e.getInventory();

			InventoryView w = e.getView();

			GamePlayer gp = Main.getPlayer(p);

			//        if (inv.getType() == InventoryType.CHEST && w.getTitle().equalsIgnoreCase(LigneDuBas.getName())) {
			//            LigneDuBas.onInventoryClick(e);
			//        }
			//        if (inv.getType() == InventoryType.CHEST && w.getTitle().equalsIgnoreCase(MenuDeBase.getName())) {
			//            MenuDeBase.onInventoryClick(e);
			//        }
			//        if (inv.getType() == InventoryType.CHEST && w.getTitle().equalsIgnoreCase(MenuConfiguration.getName())) {
			//            MenuConfiguration.onInventoryClick(e);
			//        }


			if(inv.getType() == InventoryType.CHEST && w.getTitle().equalsIgnoreCase(GameConstante.invMenuName)) {



				ItemStack clickedItem =	e.getCurrentItem();
				Log.debug("on a clique ds le menus");

				if(clickedItem!=null) {
					String name = clickedItem.getItemMeta().getPersistentDataContainer().get(ItemConfig.nameSpace, PersistentDataType.STRING);

					Log.debug("le nom recupere est "+name);

					gp.fillItemCOnfigByName(name);

					
				}
				e.setCancelled(true);

			}else {
				Log.debug("Else listenr inv");
				//Chek if we are in config mode 

				Log.debug("title "+w.getTitle());
				ItemConfig itfg =   gp.getInventoryByName(w.getTitle());

				if(itfg!=null) {
					e.setCancelled(true);
					itfg.update(e);
			
				}else {
					Log.debug("Ce nest pas un item connus");
				}
			}

		}
	
}
