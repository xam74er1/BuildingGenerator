package GUI;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MenuMain extends MenuDeBase{
	Player p;
	Inventory inv;
	
	
	
	
	public MenuMain(Player p) {
		super(p);
		this.p = p;
		ini();
	}


	public static void onInventoryClick(InventoryClickEvent e) {
		// TODO Auto-generated method stub
		//METRE DES TRUC ICI
		
	}

	@Override
	public void ini() {
		// TODO Auto-generated method stub
		//MEtre des truc ici
	}

	@Override
	public void fillInventory(Inventory inv) {
		// TODO Auto-generated method stub
		//METRE DES TRUC ICI
	}

}
