
public class ImproperFieldException extends Exception { // class for exception
	
	private String message;
	
	public ImproperFieldException (String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
