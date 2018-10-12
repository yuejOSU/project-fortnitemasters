package cs361.battleships.models;

@SuppressWarnings("unused")
public class Square {

	private int row;
	private char column;
	//used to check if current position is filled
	private boolean filled;

	public Square(int row, char column) {
		this.row = row;
		this.column = column;
		this.filled = false;
	}

	public char getColumn() {
		return column;
	}

	public void setColumn(char column) {
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setFilled(boolean filled) { this.filled = filled;}
	//returns if the current element is filled with a ship.
	public boolean getFilled () { return filled;}
}
