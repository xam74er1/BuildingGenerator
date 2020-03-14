package Utils;

import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class ParticuleDraw {


	public  static Vector getDirectionBetweenLocations(Location Start, Location End) {
		Vector from = Start.toVector();
		Vector to = End.toVector();
		return to.subtract(from);
	}

	public static void draw(Location Loc1,Location Loc2) {

		Vector vector = getDirectionBetweenLocations(Loc1, Loc2);
		Log.print("v : "+vector);

		World w = Loc1.getWorld();

		w.getPlayers().forEach(p->p.spawnParticle(Particle.REDSTONE, Loc1.getBlockX(), Loc1.getBlockY(), Loc1.getBlockZ(),(int) Loc1.distance(Loc2)*10, vector.getBlockX(), 0, 0, 0.0000001, new Particle.DustOptions(Color.fromRGB(90, 252, 3),1)));


		/*
           for (double i = 1; i <= Loc1.distance(Loc2); i += 0.5) {

               vector.multiply(i);
               Loc1.add(vector);

               int x = Loc1.getBlockX();
               w.getPlayers().forEach(p->p.spawnParticle(Particle.REDSTONE, Loc1.getBlockX(), Loc1.getBlockY(), Loc1.getBlockZ(), 10, 1, 0, 0, 0.00001, new Particle.DustOptions(Color.fromRGB(90, 252, 3),1)));



               Loc1.subtract(vector);
               vector.normalize();
           }
		 */

	}

	public static void draw(Vector start,Vector vec,World w) {

		draw(start,vec,w,new Particle.DustOptions(Color.fromRGB(90, 252, 3),1));
	}

	public static void draw(Vector start,Vector vec,World w,DustOptions color) {


		Vector d2 = vec.clone();
		d2 = d2.divide(new Vector(2, 2, 2));
		Vector delta = d2.clone().divide(new Vector(2, 2, 2));
		Vector  from = start.clone();
		Vector res = from.add(d2);

		w.getPlayers().forEach(p->p.spawnParticle(Particle.REDSTONE, res.getBlockX(), res.getBlockY(), res.getBlockZ(),(int)  delta.length()*10 , delta.getBlockX(), delta.getBlockY(), delta.getBlockZ(), 0.0000001, color));

	}



	public static void drawCube(Location Loc1,Location Loc2) {
		World w = Loc1.getWorld();
		Vector v1 =Loc1.toVector();
		Vector v2=Loc2.toVector();
		Vector min = Vector.getMinimum(v1, v2);
		Vector max = Vector.getMaximum(v1, v2);

		Vector delta = max.clone();
		delta = delta.subtract(min);

		//X+0 Z+0

		draw(min,new Vector(delta.getBlockX(), 0, 0),w,new Particle.DustOptions(Color.fromRGB(0, 0, 255),1));
		draw(min,new Vector(0, delta.getY(),0),w);
		draw(min,new Vector(0, 0, delta.getZ()),w,new Particle.DustOptions(Color.fromRGB(255, 0, 0),1));

		Vector tmp = min.clone();

		//X+1
		tmp= tmp.add(new Vector(delta.getBlockX(), 0, 0));
		draw(tmp,new Vector(0, delta.getY(),0),w);
		draw(tmp,new Vector(0, 0, delta.getZ()),w);
		
		//X+1 Y+1
		tmp = tmp.add(new Vector(0, delta.getBlockY(), 0));
		draw(tmp,new Vector(0, 0, delta.getZ()),w);
		
		//Z+1
		
		tmp = min.clone();
		tmp= tmp.add(new Vector(0, 0, delta.getBlockZ()));
		draw(tmp,new Vector(0, delta.getY(),0),w);
		draw(tmp,new Vector(delta.getX(), 0, 0),w);
		
		//Z+1 Y+1
		tmp = tmp.add(new Vector(0, delta.getBlockY(), 0));
		draw(tmp,new Vector(delta.getX(), 0, 0),w);

		//Y+1
		tmp = min.clone();
		tmp = tmp.add(new Vector(0, delta.getBlockY(), 0));
		draw(tmp,new Vector(0, 0, delta.getBlockZ()),w);
		draw(tmp,new Vector(delta.getX(), 0,0),w);

		
		//X+1 Z+1
		
		tmp = min.clone();
		tmp= tmp.add(new Vector(delta.getBlockX(), 0, delta.getBlockZ()));
		draw(tmp,new Vector(0, delta.getY(),0),w);
	


	}

}
