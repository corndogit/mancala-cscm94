package com.swansea.mancala;

public class Store extends Hole{
    protected String player;  // todo: replace later with Player class

    public Store(int value, String player) {
        super(value);
        this.value = value;
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }

    @Override
    public int getValue() {
        return value;
    }
}
