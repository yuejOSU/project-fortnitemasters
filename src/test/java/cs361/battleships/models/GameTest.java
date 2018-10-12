package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void testPlaceShip() {
        Game game = new Game();
        assertTrue(game.placeShip(new Ship("BATTLESHIP"), 1, 'D', false));
        assertFalse(game.placeShip(new Ship("BATTLESHIP"), 1, 'A', false));
    }
