package gidus.ryan.connectfour;

public class ColumnIsFullException extends Exception{

	public ColumnIsFullException() {}
	
	public ColumnIsFullException(String message) {
		super(message);
	}
}
