package GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import Utils.EG_Exception;
import Utils.Log;
import net.md_5.bungee.api.ChatColor;

public class MainGUI implements InventoryHolder{
	
	Player p;
	//Size 
	int size =27;
	Inventory inv = null;
	static String name ="hello";

	public MainGUI(Player p) {
		super();
		this.p = p;
		ini();
		p.openInventory(inv);
	}
	
	
	public void ini() {
		
		 inv = Bukkit.createInventory(this, size,name);
		 fillInventory(inv);
		
	}
	
	public void fillInventory(Inventory inv) {

		ItemStack black_glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);


		for(int i = 0;i<size;i++) {
			inv.setItem(i,black_glass);
		}
		
	}
	
	
	
	public static void onInventoryClick(InventoryClickEvent e)  {
		Player p = (Player) e.getWhoClicked();
		ItemStack it = e.getCurrentItem();

		Inventory inv = e.getInventory();

		InventoryView w = e.getView();
		
		
		e.setCancelled(true);
		if(it!=null) {
			if(it.getType()==Material.PLAYER_HEAD) {

				//On recure le nom et tout les ellment 
				String name = it.getItemMeta().getDisplayName();
				
				if(name.equalsIgnoreCase("coucou")) {
					p.sendMessage(ChatColor.BLUE+"bonjour !!!!");
				}
				
			}
			
		}
		
	}
	
	
	
	
	
	
	

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inv;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	public Player getP() {
		return p;
	}


	public void setP(Player p) {
		this.p = p;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public Inventory getInv() {
		return inv;
	}


	public void setInv(Inventory inv) {
		this.inv = inv;
	}


	public static String getName() {
		return name;
	}


	public static void setName(String name) {
		MainGUI.name = name;
	}

	
	
}
