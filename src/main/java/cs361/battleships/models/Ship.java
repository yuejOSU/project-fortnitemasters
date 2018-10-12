package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Ship {

	@JsonProperty private List<Square> occupiedSquares;

	//initialize the ship types
	public Ship(String kind) {
		this.kind = kind;
		kind = kind.toUppercase();
		if(kind=="BATTLESHIP"){
			this.occupiedSquares = new ArrayList<>(4);

		}
		else if(kind=="DESTROYER"){
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
