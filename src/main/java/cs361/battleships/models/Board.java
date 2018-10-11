package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	 //Private variables
	private char[][] board;

	 //public variables
	public static int NUM_ROWS = 10;
	public static int NUM_COLS = 10;

	public Board() {
		// TODO Implement
		this.board = new char[NUM_ROWS][NUM_COLS];
		this.initializeBoard();
	}

	public void initializeBoard(){
			for(int row = 0; row < NUM_ROWS; row++){
				for(int col = 0; col < NUM_COLS; col++){
					this.board[row][col] = "";
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
