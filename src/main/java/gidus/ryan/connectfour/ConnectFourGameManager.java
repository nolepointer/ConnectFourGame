package gidus.ryan.connectfour;

import java.util.HashMap;

public class ConnectFourGameManager {
		private static ConnectFourGameManager instance = null;
		private HashMap<String, Board> boards = new HashMap<>(); 
				
		protected ConnectFourGameManager() {
			
		}
		
		public static ConnectFourGameManager getInstance() {
			if(instance == null) {
				instance = new ConnectFourGameManager();
			}
			
			return instance;
		}
		
		public Board newBoard(boolean isFirst, GamePiece humanGamePiece) {
			Board board = new Board(isFirst, humanGamePiece);
			boards.put(board.getId(), board);
			
			if(!board.isHumanFirst()) {
				ConnectFourGameEngine.computerMove(board);
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
		
		public Board playerMove(String boardId, int location) throws ColumnIsFullException{
//			if(location )
//				throw new InvalidMoveException
			
//			if id is off throw InvalidboardException
			
			Board board = boards.get(boardId);
//			try/catch full excecptions
			ConnectFourGameEngine.humanMove(board, location);
			if(board.getStatus() == Status.ONGOING) {
				ConnectFourGameEngine.computerMove(board);
			}
			
			return board;
		}
}
