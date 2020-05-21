package GUI.ItemConfig;

import org.bukkit.Material;

import Utils.Log;
import Utils.ObjetProperty;

public class GlassButtonNumber extends GlassButton{

	double value;
	double max=Double.MAX_VALUE;
	double min=Double.MIN_VALUE;
	public GlassButtonNumber(String name, Material mt,double value,int slot) {
		super(name, mt,slot);
		this.value = value;
	
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void atEvent(ObjetProperty ob) {
		// TODO Auto-generated method stub
		double res = ((Double )ob.get()) +value;

		res = Math.round(res*100.0)/100.0;
	
		
		ob.set(res);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	
	
}
