package com.tetris.model;

import javafx.scene.paint.Color;

/**
 * Representa la pieza tipo S del juego Tetris.
 * <p>
 * Esta pieza está formada por cuatro bloques distribuidos
 * en una estructura en zigzag, similar a la letra "S".
 * Su forma es útil para encajar en espacios irregulares
 * y completar líneas dentro del tablero.
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
public class SShape extends Tetromino {

    /**
     * Inicializa la forma y el color de la pieza tipo S.
     * <p>
     * La pieza posee una disposición escalonada de bloques
     * y utiliza el color verde para su representación visual.
     * </p>
     */
    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {0,1,1},
            {1,1,0}
        };

        setColor(Color.GREEN);
    }
}