package GUI.ItemConfig;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import GUI.ItemConfig.GlassButton.PresseButtonListener;
import Utils.HeadList;
import Utils.ObjetProperty;
import fr.cr3art.spigot.Main;
import net.md_5.bungee.api.ChatColor;

public class ItemConfigProbaility extends ItemConfig<Double> {


	public ItemConfigProbaility(ItemType type, String name,  Material math,int pos,ObjetProperty value,String txt) {
		super(type, name, pos, math,value,txt);
		// TODO Auto-generated constructor stub
		ini();
	}


	@Override
	public void ini() {
		// TODO Auto-generated method stub	
		GlassButtonNumber gp10 = new GlassButtonNumber(ChatColor.DARK_GREEN+"+10%", Material.LIME_STAINED_GLASS_PANE,10.0/100.0,11);
		GlassButtonNumber gp5 = new GlassButtonNumber(ChatColor.DARK_GREEN+"+5%", Material.LIME_STAINED_GLASS_PANE,5.0/100.0,20);
		GlassButtonNumber gp1 = new GlassButtonNumber(ChatColor.DARK_GREEN+"+1%", Material.LIME_STAINED_GLASS_PANE,1.0/100.0,29);

		GlassButtonNumber gm10 = new GlassButtonNumber(ChatColor.DARK_RED+"-10%", Material.RED_STAINED_GLASS_PANE,-10.0/100.0,15);
		GlassButtonNumber gm5 = new GlassButtonNumber(ChatColor.DARK_RED+"-5%", Material.RED_STAINED_GLASS_PANE,-5.0/100.0,24);
		GlassButtonNumber gm1 = new GlassButtonNumber(ChatColor.DARK_RED+"-1%", Material.RED_STAINED_GLASS_PANE,-1.0/100.0,33);

		addButton(gp10);
		addButton(gp5);
		addButton(gp1);

		addButton(gm10);
		addButton(gm5);
		addButton(gm1);

		for(GlassButton gb : listButton.values()) {
			GlassButtonNumber gbn = (GlassButtonNumber) gb;
			gbn.setMin(0);
			gbn.setMax(100);
		}

		super.ini();
	}

	@Override
	public void generate(Inventory inv) {
		// TODO Auto-generated method stub
		if(!generate) {
			ini();
		}
		super.generate(inv);
		setValue(inv);
		setSlotButton(inv);


	}





	public void setValue(Inventory inv) {
		int percentage = (int) (value.get()*100);
		String str =  String.format("%03d", percentage);

		for(int i =0;i<3;i++) {
			ItemStack head = HeadList.getHead(str.charAt(i)+"");
			inv.setItem((3+i),head);
		}
		ItemStack head = HeadList.getHead("%");
		inv.setItem((6),head);
	}

	



}
