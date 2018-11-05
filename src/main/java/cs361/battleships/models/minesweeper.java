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

    public void setCaptainsQuarters(int row, char column, boolean vertical) {
        String col = "ABCDEFGHIJ";
        int col = 0;
        if (vertical != 0) {
            this.captainsQuarters = new Square(row + 2, column);
        } else {
            for (int i = 0; i < col.length(); i++) {
                if (col.charAt(i) == column) {
                    col = i;
                    break;
                }
            }
            this.captainsQuarters = new Square(row, col.charAt(col));
        }
    }
}