package cs361.battleships.models;

import java.util.ArrayList;

public class minesweeper extends Ship {

    public minesweeper() {
        this.occupiedSquares = new ArrayList<Square>(); // constructs list
        this.kind = "MINESWEEPER"; // sets kind equal to what was passed in

        for (int i=0; i<2; i++) {
            this.occupiedSquares.add(new Square(1+i, 'A'));
        }

    }
/*
    public void setCaptainsQuarters(int row, char column, boolean vertical){
        this.captainsQuarters = new Square(row, column);
        Armor armor = new Armor();
        armor.setArmor(0);
        this.armor = armor;

    }
    */
}