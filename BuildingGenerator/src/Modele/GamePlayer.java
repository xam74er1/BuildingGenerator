package Modele;

import org.bukkit.ChatColor;

import org.bukkit.entity.Player;

import Utils.I18nMessage;
import Utils.Log;

public class GamePlayer {
	Player p ;
	Configuration configuration;
	I18nMessage message;
	
	
	
	public GamePlayer(Player p) {
		super();
		this.p = p;
		this.configuration = new Configuration();
		message = new I18nMessage();
	}


	public boolean saveSechematics(String category) {
		return getConfiguration().getStyle().save(category, p);
	}
	

	
	///////////

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}


	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}


	public void sendMessage(String key) {
		p.sendMessage(message.getMessage(key));
	}

	public void sendMessage(String key,ChatColor color) {
		p.sendMessage(color+message.getMessage(key));
	}
	
	public void sendMessage(String key,ChatColor color,String [] args) {
		
		String str = message.getMessage(key);
		
		Log.debug("length"+args.length+" 0 "+args[0]);
		
		for(int i =0;i<args.length;i++) {
			str = str.replace("{"+i+"}", args[i]);
		}
		
		p.sendMessage(color+str);
	}
	

	public void sendMessageError(String key) {
		p.sendMessage(ChatColor.RED+message.getMessage(key));
	}
	
	public void sendMessageSucesse(String key) {
		p.sendMessage(ChatColor.GREEN+message.getMessage(key));
	}
	
	
}
