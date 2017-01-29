package gidus.ryan.connectfour;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConnectFourGameManagerTests {
	
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
	
	
	//removeBoard valid invalid
	
	//makemoves

}
