package cs361.battleships.models;

import java.util.ArrayList;

public class minesweeper extends Ship {

    public minesweeper() {
        this.occupiedSquares = new ArrayList<Square>(); // constructs list
        this.kind = "MINESWEEPER"; // sets kind equal to what was passed in

        for (int i=0; i<2; i++) {
            this.occupiedSquares.add(new Square(0, 'A'));
        }

    }
}