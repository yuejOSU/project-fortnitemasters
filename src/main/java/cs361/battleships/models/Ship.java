package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	private int size;
	private String name;
	private int length;
	@JsonProperty
	private List<Square> occupiedSquares;

	//initialize the ship types
	public Ship(String kind) {
		this.name = kind.toUpperCase();
		occupiedSquares = new ArrayList<>();

		if (name.equals("BATTLESHIP")) {
			length = 4;
		} else if (name.equals("DESTROYER")) {
			length = 3;
		} else if (name.equals("MINESWEEPER")) {
			length = 2;
		} else {
			throw new IllegalArgumentException("This is not a valid ship!");
		}
	}

	public Ship() {
		occupiedSquares = new ArrayList<>();
	}

	public List<Square> getOccupiedSquares() {
		return occupiedSquares;
	}

	public void setOccupiedSquares(Square coordinate, boolean isVertical) {
		//this.occupiedSquares = occupiedSquares;
		int shipLength = getLength();

		int x = coordinate.getRow();
		char y = coordinate.getColumn();


		// if ship is placed vertically, occupy the
		// corresponding column, incrementing down rows
		if (isVertical) {
			for (int i = 0; i < shipLength; i++) {
				Square sq = new Square(x + i,y);
				occupiedSquares.add(sq);
			}
		}
		// if ship is placed horizontally, occupy the
		// corresponding row, incrementing down columns
		else {
			for (int i = 0; i < shipLength; i++) {
				Square sq = new Square(x,(char)(y+i));
				occupiedSquares.add(sq);
			}
		}

	}

	public int getSize() {
		return size;
	}

	public String getName() {
		return name;
	}

	public int getLength() {
		return length;
	}

}