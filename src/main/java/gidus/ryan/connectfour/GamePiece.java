package gidus.ryan.connectfour;

public enum GamePiece {
	RED('R'), BLACK('B'), EMPTY(' ');
	
	private char abbreviated;
	
	GamePiece(char abbreviated) {
		this.abbreviated = abbreviated;
	}
	
	public static GamePiece getValue(char abbreviation) {
		for(GamePiece gamePiece: GamePiece.values()) {
		    if(gamePiece.abbreviated() == abbreviation) {
		      return gamePiece;
		    }
		  }
		  return null;// not found
		}
	
	public char abbreviated() {
		return abbreviated;
	}
}
