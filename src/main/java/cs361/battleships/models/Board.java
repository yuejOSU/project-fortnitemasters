package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	 //Private variables
	private Board[][] board;

	 //public variables
	public static int NUM_ROWS = 10;
	public static int NUM_COLS = 10;

	public Board() {
		// TODO Implement

		this.board = new Board[NUM_ROWS][NUM_COLS];
		this.initializeBoard();	//calls a method to initialize the game Board
	}

	//This method will initialize a 2-Dimensional array of Board Type
	public void initializeBoard(){
			for(int row = 0; row < this.board.length; row++){
				for(int col = 0; col < this.board[i].length; col++){
					this.board[row][col] = "";	//initializes every row and col to blank spaces
				}
			}
		}

	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		// TODO Implement
		return false;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {
		//TODO Implement
		return null;
	}

	public List<Ship> getShips() {
		//TODO implement
		return null;
	}

	public void setShips(List<Ship> ships) {
		//TODO implement
	}

	public List<Result> getAttacks() {
		//TODO implement
		return null;
	}

	public void setAttacks(List<Result> attacks) {
		//TODO implement
	}
}
