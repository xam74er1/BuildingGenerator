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
	boolean isInt = ob.get() instanceof Integer;
	
	
		// TODO Auto-generated method stub
		String str = ob.get().toString();
		double res = Double.valueOf(str) +value;
		

		res = Math.round(res*100.0)/100.0;

		if(isInt) {
		int tmp =(int) res;
		ob.set(tmp);
		}else {
			ob.set(res);
		}
	
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
