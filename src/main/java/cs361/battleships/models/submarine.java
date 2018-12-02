package cs361.battleships.models;

import java.util.ArrayList;

public class submarine extends Ship{
    public submarine() {
        this.size = 5;
        this.occupiedSquares = new ArrayList<Square>(); // create list of squares
        this.kind = "SUBMARINE"; // initialize the ship type
        this.underwater = false; // initially will be false
        for (int i=0; i<5; i++) {
            this.occupiedSquares.add(new Square(0, (char)('A'+1)));
        }
    }
}
