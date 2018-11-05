package cs361.battleships.models;

import java.util.ArrayList;

public class destroyer extends Ship {

    public destroyer() {
        this.occupiedSquares = new ArrayList<Square>(); // constructs list
        this.kind = "DESTROYER"; // sets kind equal to what was passed in

        for (int i=0; i<3; i++) {
            this.occupiedSquares.add(new Square(0, 'A'));
        }

    }

    public void setCaptainsQuarters(int row, char column, boolean vertical) {
        String col = "ABCDEFGHIJ";
        int x = 0;
        if (vertical == true) {
            this.captainsQuarters = new Square(row + 2, column);
        } else {
            for (int i = 0; i < col.length(); i++) {
                if (col.charAt(i) == column) {
                    x = i;
                    break;
                }
            }
            this.captainsQuarters = new Square(row, col.charAt(1 + x));
        }
    }
}