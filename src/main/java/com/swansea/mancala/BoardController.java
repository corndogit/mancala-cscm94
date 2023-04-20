package com.swansea.mancala;

/**
 * Provides functionality for the Board class.
 * @author
 */
public class BoardController {
    protected Hole[][] holes = new Hole[2][6];
    protected final int INITIAL_VALUE = 4;

    public void initialize() {
        for (int i = 0; i < holes.length; i++) {
            for (int j = 0; j < holes[0].length; j++) {
                holes[i][j] = new Hole(INITIAL_VALUE);
            }
        }
    }
}
