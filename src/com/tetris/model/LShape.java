package com.tetris.model;

import javafx.scene.paint.Color;

/**
 * Representa la pieza tipo L del juego Tetris.
 * <p>
 * Esta pieza está formada por cuatro bloques organizados
 * en una estructura similar a la letra "L". Su diseño permite
 * adaptarse a esquinas y espacios verticales dentro del tablero.
 * </p>
 * <p>
 * Hereda el comportamiento común definido en la clase
 * {@code Tetromino}, incluyendo movimiento, rotación
 * y control de posición.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class LShape extends Tetromino {

    /**
     * Inicializa la forma y el color de la pieza tipo L.
     * <p>
     * La pieza tiene una estructura vertical con una extensión
     * lateral en la parte inferior, y utiliza el color naranja
     * para su representación visual.
     * </p>
     */
    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,0},
            {1,0},
            {1,1}
        };

        setColor(Color.ORANGE);
    }
}