package command;

public class Argument {
	
	int position;
	String value;
	public Argument(int position, String value) {
		super();
		this.position = position;
		this.value = value;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
