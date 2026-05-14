package com.tetris.ui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import com.tetris.game.Game;
import com.tetris.model.Board;
import com.tetris.model.Tetromino;

public class GameRenderer {

    private static final int CELL_SIZE = 35;

    public void render(Pane root, Game game) {
        root.getChildren().clear();
        Board board = game.getBoard();
        int[][] grid = board.getGrid();

        // Dibujar el grid
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);

                cell.setX(col * CELL_SIZE);
                cell.setY(row * CELL_SIZE);

                if (grid[row][col] == 1) {
                    cell.setFill(Color.CYAN);
                } else {
                    cell.setFill(Color.BLACK);
                }

                cell.setStroke(Color.GRAY);
                root.getChildren().add(cell);
            }
        }

        drawCurrentPiece(root, game.getCurrentPiece());
    }

    private void drawCurrentPiece(Pane root, Tetromino piece) {

        int[][] shape = piece.getShape();
        int startRow = piece.getPosition().getRow();
        int startCol = piece.getPosition().getCol();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 0)
                    continue;

                Rectangle block = new Rectangle(CELL_SIZE - 1, CELL_SIZE - 1);

                block.setX((startCol + col)* CELL_SIZE);
                block.setY((startRow + row)* CELL_SIZE);

                block.setFill(piece.getColor());

                block.setStroke(Color.BLACK);
                root.getChildren().add(block);
            }
        }
    }
}