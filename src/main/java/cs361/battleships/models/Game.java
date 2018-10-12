package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random; // This will be important for random functions


import static cs361.battleships.models.AttackStatus.*;

public class Game {

    @JsonProperty private Board playersBoard = new Board();
    @JsonProperty private Board opponentsBoard = new Board();
    private String Alphabet = "ABCDEFGHIJ";
    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
    public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
        boolean successful = playersBoard.placeShip(ship, x, y, isVertical);
        if (!successful)
            return false;

        boolean opponentPlacedSuccessfully;
        do {
            // AI places random ships, so it might try and place overlapping ships
            // let it try until it gets it right
            opponentPlacedSuccessfully = opponentsBoard.placeShip(ship, randRow(), randCol(), randVertical());
        } while (!opponentPlacedSuccessfully);

        return true;
    }

    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
    public boolean attack(int x, char  y) {
        Result playerAttack = opponentsBoard.attack(x, y);
        if (playerAttack.getResult() == INVALID) {
            return false;
        }

        Result opponentAttackResult;
        do {
            // AI does random attacks, so it might attack the same spot twice
            // let it try until it gets it right
            opponentAttackResult = playersBoard.attack(randRow(), randCol());
        } while(opponentAttackResult.getResult() != INVALID);

        return true;
    }

    private char randCol() {
        char[] dims1 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
      //grab random char
        Random r = new Random();
        int rand = r.nextInt(10) +1;
        return dims1[rand];
        //return this.Alphabet.charAt(r.nextInt(10) + 1);
    }

    private int randRow() {
      //random num 0 - 10
        int[] dim2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        Random r = new Random();
        return dim2[r.nextInt(10) + 1];

    }

    private boolean randVertical() {
      //get a number 0 or 1
        Random ran = new Random();
        return ran.nextBoolean();
    }
}
