package GUI.ItemConfig;


import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import Modele.GamePlayer;
import Utils.GameConstante;
import Utils.HeadList;
import Utils.Log;
import Utils.ObjetProperty;
import fr.cr3art.spigot.Main;
import net.md_5.bungee.api.ChatColor;


abstract public class ItemConfig<T> {

	protected String text;
	private ItemType type = ItemType.INT;
	private String name;
	protected int pos = -1;
	protected ItemStack item;
	protected ItemStack back;
	protected ObjetProperty<T> value;

	protected boolean generate =false;
	
	protected int size = 45;
	protected Inventory inv = null;

public static NamespacedKey nameSpace = new NamespacedKey(Bukkit.getPluginManager().getPlugin(GameConstante.pluginName), "Name");

	HashMap<String,GlassButton> listButton = new HashMap();


	public ItemConfig(ItemType type, String name, ItemStack item,ObjetProperty<T> value,String txt) {
		super();
		this.type = type;
		this.name = name;
		this.value = value;
		this.item = item;
		this.text = txt;
		setName(item,name, text);

	}
	public ItemConfig(ItemType type, String name, ItemStack item,int pos,ObjetProperty<T> value,String txt) {
		super();
		this.type = type;
		this.name = name;
		this.pos = pos;
		this.item = item;
		this.value = value;
		this.text = txt;
		setName(item,name, text);
	}

	public ItemConfig(ItemType type, String name, int pos, Material mat,ObjetProperty<T> value,String txt) {
		super();
		this.type = type;
		this.name = name;
		this.pos = pos;
		this.value = value;
		this.text = txt;
		this.item = new ItemStack(mat,1);
		setName(item,name, text);
	}

	public ItemConfig(ItemType type, String name, int pos, String headName,ObjetProperty<T> value,String txt) {
		super();
		this.type = type;
		this.name = name;
		this.pos = pos;
		this.value = value;
		this.text = txt;
		this.item = HeadList.getHead(headName);
		setName(item, name,text);
	}


	public void ini() {

		generate = true;

	}
	public void fill(GamePlayer gp) {
		
		inv = Bukkit.createInventory(gp.getPlayer(), size, name);
		
		generate(inv);
		gp.getPlayer().openInventory(inv);
		
		
	}
	public void generate(Inventory inv) {
		back = new ItemStack(Material.SPECTRAL_ARROW,1);
	ItemMeta im=	back.getItemMeta();
	im.setDisplayName("Menu");
	back.setItemMeta(im);
		
		fillBlackGlass(inv);
		inv.setItem(22, item);
		inv.setItem(36, back);
	}
	
	public abstract void setValue(Inventory inv);
	
	public void update(InventoryClickEvent e) {
		// TODO Auto-generated method stub


		ItemStack click =e.getCurrentItem();
if(click!=null) {
		GlassButton gb = getButton(click.getItemMeta().getDisplayName());

		if(gb!=null) {
			Log.debug("Avant GB :"+value.get());
			gb.atEvent(value);
			Log.debug("Avant set value :"+value.get());
			setValue(e.getInventory());
		}else {
			if(click.getItemMeta().getDisplayName().equalsIgnoreCase("Menu")) {
				Main.getPlayer((Player) e.getWhoClicked()).back();
			}
		}
}
		e.setCancelled(true);

	}


	public void addButton(GlassButton bt) {
		listButton.put(bt.getName(),bt);
	}

	public void fillBlackGlass(Inventory inv) {
		for(int i =0;i<inv.getSize();i++) {
			inv.setItem(i,new ItemStack(Material.BLACK_STAINED_GLASS_PANE) );
		}
	}

	public GlassButton getButton(String name) {
		if(listButton.containsKey(name)) {
			return listButton.get(name);
		}
		return null;
	}

	public ItemStack getItemButton(String name) {
		if(listButton.containsKey(name)) {
			return listButton.get(name).getItem();
		}
		return null;
	}


	public void setSlotButton(Inventory inv) {
		for(GlassButton gb : listButton.values()) {
			inv.setItem(gb.getSlot(), gb.getItem());
		}
	}

	//////////////////////////////////////////////////
	public ItemType getType() {
		return type;
	}
	public void setType(ItemType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public ItemStack getItem() {
		return item;
	}
	public void setItem(ItemStack item) {
		this.item = item;
	}
	public T getValue() {
		return value.getValue();
	}
	public void setValue(T value) {
		((ObjetProperty<T>) value).setValue(value);
	}

	public ItemStack setName(ItemStack is, String name,String text){
		ItemMeta m = is.getItemMeta();
		m.setDisplayName(ChatColor.GOLD+text);
	
		m.getPersistentDataContainer().set(nameSpace, PersistentDataType.STRING, name);
		is.setItemMeta(m);
		return is;
	}



}
