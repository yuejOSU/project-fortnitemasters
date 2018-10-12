package cs361.battleships.models;

public class Result {
	//variables defined here
	private AttackStatus result;
	private Ship ship;
	private Square square;

  
	//Getters and Setters initialized below
	public AttackStatus getResult() {
		return result;
	}

	public void setResult(AttackStatus result) {
		this.result = result;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public Square getLocation() {
		return square;
	}

	public void setLocation(Square square) {
		this.square = square;
	}
}
