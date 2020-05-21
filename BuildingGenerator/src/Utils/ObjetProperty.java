package Utils;

import java.util.Comparator;

public class ObjetProperty<T> implements Comparable<T>{

	T value=null;

	T min=null;
	T max = null;

	T tmpToCompare = null;
	public ObjetProperty(T value) {
		super();
		this.value = value;
	}



	public ObjetProperty(T value, T min, T max) {
		super();
		this.value = value;
		this.min = min;
		this.max = max;
	}



	public T getValue() {
		return value;
	}

	public T get() {
		return getValue();
	}

	public void setValue(T value) {
		this.tmpToCompare = value;
		if(compareTo(min)<=0) {
			value = min;
			
		}

		if(compareTo(max)>=0) {
			value = max;
			
		}

		this.value = value;
	}

	public void set(T value) {
		this.setValue(value);
	}





	@Override
	public int compareTo(T arg0) {
		// TODO Auto-generated method stub
		if(arg0==null||this.value==null) {
			return 0;
		}
		try {

			return 	(( Comparable<T>) this.tmpToCompare).compareTo(arg0);



		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			Log.debug("pbr catch");
		}

		return 0;
	}}


