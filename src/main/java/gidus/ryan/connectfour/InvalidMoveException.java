package gidus.ryan.connectfour;

public class InvalidMoveException extends Exception{

	public InvalidMoveException() {}
	
	public InvalidMoveException(String message) {
		super(message);
	}
}
