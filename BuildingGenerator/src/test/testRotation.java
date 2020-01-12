package test;

import org.bukkit.util.Vector;

import com.sk89q.worldedit.math.Vector3;

public class testRotation {

	public static void main(String[] args) {
		test(0);
		test(90);
		test(-90);
		test(180);
		
System.out.println(Math.ceil(-3.49));
	}
	
	
	public static void test(int deg) {
		Vector a = new Vector(1,0,0);
		
		Vector b = a.clone();


		a = a.rotateAroundY(Math.toRadians(deg));
		//a.subtract(b);



		System.out.println(a);
	}

}
