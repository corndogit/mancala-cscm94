package com.swansea.mancala;

public class Hole {
    protected int value;

    public Hole(int value) {
        this.value = value;
    }

    public void addPiece() {
        this.value++;
    }

    public void removePiece() {
        this.value--;
    }

    public int getValue() {
        return value;
    }
}
