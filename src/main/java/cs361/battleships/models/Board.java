package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

	@JsonProperty
	private List<Ship> ships;
	@JsonProperty
	private List<Result> attacks;
	@JsonProperty
	private List<Square> sonars;
	private int rows;
	private int cols;
	private int numSonars;

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		this.rows = 10;
		this.cols = 10;
		this.numSonars = 2;
		ships = new ArrayList<>();
		attacks = new ArrayList<>();
		this.sonars = new ArrayList<>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		if (ships.size() >= 4) {
			return false;
		}
		if (ships.stream().anyMatch(s -> s.getKind().equals(ship.getKind()))) {
			return false;
		}
		final var placedShip = new Ship(ship.getKind());
		placedShip.place(y, x, isVertical);
		if (ships.stream().anyMatch(s -> s.overlaps(placedShip))) {
			return false;
		}
		if (placedShip.getOccupiedSquares().stream().anyMatch(s -> s.isOutOfBounds())) {
			return false;
		}
		ships.add(placedShip);
		ship.setCaptainsQuarters(x, y, isVertical);
		return true;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {

		Result result = new Result();

		// check for attack result in bounds of grid
		if (x < 1 || x > 10 || y < 'A' || y > 'J') {
			result.setResult(AtackStatus.INVALID);
			return result;
		}

		// check to see if attack location has been chosen before
		for (Result allResults: this.attacks) {
			if ((allResults.getLocation().getColumn() == y) && (allResults.getLocation().getRow() == x)) {
				result.setResult(AtackStatus.INVALID);
				return result;
			}
		}

		this.attacks.add(result);

		for (Ship ships: this.ships) {
			List<Square> taken = ships.getOccupiedSquares();

			for (Square occupied : taken) {
				if ((occupied.getRow() == x) && (occupied.getColumn() == y)) {
					result.setResult(AtackStatus.HIT);
					result.setLocation(occupied);
					return result;
				}
				else {
					Square square = new Square(x,y);
					result.setLocation(square);
					result.setResult(AtackStatus.MISS);
				}
			}
		}

		return result;
	}

	public boolean sonar(int x, char y, int numSonars) {

		// conditional to return false if all sonars are used or no ships have been sunk
		if (!ifSonar() || numSonars == 0) {
			return false;
		}

		int r = 2;
		int i;

		List<Square> squares = new ArrayList<>();

		// loop to create the radius for the sonar just like in 'game.js'
		for (i = 0; i < (r + 1); i++) {
			squares.add(new Square((x - i), y));
			squares.add(new Square((x + i), y));
			// typecasting is need for the rest of these because we cannot pass in 'int' for function
			squares.add(new Square(x, (char) ((int) y - i)));
			squares.add(new Square(x, (char) ((int) y + i)));
			squares.add(new Square((x - 1), (char) ((int) y - 1)));
			squares.add(new Square((x - 1), (char) ((int) y + 1)));
			squares.add(new Square((x + 1), (char) ((int) y - 1)));
			squares.add(new Square((x + 1), (char) ((int) y + 1)));
		}

		for (Square square : squares) {
			// this adds the squares that the sonar is used on; if there are square that aren't on the board, then
			// we just ignore it, but if it is in it, then we add it to sonar list
			if (square.getRow() <= this.rows && square.getRow() > 0 && ((int) square.getColumn() - 65) < this.cols && ((int) square.getColumn() - 65) >= 0) {
				this.sonars.add(square);
			}
		}

		return true;

	}

	private boolean ifSonar() {

		// checks to see if any ships have been sunk in order to use the sonar weapon
		for (Result r : this.attacks) {
			if (r.getResult() == AtackStatus.SUNK) {
				return true;
			}
		}

		return false;

	}

	List<Ship> getShips() {
		return ships;
	}

	public List<Square> getSonars() {
		return this.sonars;
	}

	public int getRows() {
		return this.rows;
	}

	public int getCols() {
		return this.cols;
	}
}
