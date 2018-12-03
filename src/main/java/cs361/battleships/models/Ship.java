package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import com.mchange.v1.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Ship {

	@JsonProperty protected String kind;
	@JsonProperty protected List<Square> occupiedSquares;
	@JsonProperty protected int size;
	//@JsonProperty protected Armor armor;
	@JsonProperty protected Square captainsQuarters;
	protected boolean underwater;

	public Ship() {
		occupiedSquares = new ArrayList<>();
	}
	
	public Ship(String kind) {
		this();
		this.kind = kind;
		switch(kind) {
			case "MINESWEEPER":
				this.size = 2;
				break;
			case "DESTROYER":
				this.size = 3;
				break;
			case "BATTLESHIP":
				this.size = 4;
				break;
			case "SUBMARINE":
				this.size = 5;
				break;
		}
	}

	public List<Square> getOccupiedSquares() {
		return occupiedSquares;
	}

	public void place(char col, int row, boolean isVertical) {
		for (int i=0; i<size; i++) {
			if(size == 5){
				if(isVertical) {
					occupiedSquares.add(new Square(row, col));
					occupiedSquares.add(new Square(row+1, col));
					occupiedSquares.add(new Square(row+1, (char)((int)col + 1)));
					occupiedSquares.add(new Square(row+2, col));
					occupiedSquares.add(new Square(row+3, col));
				}
				else {
					occupiedSquares.add(new Square(row, (char) (col)));
					occupiedSquares.add(new Square(row, (char) (col + 1)));
					occupiedSquares.add(new Square(row, (char) (col + 2)));
					occupiedSquares.add(new Square(row - 1, (char) (col + 2)));
					occupiedSquares.add(new Square(row, (char) (col + 3)));

				}
			}
			else {
				if (isVertical) {
					occupiedSquares.add(new Square(row + i, col));
				}
				else {
					occupiedSquares.add(new Square(row, (char) (col + i)));
				}
			}

		}
	}

	public boolean overlaps(Ship other) {
		Set<Square> thisSquares = Set.copyOf(getOccupiedSquares());
		Set<Square> otherSquares = Set.copyOf(other.getOccupiedSquares());
		Sets.SetView<Square> intersection = Sets.intersection(thisSquares, otherSquares);
		return intersection.size() != 0;
	}

	public boolean isAtLocation(Square location) {
		return getOccupiedSquares().stream().anyMatch(s -> s.equals(location));
	}

	//public void setCaptainsQuarters(int x, char y, boolean b) {}

	public Square getCaptainsQuarters() {
		return this.captainsQuarters;
	}

	public String getKind() {
		return this.kind;
	}
/*
	public void setArmor() {
		this.armor = armor;
	}

	public Armor getArmor() {
        return this.armor;
    }

	public void hitCQ() {
		this.armor.setArmor(this.armor.getArmor() -1);
	}

	public boolean sunkCQ() {
		if (this.armor.getArmor() <= 0) {
			return false;
		}
		else {
			return true;
		}
	}
*/
	public Result attack(int x, char y) {
		var attackedLocation = new Square(x, y);
		var square = getOccupiedSquares().stream().filter(s -> s.equals(attackedLocation)).findFirst();
		if (!square.isPresent()) {
			return new Result(attackedLocation);
		}
		var attackedSquare = square.get();
		if (attackedSquare.isHit()) {
			var result = new Result(attackedLocation);
			result.setResult(AtackStatus.INVALID);
			return result;
		}
		attackedSquare.hit();
		var result = new Result(attackedLocation);
		result.setShip(this);
		if (isSunk()) {
			result.setResult(AtackStatus.SUNK);
		} else {
			result.setResult(AtackStatus.HIT);
		}
		return result;
	}

	@JsonIgnore
	public boolean isSunk() {
		return getOccupiedSquares().stream().allMatch(s -> s.isHit());
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Ship)) {
			return false;
		}
		var otherShip = (Ship) other;

		return this.kind.equals(otherShip.kind)
				&& this.size == otherShip.size
				&& this.occupiedSquares.equals(otherShip.occupiedSquares);
	}

	@Override
	public int hashCode() {
		return 33 * kind.hashCode() + 23 * size + 17 * occupiedSquares.hashCode();
	}

	@Override
	public String toString() {
		return kind + occupiedSquares.toString();
	}

	public boolean getUnderwater(){
		return underwater;
	}

	public void setUnderwater(){
		underwater = false;
	}

	public int getSize(){
		return size;
	}
}


