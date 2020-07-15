package listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnClick implements Listener {

	
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {

		Player p = e.getPlayer();

		Block b = e.getClickedBlock();

		// Si il clique sur un block
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

		}
	}

}
