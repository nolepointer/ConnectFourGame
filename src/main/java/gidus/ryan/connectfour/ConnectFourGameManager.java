package gidus.ryan.connectfour;

import java.util.HashMap;

public class ConnectFourGameManager {
		private static ConnectFourGameManager instance = null;
		private HashMap<String, Board> boards = new HashMap<>(); 
				
		private ConnectFourGameManager() {}
		
		public static ConnectFourGameManager getInstance() {
			if(instance == null) {
				instance = new ConnectFourGameManager();
			}
			
			return instance;
		}
		
		public Board newBoard(boolean isFirst, GamePiece humanGamePiece) {
			Board board = new Board(humanGamePiece);
			board.setIsFirst(isFirst);
			boards.put(board.getId(), board);
			
			if(!board.isHumanFirst()) {
				try {
					ConnectFourGameEngine.computerMove(board);
				} catch (ColumnIsFullException | InvalidMoveException e) {
					//The column should never be full on first turn and the computer should
					//be making a valid move
				}
			}
			return board;
		}
		
		public boolean removeBoard(String boardId) {
			if(boards.containsKey(boardId)) {
				boards.remove(boardId);
				return true;
			}
			return false;
		}
		
		public Board getBoard(String boardId) {
			return boards.get(boardId);
		}
		
		public Board playerMove(String boardId, int location) throws ColumnIsFullException, InvalidMoveException{
			Board board = boards.get(boardId);

			if(board!= null) {
				ConnectFourGameEngine.humanMove(board, location);
				if(board.getStatus() == Status.ONGOING) {
					ConnectFourGameEngine.computerMove(board);
				}
			}
			return board;
		}
}
