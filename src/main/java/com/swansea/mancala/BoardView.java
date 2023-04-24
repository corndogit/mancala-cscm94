package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;

/**
 * Provides functionality for the Board class.
 * @author
 */
public class BoardView {
    @FXML
    public Parent BoardView;
    @FXML
    protected ImageView p1StoreView;
    @FXML
    protected ImageView p2StoreView;
    @FXML
    protected GridPane stones;
    @FXML
    protected Label hoverText;

    private String[] players = new String[]{"P1", "P2"};  // todo: load names from profile in the future
    private int[][] holes = new int[2][6];
    private int[] stores = new int[2];
    private final int INITIAL_VALUE = 4;

    public void initialize() {
        for (int i = 0; i < holes.length; i++) {
            for (int j = 0; j < holes[0].length; j++) {
                holes[i][j] = INITIAL_VALUE;
            }
        }
        updateView();
    }

    private int getHoleValue(int row, int col) {
        return holes[row][col];
    }

    private void updateView() {  // todo: implement method to refresh the UI
        p1StoreView.setImage(loadStoneImage(stores[0]));
        p2StoreView.setImage(loadStoneImage(stores[1]));
        for (Node hole: stones.getChildren()) {
            int row = GridPane.getRowIndex(hole);
            int col = GridPane.getColumnIndex(hole);
            Image stoneImage = loadStoneImage(getHoleValue(row, col));
            ((ImageView) hole).setImage(stoneImage);
        }
    }

    private Image loadStoneImage(int value){
        if (value < 0) {
            throw new IllegalArgumentException("Holes cannot have negative values");
        }
        URL url;

        if (value < 10){
            url = com.swansea.mancala.BoardView.class.getResource(String.format("assets/board/stone-%d.png", value));
        } else {
            url = com.swansea.mancala.BoardView.class.getResource("assets/board/stone-10plus.png");
        }

        assert url != null;  // exception thrown if URL does not point to a file
        return new Image(url.toString());
    }

    @FXML
    protected void onMouseHoverHole(MouseEvent e) {
        final String textPattern = "Value: %d";
        ImageView origin = (ImageView) e.getSource();
        if (origin.getParent() instanceof GridPane) {  // hovered image is a hole
            int row = GridPane.getRowIndex(origin);
            int col = GridPane.getColumnIndex(origin);
            hoverText.setText(String.format(textPattern, getHoleValue(row, col)));
        } else {  // hovered image is a store
            int value;
            if (origin.getId().equals("p1StoreView")) {
                value = stores[0];
            } else {
                value = stores[1];
            }
            hoverText.setText(String.format(textPattern, value));
        }
        hoverText.setVisible(true);
    }

    @FXML
    protected void onMouseHoverHoleExit() {
        hoverText.setVisible(false);
    }

    @FXML
    protected void onMouseClickHole(MouseEvent e) {
        ImageView origin = (ImageView) e.getSource();
        printBoard();
        updateView();
    }

    public void printBoard() {
        System.out.println("Player 2 Mancala: " + stores[1]);
        for (int i = 5; i >= 0; i--) {
            System.out.print(holes[0][i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 6; i++) {
            System.out.print(holes[1][i] + " ");
        }
        System.out.println();
        System.out.println("Player 1 Mancala: " + stores[0]);
    }
}
