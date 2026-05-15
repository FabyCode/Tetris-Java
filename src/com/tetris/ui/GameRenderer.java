package com.tetris.ui;

import com.tetris.game.Game;
import com.tetris.model.Board;
import com.tetris.model.Tetromino;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameRenderer {

    private static final int CELL_SIZE = 35;

    public void render(Pane gameLayer, Game game) {

        gameLayer.getChildren().clear();

        drawBoard(gameLayer, game.getBoard());

        drawCurrentPiece(
                gameLayer,
                game.getCurrentPiece());
    }

    private void drawBoard(
            Pane layer,
            Board board) {

        int[][] grid = board.getGrid();

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[row].length; col++) {
                Rectangle cell = new Rectangle(
                        CELL_SIZE - 1,
                        CELL_SIZE - 1);

                cell.setX(
                        col * CELL_SIZE);
                cell.setY(
                        row * CELL_SIZE);

                int pieceId = grid[row][col];
                cell.setFill(
                        ColorMapper.getColor(
                                pieceId));
                cell.setStroke(
                        Color.web("#333333"));
                layer.getChildren()
                        .add(cell);
            }
        }
    }

    private void drawCurrentPiece(
            Pane layer,
            Tetromino piece) {

        int[][] shape = piece.getShape();

        int startRow = piece.getPosition().getRow();

        int startCol = piece.getPosition().getCol();

        for (int row = 0; row < shape.length; row++) {

            for (int col = 0; col < shape[row].length; col++) {

                if (shape[row][col] == 0) {
                    continue;
                }

                Rectangle block = new Rectangle(
                        CELL_SIZE - 1,
                        CELL_SIZE - 1);

                block.setX(
                        (startCol + col)
                                * CELL_SIZE);

                block.setY(
                        (startRow + row)
                                * CELL_SIZE);

                block.setFill(
                        piece.getColor());

                block.setStroke(
                        Color.WHITE);

                layer.getChildren()
                        .add(block);
            }
        }
    }
}