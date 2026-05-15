package com.tetris.ui;

import com.tetris.model.Tetromino;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Se encarga de renderizar la vista previa de piezas en la interfaz.
 * <p>
 * Esta clase permite mostrar representaciones reducidas de tetrominós,
 * como la siguiente pieza por aparecer o la pieza almacenada
 * mediante la mecánica hold.
 * </p>
 * <p>
 * Utiliza componentes gráficos de JavaFX para dibujar
 * cada bloque de la pieza dentro de un panel independiente.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class PreviewRenderer {

    /**
     * Tamaño en píxeles de cada bloque
     * dentro de la vista previa.
     */
    private static final int PREVIEW_CELL = 25;

    /**
     * Renderiza una pieza en un panel de vista previa.
     * <p>
     * El panel se limpia antes de dibujar la nueva pieza.
     * Si no existe una pieza para mostrar, el método finaliza
     * sin realizar ninguna operación.
     * </p>
     *
     * @param previewPane panel donde se dibujará la vista previa.
     * @param piece pieza que será representada.
     */
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