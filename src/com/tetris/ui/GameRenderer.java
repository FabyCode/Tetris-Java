package com.tetris.ui;

import com.tetris.game.Game;
import com.tetris.model.Board;
import com.tetris.model.Tetromino;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Se encarga de la representación gráfica del juego Tetris.
 * <p>
 * Esta clase renderiza visualmente el tablero y la pieza activa
 * utilizando componentes gráficos de JavaFX. Su responsabilidad
 * es traducir el estado lógico del juego en elementos visuales
 * mostrados en pantalla.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class GameRenderer {

    /**
     * Tamaño en píxeles de cada celda del tablero.
     */
    private static final int CELL_SIZE = 35;

    /**
     * Renderiza el estado actual del juego.
     * <p>
     * Limpia la capa gráfica actual y dibuja nuevamente
     * el tablero junto con la pieza activa.
     * </p>
     *
     * @param gameLayer contenedor gráfico donde se dibuja el juego.
     * @param game instancia actual del juego.
     */
    public void render(Pane gameLayer, Game game) {

        gameLayer.getChildren().clear();

        drawBoard(gameLayer, game.getBoard());

        drawCurrentPiece(
                gameLayer,
                game.getCurrentPiece());
    }

    /**
     * Dibuja el tablero y todas las celdas ocupadas o vacías.
     *
     * @param layer contenedor gráfico donde se dibujará el tablero.
     * @param board tablero actual del juego.
     */
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

    /**
     * Dibuja la pieza actualmente controlada por el jugador.
     * <p>
     * Cada bloque visible de la pieza se representa
     * como un rectángulo coloreado en su posición
     * correspondiente dentro del tablero.
     * </p>
     *
     * @param layer contenedor gráfico donde se dibujará la pieza.
     * @param piece pieza activa del juego.
     */
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