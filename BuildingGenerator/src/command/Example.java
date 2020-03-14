package command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.util.Location;

import Utils.Log;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.PacketPlayOutOpenSignEditor;

import org.bukkit.command.CommandExecutor;

/**
 * Example
 * Commande de test pour apprendre à utiliser l'autocompletion
 * avec les tabulation dans le tchat
 */
public class Example implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Log.print("test");
        if (sender instanceof Player) {
            Player player = (Player) sender;
            
          
            test(player);
            player.sendMessage("Super message de test de la mort qui tue !!!! ");
            if (args.length > 0) {
                player.sendMessage("Arguments = " + args.length);
            }
        }
        return false;
    }

    public static TabCompleter tabCompleter = new TabCompleter() {

        @Override
        public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
            List<String> result = new ArrayList<String>();
            Log.print("onTabComplete called");

            if (arg3.length == 1) {
                result.add("testArgs1");
                result.add("testArgs2");
            }

            if (arg3.length == 2) {
                result.add("testArgs3");
                result.add("testArgs4");
            }

            return result;
        }
    };
    
    public void test(Player player) {
    	Log.print("test");
    	org.bukkit.Location l = player.getLocation();
    	Player p = player;
    
    	
	
		
	
			PacketPlayOutOpenSignEditor pke = new PacketPlayOutOpenSignEditor();
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(pke);
		
           // This sends the packet to the playerconnection so the player
   
    }
    
    public Class<?> getNMSClass(String clazz) {
        try {
            return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}