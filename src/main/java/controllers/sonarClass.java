package controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs361.battleships.models.Game;

public class sonarClass {
    @JsonProperty
    private Game game;

    @JsonProperty
    private int x;

    @JsonProperty
    private char y;

    @JsonProperty
    private int sonarCount;

    public Game getGame()
    {
        return game;
    }

    public int getRow()
    {
        return x;
    }

    public char getColumn()
    {
        return y;
    }

    public int getsonarCount()
    {
        return sonarCount;
    }
}
