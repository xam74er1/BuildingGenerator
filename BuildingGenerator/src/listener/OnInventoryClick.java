package listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import GUI.MenuConfiguration;
import GUI.MenuDeBase;
import GUI.ItemConfig.ItemConfig;
import Modele.GamePlayer;
import fr.cr3art.spigot.Main;
import GUI.LigneDuBas;

public class OnInventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();

        Inventory inv = e.getInventory();

        InventoryView w = e.getView();

        if (inv.getType() == InventoryType.CHEST && w.getTitle().equalsIgnoreCase(LigneDuBas.getName())) {
            LigneDuBas.onInventoryClick(e);
        }
        if (inv.getType() == InventoryType.CHEST && w.getTitle().equalsIgnoreCase(MenuDeBase.getName())) {
            MenuDeBase.onInventoryClick(e);
        }
        if (inv.getType() == InventoryType.CHEST && w.getTitle().equalsIgnoreCase(MenuConfiguration.getName())) {
            MenuConfiguration.onInventoryClick(e);
        }
        
        //Chek if we are in config mode 
        
        GamePlayer gp = Main.getPlayer(p);
        
     ItemConfig itfg =   gp.getInventoryByName(w.getTitle());
     
     if(itfg!=null) {
    	itfg.update(e);
     }

    }
}
