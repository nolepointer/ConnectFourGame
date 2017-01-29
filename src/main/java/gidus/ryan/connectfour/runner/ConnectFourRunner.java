package gidus.ryan.connectfour.runner;

import java.util.Scanner;

import gidus.ryan.connectfour.Board;
import gidus.ryan.connectfour.ColumnIsFullException;
import gidus.ryan.connectfour.ConnectFourGameManager;
import gidus.ryan.connectfour.GamePiece;
import gidus.ryan.connectfour.Status;

public class ConnectFourRunner {
	public static void main (String[] args) {
		ConnectFourGameManager gameManager = ConnectFourGameManager.getInstance();
		
		System.out.println("Welcome to ConnectFour!\n");

		while(true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Please choose a red or black game piece color (r/b).");
			String gamePieceString = input.nextLine();
			GamePiece humanGamePiece = GamePiece.getValue(gamePieceString.toUpperCase().charAt(0));
			
			System.out.println("Would you like to go first (y/n)?");
			String isFirstString = input.nextLine();
			boolean isFirst = false;
			if(isFirstString.toUpperCase().startsWith("Y")) {
				isFirst = true;
			}
			
			Board board = gameManager.newBoard(isFirst, humanGamePiece);
			String boardId = board.getId();
			
			printBoard(board);
			
			do{
			System.out.println("Please select the column which you would like to move (1-7)?");
			int column = input.nextInt();
			if(column < 1 || column > 7) {
				System.out.println("Please enter value between 1 and 7.");
				continue;
			}
			
			try {
				board = gameManager.playerMove(boardId, column-1);
			} catch (ColumnIsFullException e) {
				System.out.println("Cannot move there, column is full");
				continue;
			}
			printBoard(board);
			}
			while(board.getStatus() == Status.ONGOING);		
			
			if(board.getStatus() == Status.DRAW) {
				System.out.println("Game ends in a draw");
			}
			else if(board.getStatus() == Status.OVER && board.isWinner()) {
				System.out.println("Congratulations you won!\n");
			}
			else {
				System.out.println("Sorry, you have lost.");
			}
			
			gameManager.removeBoard(boardId);
			
			System.out.println("Enter q to quit or any other key to play again...");
			String playAgain = input.nextLine();
			if(playAgain.toUpperCase().equals("Q")) {
				break;
			}
		
		}
		
		System.out.println("Application has ended.");
	}
	
	public static void printBoard(Board board) {
		for(int i = 0; i < Board.NUM_ROWS; i++) {
			System.out.print("| ");
			for(int j = 0; j < Board.NUM_COLUMNS; j++) {
				System.out.print(board.getGameBoard()[i][j].abbreviated() + "| ");;
			}
			System.out.println();
		}
		
		printFooter();
		
		int lastMove;
		if((lastMove = board.getLastMove()) != -1 && board.getStatus() == Status.ONGOING) {
			lastMove++;
			System.out.println("Computer moved to column " + lastMove + ".");
		}
		
	}
	
	private static void printFooter() {
		for(int i = 0; i < Board.NUM_COLUMNS; i++) {
			System.out.print("---");
		}
		System.out.println("-");
		for(int i = 1; i <= Board.NUM_COLUMNS; i++) {
			System.out.print(" " + i + " ");
		}
		System.out.println("\n");
	}
}
