package Modele;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils.Null;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;

import Modele.Build.BuildType;
import Modele.Build.Rooft;
import Utils.FileUtility;
import Utils.GameConstante;
import Utils.Log;

public class Style {

	private static HashMap<String,Style> mapStyle = new HashMap<String, Style>();

	String name;
	ArrayList<Schematics> listFloor = new ArrayList<Schematics>();
	ArrayList<Schematics> listWalls = new ArrayList<Schematics>();
	ArrayList<Schematics> listRooft = new ArrayList<Schematics>();

	//Taille des mur
	int wallsSize = -1;

	public Style(String name) {
		this.name = name;
		loadSchem();
	}


	public void loadSchem() {
		loadSchemFloor();
		loadSchemWalls();
		loadSchemRooft();
	}

	public void loadSchemFloor() {

		String path =getPath()+"/floor";

		File listSchem[] = new File(path).listFiles();

		for(File f : listSchem) {
			listFloor.add(new Schematics(f.getAbsolutePath()));
		}

		if(name.equalsIgnoreCase("default")) {
			//Provisoire
			listFloor.add(new Schematics(GameConstante.schematicsPath+"base2.schem"));
		}
	}

	public void loadSchemWalls() {


		String pathW =getPath()+"/walls";

		File listSchem[] = new File(pathW).listFiles();


		for(File f : listSchem) {
			Schematics tmpSchem = new Schematics(f.getAbsolutePath());
			//si il a besoin detre rotate pour etre dans le bon sens 
			tmpSchem.appliqueRotation();
			listWalls.add(tmpSchem);
			int y = tmpSchem.getDimention().getBlockY();

			if(y>wallsSize) {
				wallsSize = y;
			}

		}

		/*
		if(name.equalsIgnoreCase("default")) {
			//Provisoire
			String[] list = {GameConstante.schematicsPath+"walls6.schem",GameConstante.schematicsPath+"walls5.schem"};

			for(String path : list) {
				Schematics tmpSchem = new Schematics(path);
				//si il a besoin detre rotate pour etre dans le bon sens 
				tmpSchem.appliqueRotation();
				listWalls.add(tmpSchem);
				int y = tmpSchem.getDimention().getBlockY();

				if(y>wallsSize) {
					wallsSize = y;
				}

			}
		}
		*/
		//Provisiroire
	}

	public void loadSchemRooft() {

		String path =getPath()+"/rooft";

		File listSchem[] = new File(path).listFiles();

		for(File f : listSchem) {
			listRooft.add(new Schematics(f.getAbsolutePath()));
		}
		if(name.equalsIgnoreCase("default")) {
			listRooft.add(new Schematics(GameConstante.schematicsPath+"base2.schem"));
		}
	}

	public Schematics getRandomFloor() {
		Log.print("Size :"+listFloor.size());
		return listFloor.get(0);
	}

	//Retounre un mur aleatoire , sois le mur de dimention exate , sois celui qui en est le plus proche 
	public Schematics getRandomWalls(int param) {
		ArrayList<Schematics> candidat = new ArrayList<Schematics>();

		Schematics min = null;
		int dmin = Integer.MAX_VALUE;

		for(Schematics w : listWalls) {
			int dx = Math.abs(w.getDimention().getBlockX()-Math.abs(param));

			//	Log.print("d = "+dx+" "+w.path+" "+w.getDimention()+" p "+param);

			if(dx==0) {
				candidat.add(w);
			}else if(dmin>dx){
				dmin = dx;
				min = w;
			}

		}

		if(candidat.size()!=0) {
			return candidat.get((int) Math.round(Math.random()*(candidat.size()-1))); 
		}

		return min;
	}

	public Schematics getRandomRooft() {
		int random = (int) (Math.random()*listRooft.size()-1);
		if(random>=0) {
			return listRooft.get(random);
		}
		return null;
	}




	public static Style getStyle(String name) {
		if(mapStyle.containsKey(name)) {
			return mapStyle.get(name);
		}else {
			//Renvois null si le style nexiste pas 
			return loadStyle(name);
		}


	}


	public static Style loadStyle(String name) {

		File f = new File(GameConstante.stylePath+"/"+name) ;

		if(f.exists()) {
			Style s = 	new Style(name);
			addStyle(name,s);
			return s;

		}

		return null;
	}


	private String getPath() {
		return GameConstante.stylePath+"/"+name;
	}


	public static Style createStyle(String name) {
		File f = new File(GameConstante.stylePath+"/"+name);


		//On cree le fichier principale
		boolean cree = FileUtility.createFileIfNotExiste(GameConstante.stylePath+"/"+name);

//On cree pour chaque buit type un fichier 
		for(BuildType bt : BuildType.values()) {
			FileUtility.createFileIfNotExiste(GameConstante.stylePath+"/"+name+"/"+bt.getName());
		}


		Style s = null;
		//Il est possible que le fichier exitais deja donc si il a na pas ete cree on continue
		if(cree) {
			s = new Style(name);
			addStyle(name, s);
		}
		return s;

	}



