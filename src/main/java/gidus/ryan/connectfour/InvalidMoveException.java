package gidus.ryan.connectfour;

public class InvalidMoveException extends Exception{

	private static final long serialVersionUID = -7355017043082558714L;

	public InvalidMoveException() {}
	
	public InvalidMoveException(String message) {
		super(message);
	}
}
