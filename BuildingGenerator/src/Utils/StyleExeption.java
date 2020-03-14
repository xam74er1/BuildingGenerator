package Utils;

public class StyleExeption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	public StyleExeption(String message) {
		super(message);
		this.message = message;
	}
	
	

}
