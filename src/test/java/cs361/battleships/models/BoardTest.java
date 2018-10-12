package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BoardTest {

    @Test
    public void testInvalidPlacement() {
        Board board = new Board();
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));
    }
    @Test
		public void testOverlapsShips() {
			Board board = new Board();
			assertFalse(board.placeShip(new Ship("BATTLESHIP"), 5, 'A', false));
			assertFalse(board.placeShip(new Ship("MINESWEEPER"), 5, 'B', false));
			assertFalse(board.placeShip(new Ship("DESTROYER"), 5, 'A', false));
		}
	@Test

}
