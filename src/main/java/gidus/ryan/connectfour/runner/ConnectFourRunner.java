package gidus.ryan.connectfour.runner;

import java.util.Scanner;

import gidus.ryan.connectfour.Board;
import gidus.ryan.connectfour.ColumnIsFullException;
import gidus.ryan.connectfour.ConnectFourGameManager;
import gidus.ryan.connectfour.GamePiece;
import gidus.ryan.connectfour.InvalidMoveException;
import gidus.ryan.connectfour.Status;

public class ConnectFourRunner {
	public static void main (String[] args) {
		ConnectFourGameManager gameManager = ConnectFourGameManager.getInstance();
		
		System.out.println("Welcome to ConnectFour!\n");

		while(true) {
			Scanner input = new Scanner(System.in);

			GamePiece humanGamePiece = promptForColor(input);
			boolean isFirst = promptIsFirst(input);
			
			Board board = gameManager.newBoard(isFirst, humanGamePiece);
			String boardId = board.getId();
			
			printBoard(board);
			
			do{
				System.out.println("Please select the column which you would like to move (1-7)?");
				int column = input.nextInt();
				
				try {
					board = gameManager.playerMove(boardId, column-1);
				} catch (ColumnIsFullException e) {
					System.out.println("Cannot move there, column is full");
					continue;
				} catch (InvalidMoveException e) {
					System.out.println("Please enter value between 1 and 7.");
					continue;
				}
				printBoard(board);
			}
			while(board.getStatus() == Status.ONGOING);
			
			printResults(board);
			gameManager.removeBoard(boardId);
			
			//TODO fix this and move to own function
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
	
	private static boolean promptIsFirst(Scanner input) {
		System.out.println("Would you like to go first (y/n)?");
		String isFirstString = input.nextLine();
		boolean isFirst = false;
		if(isFirstString.toUpperCase().startsWith("Y")) {
			isFirst = true;
		}
		
		return isFirst;
	}
	
	private static GamePiece promptForColor(Scanner input) {
		System.out.println("Please choose a red or black game piece color (r/b).");
		String gamePieceString = input.nextLine();
		GamePiece gamePiece = GamePiece.getValue(gamePieceString.toUpperCase().charAt(0));
		return gamePiece;
	}
	
	private static void printResults(Board board) {
		if(board.getStatus() == Status.DRAW) {
			System.out.println("Game ends in a draw");
		}
		else if(board.getStatus() == Status.OVER && board.isWinner()) {
			System.out.println("Congratulations you won!\n");
		}
		else {
			System.out.println("Sorry, you have lost.");
		}
	}
}
