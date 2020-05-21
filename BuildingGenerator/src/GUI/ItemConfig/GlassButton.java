package GUI.ItemConfig;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import Utils.HeadList;
import Utils.ObjetProperty;

public class GlassButton {
	@FunctionalInterface
	public interface PresseButtonListener {

	    public void onPresseButton(ObjetProperty obj);

	}
	
	ItemStack it;
	String name;
	int slot = 0;
	
	PresseButtonListener listener;
	
	public GlassButton(String name,Material mt,int slot) {
		// TODO Auto-generated constructor stub
		
		this.it = new ItemStack(mt,1);
		this.slot = slot;
		HeadList.setName(it, name);
		
		this.name = name;
		
	}
	
	
	public void setEvent(PresseButtonListener listener) {
		this.listener = listener;
	}
	
	public void atEvent(ObjetProperty ob) {
		listener.onPresseButton(ob);
	}


	public ItemStack getItem() {
		return it;
	}


	public void setItem(ItemStack it) {
		this.it = it;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public PresseButtonListener getListener() {
		return listener;
	}


	public void setListener(PresseButtonListener listener) {
		this.listener = listener;
	}


	public ItemStack getIt() {
		return it;
	}


	public void setIt(ItemStack it) {
		this.it = it;
	}


	public int getSlot() {
		return slot;
	}


	public void setSlot(int slot) {
		this.slot = slot;
	}
	
	

}


