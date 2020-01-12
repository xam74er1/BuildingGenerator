package command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.session.*;
import com.sk89q.worldedit.world.World;

import GUI.MainGUI;
import Utils.Log;

public class exmple implements CommandExecutor {
	int k = 0;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
			try {
				if (p.hasPermission("permitionExmple") || p.isOp()) {

					System.out.println("start");

					// Load schem
					// Walls

					String path2 = worldEdit.getDataFolder().getAbsolutePath() + "\\schematics\\walls3.schem";
					System.out.println("path : " + path2);
					File file2 = new File(path2);
					Clipboard walls;
					ClipboardFormat format2 = ClipboardFormats.findByFile(file2);
					ClipboardReader reader2 = format2.getReader(new FileInputStream(file2));
					walls = reader2.read();

					String path = worldEdit.getDataFolder().getAbsolutePath() + "\\schematics\\walls4.schem";
					System.out.println("path : " + path);
					File file = new File(path);
					Clipboard walls2;
					ClipboardFormat format = ClipboardFormats.findByFile(file);
					ClipboardReader reader = format.getReader(new FileInputStream(file));
					walls2 = reader.read();

					// Base

					path = worldEdit.getDataFolder().getAbsolutePath() + "\\schematics\\base2.schem";

					file = new File(path);
					Clipboard base;
					format = ClipboardFormats.findByFile(file);
					reader = format.getReader(new FileInputStream(file));
					base = reader.read();

					// Paste the schem

					int x = p.getLocation().getBlockX();
					int y = p.getLocation().getBlockY();
					int z = p.getLocation().getBlockZ();

					LocalSession localSession = worldEdit.getSession(p);
					EditSession editSession = WorldEdit.getInstance().getEditSessionFactory()
							.getEditSession(new BukkitWorld(p.getWorld()), -1);

					/*
					 * AffineTransform transform = new AffineTransform();
					 * 
					 * transform = transform.rotateY(90);
					 * 
					 * 
					 * 
					 * 
					 * 
					 * ClipboardHolder ch = new ClipboardHolder(walls);
					 * 
					 * 
					 * 
					 * // ch.setTransform(transform); Log.print(" il sera paste en "+get00Paste(x,
					 * y+base.getDimensions().getBlockY(), z, walls));
					 * 
					 * Operation operation = ch.createPaste(editSession) .to(get00Paste(x,
					 * y+base.getDimensions().getBlockY(), z, walls)) .ignoreAirBlocks(false)
					 * .build();
					 * 
					 * 
					 * 
					 * //////////////////90 ///////////
					 * 
					 * ch = new ClipboardHolder(walls); ch.setTransform(transform);
					 * Log.print(" il sera paste en "+get00Paste(x,
					 * y+base.getDimensions().getBlockY(), z, walls));
					 * 
					 * Operation operation3 = ch.createPaste(editSession) .to(get00Paste(x,
					 * y+base.getDimensions().getBlockY(), z, walls)) .ignoreAirBlocks(false)
					 * .build();
					 * 
					 * 
					 * /////////////////////: -90///////////////////::
					 * 
					 * transform = new AffineTransform();
					 * 
					 * transform = transform.rotateY(-90);
					 * 
					 * ch = new ClipboardHolder(walls); ch.setTransform(transform);
					 * 
					 * Log.print(" original "+walls.getDimensions()+" "+walls.getOrigin()
					 * +"  rottate "+ch.getClipboard().getDimensions()+" "+ch.getClipboard().
					 * getOrigin());
					 * 
					 * Log.print("-90"); for(double d : transform.coefficients()) {
					 * Log.print(" coef "+d); }
					 * 
					 * 
					 * Operation operation4 = ch.createPaste(editSession)
					 * .to(get00Paste(x-base.getDimensions().getBlockX(),
					 * y+base.getDimensions().getBlockY(), z+base.getDimensions().getBlockZ(),
					 * walls)) .ignoreAirBlocks(false) .build();
					 * 
					 * 
					 * ////////////////////////////// 180 ///////////////
					 * 
					 * 
					 * 
					 * 
					 * transform = new AffineTransform();
					 * 
					 * transform = transform.rotateY(180);
					 * 
					 * ch = new ClipboardHolder(walls); ch.setTransform(transform);
					 * 
					 * Vector test = new Vector(x, y, z);
					 * 
					 * 
					 * Log.print(" original "+walls.getDimensions()+" "+walls.getOrigin()
					 * +"  rottate "+ch.getClipboard().getDimensions()+" "+ch.getClipboard().
					 * getOrigin());
					 * 
					 * Log.print("180"); for(double d : transform.coefficients()) {
					 * Log.print(" coef "+d); }
					 * 
					 * 
					 * Operation operation5 = ch.createPaste(editSession)
					 * .to(get00Paste(x-base.getDimensions().getBlockX(),
					 * y+base.getDimensions().getBlockY(), z+base.getDimensions().getBlockZ(),
					 * walls)) .ignoreAirBlocks(false) .build();
					 * 
					 * 
					 * 
					 * ClipboardHolder ch2 = new ClipboardHolder(base); Operation operation2 =
					 * ch2.createPaste(editSession) .to(get00Paste(x, y, z, base))
					 * .ignoreAirBlocks(false) .build();
					 * 
					 * Operations.complete(operation5); Operations.complete(operation4);
					 * Operations.complete(operation3); Operations.complete(operation2);
					 * Operations.complete(operation);
					 * 
					 */
					ClipboardHolder ch2 = new ClipboardHolder(base);
					Operation operation2 = ch2.createPaste(editSession).to(centreAtPosition(x, y, z, base))
							.ignoreAirBlocks(false).build();

					Log.print(" \n \n \n ");

					Operations.complete(pastWithRotation(x, y, z, walls2, base, 0, editSession));
					Operations.complete(pastWithRotation(x, y, z, walls2, base, 90, editSession));
					Operations.complete(pastWithRotation(x, y, z, walls2, base, -90, editSession));
					Operations.complete(pastWithRotation(x, y, z, walls2, base, 180, editSession));

					Operations.complete(operation2);
					editSession.flushSession();

					localSession.remember(editSession);

					p.sendMessage(" x :" + x + " y: " + y + " z " + z + " ");

					System.out.println("end 2");
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (WorldEditException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

		}

		// TODO Auto-generated method stub
		return false;
	}

	public BlockVector3 get00Paste(Clipboard cp) {

		BlockVector3 bv3 = cp.getOrigin();

		return bv3.subtract(cp.getMinimumPoint());

	}

	public BlockVector3 get00Paste(int x, int y, int z, Clipboard cp) {

		BlockVector3 bv3 = cp.getOrigin();

		bv3 = bv3.subtract(cp.getMinimumPoint());

		// Log.print(" bv "+bv3+" x "+x+" y"+y+" z "+z+" "+cp.getOrigin()+" ");

		return bv3.add(x, y, z);

	}

	public BlockVector3 centreAtPosition(int x, int y, int z, Clipboard cp) {
		Vector3 centre = cp.getRegion().getCenter();

		centre = centre.subtract(cp.getOrigin().getBlockX(), cp.getOrigin().getBlockY(), cp.getOrigin().getBlockZ());
		//
		// Log.print("center "+cp.getRegion().getCenter());
		// Log.print("origine"+cp.getOrigin());
		// Log.print("centre = "+centre);

		return BlockVector3.at(x - centre.getX(), y, z - centre.getZ());
	}

	public Operation pastWithRotation(int x, int y, int z, Clipboard original, Clipboard base, int rotation,
			EditSession editSession) {
		Log.print("\n roation : " + rotation);

		if (original.getDimensions().getX() > original.getDimensions().getZ()) {
			rotation += 90;
		}

		BlockVector3 bv3 = BlockVector3.at(x, y + 1 + k, z);
		k++;

		// sert a centre au milleux
		Vector decalage = blockVector3ToVector(original.getOrigin());
		decalage = decalage.subtract(blockVector3ToVector(original.getMinimumPoint()));

		decalage = decalage.rotateAroundY(Math.toRadians(rotation));

		Log.print("decalage" + decalage);

		// On les centre au milleux
		bv3 = bv3.add(BlockVector3.at((int) Math.round(decalage.getX()), 1, (int) Math.round(decalage.getZ())));

		Vector vec = new Vector(base.getDimensions().getBlockX(), base.getDimensions().getBlockY(),
				base.getDimensions().getBlockZ());
		vec = vec.divide(new Vector(2, 1, 2));

		vec.rotateAroundY(Math.toRadians(rotation));
		// On vas fair en sorte que tous aye la meme rotation

		Log.print("dimention de la base " + base.getDimensions() + " vec " + vec);

		// On les place au bonne position
		bv3 = bv3.subtract(vectorToBlockVector3(vec));

		// Log.print(" bv "+bv3+" x "+x+" y"+y+" z "+z+" "+cp.getOrigin()+" ");

		// bv3 = bv3.add(original.getOrigin());
		// bv3 =
		// bv3.add(getUpBlock(vec.getX()),getUpBlock(vec.getY()),getUpBlock(vec.getZ()));
		// bv3= bv3.add(0,decalage.getBlockY(),decalage.getBlockZ());

		// bv3= bv3.subtract(base.getDimensions().getX()-1,0,0);

		AffineTransform transform = new AffineTransform();

		transform = transform.rotateY(rotation);

		ClipboardHolder ch = new ClipboardHolder(original);
		ch.setTransform(transform);

		Log.print("point de paste " + bv3);
		Operation operation = ch.createPaste(editSession).to(bv3).ignoreAirBlocks(false).build();

		return operation;

	}

	public int getUpBlock(double a) {
		if (a > 0) {
			return (int) Math.ceil(a);
		}

		return (int) Math.floor(a);
	}

	public BlockVector3 vectorToBlockVector3(Vector v) {
		return BlockVector3.at(v.getX(), v.getY(), v.getZ());
	}

	public Vector blockVector3ToVector(BlockVector3 bv) {
		return new Vector(bv.getX(), bv.getY(), bv.getZ());
	}

}
