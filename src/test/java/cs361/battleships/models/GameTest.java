package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void testPlaceShip() {
        Game game = new Game();
        assertTrue(game.placeShip(new Ship("BATTLESHIP"), 1, 'D', false));
        assertFalse(game.placeShip(new Ship("BATTLESHIP"), 1, 'A', false));
    }

    @Test
    public void testGoodRandRow() {
        Game game = new Game();
        for(int i=0; i<20; i++) {
            assertTrue(game.randRow() < 11 && game.randRow() > 0);
        }
    }

    @Test
    public void testGoodRandCol() {
        Game game = new Game();
        for(int i=0; i<20; i++) {
            assertTrue(game.randCol() >= 'A' && game.randCol() <= 'J');
        }
    }

    @Test
    public void testnotSonarPulseAttack() {
        Game game = new Game();
        boolean result = game.sonarPulseAttack(11, 'E', 2);
        assertFalse(result);
    }

    @Test
    public void testAttack()
    {
        Game game = new Game();
        Ship minesweeper = new Ship("MINESWEEPER");
        Ship destroyer = new Ship("DESTROYER");
        Ship battleship = new Ship("BATTLESHIP");
        game.placeShip(minesweeper, 1, 'A', true);
        game.placeShip(destroyer, 1, 'B', true);
        game.placeShip(battleship, 1, 'C', true);

        assertTrue(game.attack(1, 'A'));
    }



}