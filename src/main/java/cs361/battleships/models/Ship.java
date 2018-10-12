package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	private int size:
	private String name;
	@JsonProperty private List<Square> occupiedSquares;

	//initialize the ship types
	public Ship(String kind) {
		this.name = kind;
		name= name.toUppercase();
		if(name=="BATTLESHIP"){
			this.occupiedSquares = new ArrayList<>(4);

		}
		else if(name=="DESTROYER"){
			this.occupiedSquares = new Arraylist<>(3);
		}
		else {
			this.occupiedSquares = new ArrayList<>(2);
		}
	}

	public Ship() {
		occupiedSquares = new ArrayList<>();
	}

	public List<Square> getOccupiedSquares() {
		return occupiedSquares;
	}
}

	public void setOccupiedSquares(List<Square> occupiedSquares) {
		this.occupiedSquares = occupiedSquares;
	}

	public int getSize() {
		return size;
	}

	public String getName() {
		return name;
	}
