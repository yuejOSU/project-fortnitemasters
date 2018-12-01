package cs361.battleships.models;

import java.util.ArrayList;

public class submarine extends Ship{
    public submarine() {
        this.occupiedSquares = new ArrayList<Square>(); // constructs list
        this.kind = "SUBMARINE"; // sets kind equal to what was passed in

        for (int i=0; i<5; i++) {
            this.occupiedSquares.add(new Square(0, 'A'));
        }
    }
}
