package Modele;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import Utils.I18nMessage;

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
		
		
		for(int i =0;i<args.length;i++) {
			str.replace("{"+i+"}", args[i]);
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
