package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSquare{

    @Test
    public void SquareTest(){
        Square square = new Square();
        square.setColumn('C');
        square.setRow('1');
        assertEquals('C',square.getColumn());
        assertEquals('1',square.getRow());

    }

}