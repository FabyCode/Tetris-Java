package com.tetris.model;

import javafx.scene.paint.Color;

/**
 * Representa la pieza tipo T del juego Tetris.
 * <p>
 * Esta pieza está compuesta por cuatro bloques organizados
 * en una estructura similar a la letra "T". Su diseño la hace
 * especialmente versátil para adaptarse a diferentes espacios
 * dentro del tablero.
 * </p>
 * <p>
 * Hereda el comportamiento base definido en la clase
 * {@code Tetromino}, incluyendo movimiento, rotación
 * y gestión de posición.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class TShape extends Tetromino {

    /**
     * Inicializa la forma y el color de la pieza tipo T.
     * <p>
     * La pieza presenta una fila horizontal de tres bloques
     * con una extensión central en la parte inferior, y utiliza
     * el color púrpura para su representación visual.
     * </p>
     */
    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,1,1},
            {0,1,0}
        };

        setColor(Color.PURPLE);
    }
}