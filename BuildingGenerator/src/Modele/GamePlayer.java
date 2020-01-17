package Modele;

import org.bukkit.entity.Player;

public class GamePlayer {
	Player p ;
	Configuration configuration;
	
	
	
	public GamePlayer(Player p) {
		super();
		this.p = p;
		this.configuration = new Configuration();
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




	

}
