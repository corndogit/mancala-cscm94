package com.swansea.mancala;

/**
 * Provides functionality for the Board class.
 * @author
 */
public class BoardController {
    private int[][] holes = new int[2][6];
    private int[] stores = new int[2];
    private final int INITIAL_VALUE = 4;

    public void initialize() {
        for (int i = 0; i < holes.length; i++) {
            for (int j = 0; j < holes[0].length; j++) {
                holes[i][j] = INITIAL_VALUE;
            }
        }
    }

    private int getHoleValue(int row, int col) {
        return holes[row][col];
    }

    private void updateView() {

    }
}
