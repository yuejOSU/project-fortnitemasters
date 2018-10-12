package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	 //Private variables
	private Square[][] board;
	private List<Ship> ships;

	 //public variables
	public static int NUM_ROWS = 10;
	public static int NUM_COLS = 10;

	public Board() {
		this.board = board;
		this.initializeBoard();	//calls a method to initialize the game Board
		this.createShips();
		this.recordAttacks();
	}

	//This method will initialize a 2-Dimensional array of Board Type
	public void initializeBoard(){
		this.board = new Square[NUM_ROWS][NUM_COLS];
			for(int row = 0; row < this.board.length; row++){
				for(int col = 0; col < this.board[row].length; col++){
					this.board[row][col] = null;	//initializes every row and col to blank spaces
				}
			}
	}
	public void createShips(){
		ships = new ArrayList<Ship>();
	}
	public void recordAttacks(){
		//attacks = new ArrayList<Result>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		// the following is a very simple placing ship functionality
		// and does not include handlings like if the ship has already been
		// placed, checking squares for already occupied ships, etc...

		// simple check if ship coordinate placement is off the grid
		/*if (isVertical) {
			if(x+ship.getLength()-1 > NUM_ROWS) {
			    return false;
            }
		}
		else {
			if(y+ship.getLength()-1 > NUM_COLS) {
				return false;
			}
		}*/

		ship.setOccupiedSquares(new Square(x,y), isVertical);
		ships.add(ship);

		return true;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {
			//Result results = new Result();

			//Square square = new Square(x, y);

			//results.setLocation(square);

		return null;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public List<Result> getAttacks() {
		return null;
	}

	public void setAttacks(List<Result> attacks) {
		//this.attacks = attacks;
	}
}
