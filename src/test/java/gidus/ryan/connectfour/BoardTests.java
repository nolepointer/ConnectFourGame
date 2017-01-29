package gidus.ryan.connectfour;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTests {
	
	@Test
	public void testDeclareWinnerHuman(){
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(human);
		board.declareWinner(human);
		
		assertTrue(board.isWinner());
	}
	
	@Test
	public void testDeclareWinnerComputer(){
		GamePiece human = GamePiece.BLACK;
		Board board = new Board(human);
		board.declareWinner(GamePiece.RED);
		
		assertFalse(board.isWinner());
	}

}
