package Modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.util.Vector;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.session.ClipboardHolder;

import Utils.GameConstante;
import Utils.Log;

public class Schematics {
	
	Clipboard cliboard;

	BlockVector3 centre;
	BlockVector3 dimention;
	String path = "no path";
	
	int rotation = 0;
	
	
	public Schematics(String path) {
		load(path);
		this.path = path;
		initlisze();
		// TODO Auto-generated constructor stub
	}

	



	public Schematics(Clipboard cliboard) {
		super();
		this.cliboard = cliboard;
		initlisze();
	}


public void initlisze() {
	centre = calculeCenter();
	BlockVector3 tmp = cliboard.getDimensions();
	dimention = tmp.at(tmp.getBlockX(), tmp.getBlockY(), tmp.getBlockZ());
}


	public void load(String path) {


				File file = new File(path);
		Clipboard walls2;
		ClipboardFormat format = ClipboardFormats.findByFile(file);
		try {
			ClipboardReader reader = format.getReader(new FileInputStream(file));
		this.cliboard =	reader.read();
			reader.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	
	public BlockVector3 calculeCenter() {

		BlockVector3 bv3 = cliboard.getOrigin();

		return bv3.subtract(cliboard.getMinimumPoint());

	}

	public BlockVector3 get00Paste(int x, int y, int z, Clipboard cliboard) {

		BlockVector3 bv3 = cliboard.getOrigin();

		bv3 = bv3.subtract(cliboard.getMinimumPoint());

		// Log.print(" bv "+bv3+" x "+x+" y"+y+" z "+z+" "+cliboard.getOrigin()+" ");

		return bv3.add(x, y, z);

	}

	public BlockVector3 centreAtPosition(int x, int y, int z, Clipboard cliboard) {
		Vector3 centre = cliboard.getRegion().getCenter();

		centre = centre.subtract(cliboard.getOrigin().getBlockX(), cliboard.getOrigin().getBlockY(), cliboard.getOrigin().getBlockZ());
		

		return BlockVector3.at(x - centre.getX(), y, z - centre.getZ());
	}

	public Operation pastWithRotation(int x, int y, int z,  int rotation,EditSession editSession) {
	

	rotation += this.rotation;

		BlockVector3 bv3 = BlockVector3.at(x, y  , z);
		

		// sert a centre au milleux
		Vector decalage = blockVector3ToVector(cliboard.getOrigin());
		decalage = decalage.subtract(blockVector3ToVector(cliboard.getMinimumPoint()));

		decalage = decalage.rotateAroundY(Math.toRadians(rotation));

	//	Log.print("decalage" + decalage);

		// On les centre au milleux
		bv3 = bv3.add(BlockVector3.at((int) Math.round(decalage.getX()), (int) Math.round(decalage.getY()), (int) Math.round(decalage.getZ())));

		AffineTransform transform = new AffineTransform();

		transform = transform.rotateY(rotation);

		ClipboardHolder ch = new ClipboardHolder(cliboard);
		ch.setTransform(transform);

		//Log.print("point de paste " + bv3);
		Operation operation = ch.createPaste(editSession).to(bv3).ignoreAirBlocks(false).build();

		return operation;

	}
	
	//Si lellment a besoin d'avoir une rotation particluire dans le cas ou x>y 
	public void appliqueRotation() {
		if (cliboard.getDimensions().getX() < cliboard.getDimensions().getZ()) {
			rotation = 90;
			
		
		 dimention =   dimention.at(dimention.getBlockZ(), dimention.getBlockY(), dimention.getBlockX());
			
		}
		
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





	public Clipboard getCliboard() {
		return cliboard;
	}





	public void setCliboard(Clipboard cliboard) {
		this.cliboard = cliboard;
	}





	public BlockVector3 getCentre() {
		return centre;
	}





	public void setCentre(BlockVector3 centre) {
		this.centre = centre;
	}





	public BlockVector3 getDimention() {
		return dimention;
	}





	public void setDimention(BlockVector3 dimention) {
		this.dimention = dimention;
	}






	
	
	
	

}
