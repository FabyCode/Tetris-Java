package com.tetris.ui;

import com.tetris.model.Tetromino;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PreviewRenderer {

    private static final int PREVIEW_CELL = 25;

    public void render(
            Pane previewPane,
            Tetromino piece) {

        previewPane.getChildren().clear();

        if (piece == null) {
            return;
        }

        int[][] shape = piece.getShape();

        // Centrado visual aproximado
        int offsetX = 25;
        int offsetY = 25;

        for (int row = 0; row < shape.length; row++) {

            for (int col = 0; col < shape[row].length; col++) {

                if (shape[row][col] == 0) {
                    continue;
                }

                Rectangle block = new Rectangle(
                        PREVIEW_CELL - 2,
                        PREVIEW_CELL - 2);

                block.setX(
                        offsetX +
                                col * PREVIEW_CELL);

                block.setY(
                        offsetY +
                                row * PREVIEW_CELL);

                block.setFill(
                        piece.getColor());

                block.setStroke(
                        Color.WHITE);

                previewPane
                        .getChildren()
                        .add(block);
            }
        }
    }
}