package gidus.ryan.connectfour;

class ConnectFourGameEngine {
	
	public static void humanMove(Board board, int position) throws ColumnIsFullException{
		insertIntoColumn(board.getGameBoard(), position, board.getHumanGamePiece());
		checkStatus(board);
		
		board.setLastMove(position);
	}
	
	public static void computerMove(Board board) {
		//TODO improve AI
		int position = (int)(Math.random() * Board.NUM_ROWS);
		try{
			insertIntoColumn(board.getGameBoard(), position, board.getComputerGamePiece());
		}
		catch(ColumnIsFullException e) {
			//Computer should never throw this exception
		}
		
		checkStatus(board);
		
		board.setLastMove(position);
	}
	
	private static void insertIntoColumn(GamePiece[][] gamePieces, int position, GamePiece humanGamePiece) throws ColumnIsFullException {
		for(int i = Board.NUM_ROWS - 1; i >= 0; i--) {
			if(gamePieces[i][position] == GamePiece.EMPTY) {
				gamePieces[i][position] = humanGamePiece;
				return;
			}
		}
		throw new ColumnIsFullException("Cannot move here. Column is full");
	}
	
	private static void checkStatus(Board board) {
		GamePiece winner;
		if((winner = checkRowsForWinner(board.getGameBoard())) != GamePiece.EMPTY) {
			board.declareWinner(winner);
		}
		
		else if((winner = checkColumnsForWinner(board.getGameBoard())) != GamePiece.EMPTY) {
			board.declareWinner(winner);
		}
		
		//Check diaganols
		
	}
	
	private static GamePiece checkRowsForWinner(GamePiece[][] gamePieces) {
		for(int i = Board.NUM_ROWS - 1; i >= 0; i--) {
			int pieceCount = 0;
			for(int j = 1; j < Board.NUM_COLUMNS; j++) {
				if(gamePieces[i][j] != GamePiece.EMPTY && gamePieces[i][j] == gamePieces[i][j-1]) {
					pieceCount++;
				}
				else {
					pieceCount = 0;
				}
				
				if(pieceCount == 3) {
					return gamePieces[i][j];
				}
			}
		}
		
		return GamePiece.EMPTY;
	}
	
	private static GamePiece checkColumnsForWinner(GamePiece[][] gamePieces) {
		for(int i = 0; i < Board.NUM_COLUMNS; i++) {
			int pieceCount = 0;
			for(int j = Board.NUM_ROWS - 2; j >= 0; j--) {
				if(gamePieces[j][i] != GamePiece.EMPTY && gamePieces[j][i] == gamePieces[j+1][i]) {
					pieceCount++;
				}
				else {
					pieceCount = 0;
				}
				
				if(pieceCount == 3) {
					return gamePieces[j][i];
				}
			}
		}
		
		return GamePiece.EMPTY;
	}
	
}
