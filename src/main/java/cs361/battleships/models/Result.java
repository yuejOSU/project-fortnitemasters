package cs361.battleships.models;

public class Result {
	private AtackStatus result;
	private Ship ship;
	private Square square;


	public AtackStatus getResult() {
		//TODO implement
		return result;
	}

	public void setResult(AtackStatus result) {
		//TODO implement
		this.result = result;
	}

	public Ship getShip() {
		//TODO implement
		return ship;
	}

	public void setShip(Ship ship) {
		//TODO implement
		this.ship = ship;
	}

	public Square getLocation() {
		//TODO implement
		return square;
	}

	public void setLocation(Square square) {
		//TODO implement
		this.square = square;
	}
}
