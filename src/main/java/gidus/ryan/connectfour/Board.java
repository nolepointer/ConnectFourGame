package gidus.ryan.connectfour;

import java.util.UUID;

//TODO make this sixbyseven board and board an interface
public class Board {
	
	private String boardId;
	public static final int NUM_ROWS = 6;
	public static final int NUM_COLUMNS = 7;
	private GamePiece[][] gameBoard;
	private Status status;
	
	private boolean isFirst = true;
	private boolean isWinner = false;
	private GamePiece humanPiece;
	private GamePiece computerPiece;
	
	private int lastMove = -1;
	private int numMoves = 0;
	
	Board(GamePiece human) {
		this.isFirst = isFirst;
		this.boardId = UUID.randomUUID().toString();
		this.status = Status.ONGOING;
		initializeGamePieces(human);
		initializeBoard();
	}
	
	void declareWinner(GamePiece winner) {
		this.status = Status.OVER;
		if(winner == this.humanPiece) {
			isWinner = true;
		}
	}
	
	void declareDraw() {
		this.status = Status.DRAW;
	}
	
	void addMove() {
		numMoves++;
	}
	
	void setLastMove(int position) {
		this.lastMove = position;
	}
	
	void setIsFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	
	public int getNumberOfMoves() {
		return numMoves;
	}
	
	public int getLastMove() {
		return lastMove;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public String getId() {
		return boardId;
	}
	
	public GamePiece[][] getGameBoard() {
		return gameBoard;
	}
	
	public GamePiece getHumanGamePiece() {
		return humanPiece;
	}
	
	public GamePiece getComputerGamePiece() {
		return computerPiece;
	}
	
	public boolean isHumanFirst() {
		return isFirst;
	}
	
	public boolean isWinner() {
		return isWinner;
	}
	
	private void initializeGamePieces(GamePiece human) {
		//Do not allow a human to set their gamepiece to empty
		if(human == GamePiece.EMPTY) {
			human = GamePiece.RED;
		}
		this.humanPiece = human;
		
		if(human == GamePiece.BLACK) {
			this.computerPiece = GamePiece.RED;
		}
		else {
			this.computerPiece = GamePiece.BLACK;
		}
	}
	
	private void initializeBoard() {
		gameBoard = new GamePiece[NUM_ROWS][NUM_COLUMNS];
		for(int i = 0; i < NUM_ROWS; i++) {
			for(int j = 0; j < NUM_COLUMNS; j++) {
				gameBoard[i][j] = GamePiece.EMPTY;
			}
		}
	}
}
