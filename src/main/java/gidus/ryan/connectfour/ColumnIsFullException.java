package gidus.ryan.connectfour;

public class ColumnIsFullException extends Exception{

	private static final long serialVersionUID = 484571464888711351L;

	public ColumnIsFullException() {}
	
	public ColumnIsFullException(String message) {
		super(message);
	}
}
