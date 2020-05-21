package GUI.ItemConfig;


import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Modele.GamePlayer;
import Utils.HeadList;
import Utils.ObjetProperty;
import net.md_5.bungee.api.ChatColor;


abstract public class ItemConfig<T> {

	protected String text;
	private ItemType type = ItemType.INT;
	private String name;
	protected int pos = -1;
	protected ItemStack item;
	protected ObjetProperty<T> value;

	protected boolean generate =false;
	
	protected int size = 45;
	Inventory inv = null;



	HashMap<String,GlassButton> listButton = new HashMap();


	public ItemConfig(ItemType type, String name, ItemStack item,ObjetProperty<T> value,String txt) {
		super();
		this.type = type;
		this.name = name;
		this.value = value;
		this.item = item;
		this.text = txt;
		setName(item, text);

	}
	public ItemConfig(ItemType type, String name, ItemStack item,int pos,ObjetProperty<T> value,String txt) {
		super();
		this.type = type;
		this.name = name;
		this.pos = pos;
		this.item = item;
		this.value = value;
		this.text = txt;
		setName(item, text);
	}

	public ItemConfig(ItemType type, String name, int pos, Material math,ObjetProperty<T> value,String txt) {
		super();
		this.type = type;
		this.name = name;
		this.pos = pos;
		this.value = value;
		this.text = txt;
		this.item = new ItemStack(math,1);
		setName(item, text);
	}

	public ItemConfig(ItemType type, String name, int pos, String headName,ObjetProperty<T> value,String txt) {
		super();
		this.type = type;
		this.name = name;
		this.pos = pos;
		this.value = value;
		this.text = txt;
		this.item = HeadList.getHead(headName);
		setName(item, text);
	}


	public void ini() {

		generate = true;

	}
	public void fill(GamePlayer gp) {
		
		inv = Bukkit.createInventory(gp.getP(), size, name);
		
		generate(inv);
		gp.getP().openInventory(inv);
		
		
	}
	public abstract void generate(Inventory inv);
	public abstract void update(InventoryClickEvent e);


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

	public ItemStack setName(ItemStack is, String name){
		ItemMeta m = is.getItemMeta();
		m.setDisplayName(ChatColor.GOLD+name);
		is.setItemMeta(m);
		return is;
	}



}
