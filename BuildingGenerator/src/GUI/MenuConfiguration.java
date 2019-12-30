package GUI;



import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;




public class MenuConfiguration extends  MenuDeBase{
	int size =45;
	Inventory inv = null;
	static String name ="§7§lConfiguration";
	Player p;
	
	



	public MenuConfiguration(Player p) {
		super(p);
		this.p = p;
		p.sendMessage("Menu config");
	}




	public void init(Inventory inv) {
		// TODO Auto-generated method stub

	}




	public static void onInventoryClick(InventoryClickEvent e)  {

		Player p = (Player) e.getWhoClicked();
		ItemStack it = e.getCurrentItem();

		e.setCancelled(true);
		if(it!=null) {
			if (it.getType()==Material.BLUE_STAINED_GLASS_PANE) {

				p.sendMessage(ChatColor.BLUE+"");

			}
		}
	}




	public static String getName() {
		return name;
	}


	public static void setName(String name) {
		MenuConfiguration.name = name;
	}




	@Override
	public void ini() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void fillInventory(Inventory inv) {
		// TODO Auto-generated method stub
		
	}


}
