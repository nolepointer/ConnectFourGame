package gidus.ryan.connectfour;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTests {
	
	@Test
	public void testInitializeBoard() {
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(human);
		
		assertEquals(board.getHumanGamePiece(), GamePiece.BLACK);
		assertFalse(board.isWinner());
		assertEquals(board.getNumberOfMoves(), 0);
		assertEquals(board.getComputerGamePiece(), GamePiece.RED);
		assertEquals(board.getStatus(), Status.ONGOING);

		GamePiece[][] gameBoard = board.getGameBoard();
		
		for(int i = 0; i < Board.NUM_ROWS; i++) {
			for(int j = 0; j < Board.NUM_COLUMNS; j++) {
				assertEquals(gameBoard[i][j], GamePiece.EMPTY);
			}
		}
	
	}
	
	@Test
	public void testDeclareDraw(){
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(human);
		board.declareDraw();
		
		assertFalse(board.isWinner());
		assertEquals(board.getStatus(), Status.DRAW);
	}
	
	@Test
	public void testDeclareWinnerHuman(){
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(human);
		board.declareWinner(human);
		
		assertEquals(board.getStatus(), Status.OVER);
		assertTrue(board.isWinner());
	}
	
	@Test
	public void testDeclareWinnerComputer(){
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(human);
		board.declareWinner(GamePiece.RED);
		
		assertEquals(board.getStatus(), Status.OVER);
		assertFalse(board.isWinner());
	}

}
