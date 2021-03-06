package gidus.ryan.connectfour;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ConnectFourGameManagerTest {
	
	@Test
	public void testSingleton(){
		ConnectFourGameManager gameManager = ConnectFourGameManager.getInstance();
		String id = gameManager.newBoard(true, GamePiece.RED).getId();
		ConnectFourGameManager newGameManager = ConnectFourGameManager.getInstance();
		
		assertNotNull(newGameManager.getBoard(id));
	}
	
	@Test
	public void testNewBoard() {
		ConnectFourGameManager gameManager = ConnectFourGameManager.getInstance();
		String id = gameManager.newBoard(true, GamePiece.RED).getId();
		
		assertNotNull(gameManager.getBoard(id));

	}
	
	@Test
	public void removeBoardExists() {
		ConnectFourGameManager gameManager = ConnectFourGameManager.getInstance();
		String id = gameManager.newBoard(true, GamePiece.RED).getId();
		
		assertTrue(gameManager.removeBoard(id));
	}
	
	@Test
	public void removeBoardNotExists() {
		ConnectFourGameManager gameManager = ConnectFourGameManager.getInstance();
		gameManager.newBoard(true, GamePiece.RED).getId();
		
		assertFalse(gameManager.removeBoard("asdfsdf"));
		
	}
	
	@Test
	public void testPlayerMove() throws ColumnIsFullException, InvalidMoveException {
		ConnectFourGameManager gameManager = ConnectFourGameManager.getInstance();
		String id = gameManager.newBoard(true, GamePiece.RED).getId();
		gameManager.playerMove(id, 0);
		
		assertNotNull(gameManager.getBoard(id));
	}
	
}
