package gidus.ryan.connectfour;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConnectFourGameEngineTests {
	
	@Test
	public void testHumanMove() throws ColumnIsFullException, InvalidMoveException {
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(true, human);
		
		int movePosition = 0;
		ConnectFourGameEngine.humanMove(board, movePosition);
		
		assertEquals(board.getGameBoard()[5][0], human);
		assertEquals(board.getLastMove(), movePosition);
		assertEquals(board.getStatus(), Status.ONGOING);
		assertEquals(board.getNumberOfMoves(), 1);
	}
	
	@Test
	public void testComputerMove() throws ColumnIsFullException, InvalidMoveException {
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(true, human);
		
		ConnectFourGameEngine.computerMove(board);
		
		assertTrue(board.getLastMove() >= 0 && board.getLastMove() <= 7);
		assertEquals(board.getStatus(), Status.ONGOING);	
		assertEquals(board.getNumberOfMoves(), 1);
	}
	
	@Test(expected = ColumnIsFullException.class)
	public void testHumanMoveColumnIsFull() throws ColumnIsFullException, InvalidMoveException {
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(true, human);
		
		ConnectFourGameEngine.humanMove(board, 1);
		ConnectFourGameEngine.humanMove(board, 1);
		ConnectFourGameEngine.humanMove(board, 1);
		ConnectFourGameEngine.humanMove(board, 1);
		ConnectFourGameEngine.humanMove(board, 1);
		ConnectFourGameEngine.humanMove(board, 1);
		ConnectFourGameEngine.humanMove(board, 1);
	}
	
	@Test(expected = InvalidMoveException.class)
	public void testInvalidMove() throws ColumnIsFullException, InvalidMoveException {
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(true, human);
		
		ConnectFourGameEngine.humanMove(board, Board.NUM_COLUMNS + 1);
	}
	
	@Test
	public void testDraw() throws ColumnIsFullException, InvalidMoveException {
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(true, human);
		
		int maxMoves = Board.NUM_COLUMNS * Board.NUM_ROWS;
		
		for(int i = 0; i < maxMoves; i++) {
			ConnectFourGameEngine.humanMove(board, i % Board.NUM_COLUMNS);
		}
		
		assertEquals(board.getStatus(), Status.DRAW);	
		assertEquals(board.getNumberOfMoves(), maxMoves);
	}
	
	@Test
	public void testWinByHorizontal() throws ColumnIsFullException, InvalidMoveException {
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(true, human);
		
		ConnectFourGameEngine.humanMove(board, 0);
		ConnectFourGameEngine.humanMove(board, 1);
		ConnectFourGameEngine.humanMove(board, 2);
		ConnectFourGameEngine.humanMove(board, 3);

		assertEquals(board.getStatus(), Status.OVER);
		assertEquals(board.isWinner(), true);
	}
	
	@Test
	public void testHorizontalGap() throws ColumnIsFullException, InvalidMoveException {
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(true, human);
		
		ConnectFourGameEngine.humanMove(board, 0);
		ConnectFourGameEngine.humanMove(board, 1);
		ConnectFourGameEngine.forceComputerMove(board, 2);
		ConnectFourGameEngine.humanMove(board, 3);
		ConnectFourGameEngine.humanMove(board, 4);

		assertEquals(board.getStatus(), Status.ONGOING);
		assertEquals(board.isWinner(), false);
	}
	
	@Test
	public void testWinByVertical() throws ColumnIsFullException, InvalidMoveException {
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(true, human);
		
		ConnectFourGameEngine.humanMove(board, 0);
		ConnectFourGameEngine.humanMove(board, 0);
		ConnectFourGameEngine.humanMove(board, 0);
		ConnectFourGameEngine.humanMove(board, 0);

		assertEquals(board.getStatus(), Status.OVER);
		assertEquals(board.isWinner(), true);
	}
	
	@Test
	public void testVerticalGap() throws ColumnIsFullException, InvalidMoveException {
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(true, human);
		
		ConnectFourGameEngine.humanMove(board, 0);
		ConnectFourGameEngine.humanMove(board, 0);
		ConnectFourGameEngine.humanMove(board, 0);
		ConnectFourGameEngine.forceComputerMove(board, 0);
		ConnectFourGameEngine.humanMove(board, 0);

		assertEquals(board.getStatus(), Status.ONGOING);
		assertEquals(board.isWinner(), false);
	}

}
