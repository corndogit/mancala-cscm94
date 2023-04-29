package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.Arrays;

/**
 * Provides functionality for the Board component of the game, and provides methods for moving the pieces around the
 * board, selecting the next player's turn and determining the win state.
 * @author Nathan Brenton
 */
public class BoardView {
    @FXML
    protected Label p1Score;
    @FXML
    protected Label p2Score;
    @FXML
    protected Label playerTurnLabel;
    @FXML
    protected ImageView p1StoreView;
    @FXML
    protected ImageView p2StoreView;
    @FXML
    protected GridPane stones;
    @FXML
    protected Label hoverText;

    private String[] players = new String[]{"P1", "P2"};  // todo: load names from profile in the future
    private String playerTurn = players[0];
    private final int[][] holes = new int[2][6];
    private final int[] stores = new int[2];
    private boolean isFinished = false;

    /**
     * Sets up the game once a Game is instantiated
     */
    public void initialize() {
        final int INITIAL_VALUE = 4;
        for (int i = 0; i < holes.length; i++) {
            for (int j = 0; j < holes[0].length; j++) {
                holes[i][j] = INITIAL_VALUE;
            }
        }
        // playerTurn = players[Math.abs(random.nextInt()) % 2];  // randomly chooses a player (not implemented)
        updateView();
    }

    /**
     * Gets a hole value
     * @param row row index
     * @param col column index
     * @return value stored at board position [row][col]
     */
    private int getHoleValue(int row, int col) {
        return holes[row][col];
    }

    /**
     * Refreshes the UI, updating the view of holes and stores
     */
    private void updateView() {
        p1Score.setText(String.format("%s: %d", players[0], stores[0]));
        p2Score.setText(String.format("%s: %d", players[1], stores[1]));
        if (!isFinished) {
            playerTurnLabel.setText(String.format("%s's turn", playerTurn));
        }
        p1StoreView.setImage(loadStoneImage(stores[0]));
        p2StoreView.setImage(loadStoneImage(stores[1]));
        for (Node hole: stones.getChildren()) {
            int row = GridPane.getRowIndex(hole);
            int col = GridPane.getColumnIndex(hole);
            Image stoneImage = loadStoneImage(getHoleValue(row, col));
            ((ImageView) hole).setImage(stoneImage);
        }
    }

    /**
     * Loads the image file corresponding to the correct value given.
     * @param value number of stones in a hole or store
     * @return Image matching the number of stones
     */
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

    /**
     * Shows the value text when a node is hovered over with the cursor.
     * @param e Event fired by the hovered node
     */
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

    /**
     * Hides the value text when a target node is no longer being hovered.
     */
    @FXML
    protected void onMouseHoverHoleExit() {
        hoverText.setVisible(false);
    }

    /**
     * Gets information from the clicked node and passes it to internal methods.
     * @param e Event fired by the clicked node
     */
    @FXML
    protected void onMouseClickHole(MouseEvent e) {
        if (isFinished) {
            return;
        }
        ImageView origin = (ImageView) e.getSource();
        int row = GridPane.getRowIndex(origin);
        int col = GridPane.getColumnIndex(origin);
        makeMove(row, col, playerTurn);
        updateView();
    }

    /**
     * Calculates the next move of the game.
     * @param row row index
     * @param col column index
     * @param player name of the player that made the move
     */
    public void makeMove(int row, int col, String player) {
        // validate move
        boolean correctRow = (players[0].equals(player) && row == 0) || (players[1].equals(player) && row == 1);
        boolean holeIsNotEmpty = holes[row][col] > 0;

        if (!correctRow || !holeIsNotEmpty) {
            return;  // do nothing - the move is invalid
        }

        // take stones from the selected hole
        int moves = holes[row][col];
        holes[row][col] = 0;

        // calculate board moves
        int posX = col;
        int posY = row;
        for (int i = 0; i < moves; i++) {
            if (posY == 1) {  // bottom row
                posX++;
                if (posX >= holes[row].length) {
                    if (players[1].equals(player)) {
                        stores[1]++;
                    } else {
                        posX = holes[row].length - 1;
                        holes[0][posX]++;
                    }
                    posY = 0;
                } else {
                    holes[posY][posX]++;
                }
            } else {  // top row
                posX--;
                if (posX < 0) {
                    if (players[0].equals(player)) {
                        stores[0]++;
                    } else {
                        posX = 0;
                        holes[1][posX]++;
                    }
                    posY = 1;
                } else {
                    holes[posY][posX]++;
                }
            }
        }

        // check for win
        String winner = checkForWin();
        if (winner != null) {
            if ("tie".equals(winner)) {
                playerTurnLabel.setText("It's a tie!");
            } else {
                playerTurnLabel.setText(winner + " wins!");
            }
            isFinished = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Winner!");
            alert.setHeaderText(winner + " wins the game!");
            alert.setContentText("Click exit to return to the main menu.");
            alert.showAndWait();
            return;
        }

        // set next player's turn
        boolean p1Bonus = (posX < 0) && players[0].equals(player);
        boolean p2Bonus = (posX >= holes[posY].length) && players[1].equals(player);
        if (p1Bonus || p2Bonus) {  // repeat turn (do not swap)
            playerTurnLabel.setText(playerTurn + "'s bonus turn");
            return;
        }
        playerTurn = players[0].equals(playerTurn) ? players[1] : players[0];  // switch players
        playerTurnLabel.setText(playerTurn + "'s turn");
    }

    /**
     * Checks if either of the sides of the board are empty, giving the remaining pieces to whichever player still
     * has stones on their side of the board.
     * @return the name of the player with no stones left
     */
    private String checkForWin() {
        int topRowSum = Arrays.stream(holes[0]).sum();
        int bottomRowSum = Arrays.stream(holes[1]).sum();

        if (topRowSum == 0 || bottomRowSum == 0) {
            if (topRowSum == 0) {
                stores[1] += bottomRowSum;
            } else {
                stores[0] += topRowSum;
            }

            updateView();

            if (stores[0] > stores[1]) {
                return players[0];
            } else if (stores[1] > stores[0]) {
                return players[1];
            }
            return "tie";
        }
        return null;
    }
}
