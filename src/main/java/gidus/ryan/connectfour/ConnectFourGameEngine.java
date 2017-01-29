package gidus.ryan.connectfour;

class ConnectFourGameEngine {
	
	public static void humanMove(Board board, int position) throws ColumnIsFullException, InvalidMoveException{
		move(board, position, board.getHumanGamePiece());
	}
	
	public static void computerMove(Board board) throws ColumnIsFullException, InvalidMoveException {
		//TODO improve AI
		int position = (int)(Math.random() * Board.NUM_ROWS);
		move(board, position, board.getComputerGamePiece());
	}
	
	public static void forceComputerMove(Board board, int position) throws ColumnIsFullException, InvalidMoveException{
		move(board, position, board.getComputerGamePiece());
	}
	
	private static void move(Board board, int position, GamePiece gamePiece) throws ColumnIsFullException, InvalidMoveException {
		
		if(position < 0 || position > Board.NUM_COLUMNS) {
			throw new InvalidMoveException("Invalid column index.");
		}
		
		insertIntoColumn(board.getGameBoard(), position, gamePiece);
		
		checkStatus(board);
		
		board.setLastMove(position);
		board.addMove();
		
		if(board.getNumberOfMoves() == Board.NUM_COLUMNS * Board.NUM_ROWS) {
			board.declareDraw();
		}
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
		else if((winner = checkTopToBottomDiagonals(board.getGameBoard())) != GamePiece.EMPTY) {
			board.declareWinner(winner);
		}
		else if((winner = checkBottomToTopDiagonals(board.getGameBoard())) != GamePiece.EMPTY) {
			board.declareWinner(winner);
		}
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
	
	private static GamePiece checkTopToBottomDiagonals(GamePiece[][] gamePieces) {
		for(int i = 0; i < Board.NUM_ROWS - 3; i++) {
			for(int j = 0; j < Board.NUM_COLUMNS - 3; j++) {
				GamePiece match = gamePieces[i][j];
				if(match == GamePiece.EMPTY) {
					continue;
				}
				
				boolean isFour = true;
				for(int k = 1; k < 4; k++) {
					if(gamePieces[i+k][j+k] != match) {
						isFour = false;
					}
				}
				if(isFour) {
					return match;
				}
			}
		}
		
		return GamePiece.EMPTY;
	}
	
	private static GamePiece checkBottomToTopDiagonals(GamePiece[][] gamePieces) {
		for(int i = 3; i < Board.NUM_ROWS; i++) {
			for(int j = 0; j < Board.NUM_COLUMNS - 3; j++) {
				GamePiece match = gamePieces[i][j];
				if(match == GamePiece.EMPTY) {
					continue;
				}
				
				boolean isFour = true;
				for(int k = 1; k < 4; k++) {
					if(gamePieces[i-k][j+k] != match) {
						isFour = false;
					}
				}
				if(isFour) {
					return match;
				}
			}
		}
		
		return GamePiece.EMPTY;
	}
}
