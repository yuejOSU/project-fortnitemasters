package cs361.battleships.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testInvalidPlacement() {
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));
    }

    @Test
    public void testPlaceMinesweeper() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true));
    }

    @Test
    public void testPlaceBattleship() {
        assertTrue(board.placeShip(new Ship("BATTLESHIP"), 1, 'A', true));
    }

    @Test
    public void testAttackEmptySquare() {
        board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true);
        Result result = board.attack(2, 'E');
        assertEquals(AtackStatus.MISS, result.getResult());
    }

    @Test
    public void testAttackShip() {
        Board board = new Board();
        Ship minesweeper = new Ship("MINESWEEPER");
        board.placeShip(minesweeper, 1, 'A', true);
        Result result = new Result();
        Result expected = new Result();
        expected.setResult(AtackStatus.HIT);

        result = board.attack(1, 'A');
        assertEquals(expected.getResult(), result.getResult());
    }

    @Test
    public void testSonar() {
        Board board = new Board();
        assertFalse(board.sonar(5, 'c', 2));
        assertFalse(board.sonar(5, 'c', 0));
    }

    @Test
    public void testAttackSameSquareMultipleTimes() {
        Ship minesweeper = new Ship("MINESWEEPER");
        board.placeShip(minesweeper, 1, 'A', true);
        board.attack(1, 'A');
        Result result = board.attack(1, 'A');
        assertEquals(AtackStatus.INVALID, result.getResult());
    }

    @Test
    public void testAttackSameEmptySquareMultipleTimes() {
        Board board = new Board();
        Ship minesweeper = new Ship("MINESWEEPER");
        board.placeShip(minesweeper, 1, 'A', true);

        Result result = new Result();
        result = board.attack(5, 'C');

        assertEquals(AtackStatus.MISS, result.getResult());
        result = board.attack(5, 'C');
        assertEquals(AtackStatus.INVALID, result.getResult());
    }


    @Test
    public void testPlaceMultipleShipsOfSameType() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true));
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 5, 'D', true));

    }


    @Test
    public void testCantPlaceMoreThan4Ships() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'A', false));
        assertTrue(board.placeShip(new Ship("BATTLESHIP"), 2, 'A', false));
        assertTrue(board.placeShip(new Ship("DESTROYER"), 3, 'A', false));
        assertTrue(board.placeShip(new Ship("SUBMARINE"), 5, 'A', false));
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 6, 'A', false));

    }

}
