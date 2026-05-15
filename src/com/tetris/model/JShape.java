package com.tetris.model;

import javafx.scene.paint.Color;

/**
 * Representa la pieza tipo J del juego Tetris.
 * <p>
 * Esta pieza está compuesta por cuatro bloques organizados
 * en una forma similar a la letra "J". Su estructura permite
 * realizar ajustes en esquinas y espacios verticales dentro
 * del tablero.
 * </p>
 * <p>
 * Hereda el comportamiento general de la clase {@code Tetromino},
 * incluyendo movimiento, rotación y gestión de posición.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class JShape extends Tetromino {

    /**
     * Inicializa la forma y el color de la pieza tipo J.
     * <p>
     * La pieza adopta una estructura vertical con una extensión
     * lateral en la parte inferior, y utiliza el color azul
     * para su representación visual.
     * </p>
     */
    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {0,1},
            {0,1},
            {1,1}
        };

        setColor(Color.BLUE);
    }
}