	public boolean save(String str,Player player) {

		BuildType bt = BuildType.find(str);

		if(bt!=null) {
			WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
			try {
				Region region = worldEdit.getSession(player).getSelection(worldEdit.getSession(player).getSelectionWorld());


				BukkitWorld world = new BukkitWorld(player.getWorld());
				CuboidRegion cuboidRegion = new CuboidRegion(world,region.getMinimumPoint(), region.getMaximumPoint());

				BlockArrayClipboard clipboard = new BlockArrayClipboard(cuboidRegion);

				try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {
					ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());

					Operations.complete(forwardExtentCopy);
				} catch (WorldEditException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				return save(bt,clipboard );
			} catch (IncompleteRegionException |NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				return false;
			}


		}

		return false;
	}

/*
 * Sauvegade une selection en un schematics
 * 
 * @return une boolean
 * 
 */
	public boolean save(BuildType build,Clipboard clipboard) {

		//Chemin du dossier
		String path = GameConstante.stylePath+"/"+name+"/"+build.getName();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}


		String patternStr = build.getName()+".*[0-9]+\\.schem$";
		Pattern pattern = Pattern.compile(patternStr);

		String[] list = folder.list((d, s) -> {

			return pattern.matcher(s).find();
		});

		//On vas cree un nvx fichier donc on verifie quil ny en a pas qui existe 


		int max = -1;

		for(String s : list) {
			String[] premierePatrie = s.split(build.getName());

			if(premierePatrie.length>1) {
				String[] tmp = premierePatrie[1].split(".schem");
				int nb;
				if(tmp.length>1) {

					nb = Integer.parseInt(tmp[1]);

				}else {
					nb = Integer.parseInt(tmp[0]);
				}
				if(nb>max) {
					max = nb;
				}
			}
		}
		max++;


		String pathSchem =  path+"/"+build.getName()+""+max+".schem";

		File f = new File(pathSchem);

		try (ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(f))) {
			writer.write(clipboard);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}


		//ON ajoute a la liste apres l'avoir save

		switch (build) {
		case Floor:
			listFloor.add(new Schematics(clipboard));
			break;
		case Rooft:
			listRooft.add(new Schematics(clipboard));
			break;
		case Wall:
			//Traitement spetialle pour les walls 

			Schematics tmpSchem = new Schematics(clipboard);
			//si il a besoin detre rotate pour etre dans le bon sens 
			tmpSchem.appliqueRotation();
			listWalls.add(tmpSchem);
			int y = tmpSchem.getDimention().getBlockY();
			//On regarde la hauteure maximalle 
			if(y>wallsSize) {
				wallsSize = y;
			}

			break;
		default:
			Log.debug("Bon on ne dois jamais arrive ici");
			break;

		}

		return true;
	}


public boolean update(Schematics schem,Region region,World world,BuildType bt) {
	String pathSchem = schem.getPath();
	
	
	Log.debug("Avant :" +schem.getDimention());
	BlockArrayClipboard clipboard = new BlockArrayClipboard(region);

	try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {
		ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());

		Operations.complete(forwardExtentCopy);
	} catch (WorldEditException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	//We save the clipbord inside the mode
			schem.setCliboard(clipboard);
			
			Log.debug("bt : "+bt.getName());
			if(bt == BuildType.Wall) {
			schem.appliqueRotation();
			Log.debug("roation ");
			}
			Log.debug("Apres :" +schem.getDimention());
	File f = new File(pathSchem);

	try (ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(f))) {
		//We save the file
		writer.write(clipboard);
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	return true;
}



	/////////////////////////////////////////////////////////

	public static String[] listStyle() {
		return new File(GameConstante.stylePath).list();
	}

	public static void addStyle(String name , Style s) {
		mapStyle.put(name,s);
	}

	//gette and setter

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getWallsSize() {
		return wallsSize;
	}


	public void setWallsSize(int wallsSize) {
		this.wallsSize = wallsSize;
	}


	public static HashMap<String, Style> getMapStyle() {
		return mapStyle;
	}


	public static void setMapStyle(HashMap<String, Style> mapStyle) {
		Style.mapStyle = mapStyle;
	}


	public ArrayList<Schematics> getListFloor() {
		return listFloor;
	}


	public void setListFloor(ArrayList<Schematics> listFloor) {
		this.listFloor = listFloor;
	}


	public ArrayList<Schematics> getListWalls() {
		return listWalls;
	}


	public void setListWalls(ArrayList<Schematics> listWalls) {
		this.listWalls = listWalls;
	}


	public ArrayList<Schematics> getListRooft() {
		return listRooft;
	}


	public void setListRooft(ArrayList<Schematics> listRooft) {
		this.listRooft = listRooft;
	}





}
