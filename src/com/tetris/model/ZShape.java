package com.tetris.model;

import javafx.scene.paint.Color;

/**
 * Representa la pieza tipo Z del juego Tetris.
 * <p>
 * Esta pieza está formada por cuatro bloques distribuidos
 * en una estructura en zigzag, similar a la letra "Z".
 * Su forma permite ocupar espacios irregulares y complementar
 * configuraciones específicas dentro del tablero.
 * </p>
 * <p>
 * Esta pieza es la versión espejo de la pieza tipo S y
 * hereda el comportamiento general definido en la clase
 * {@code Tetromino}, incluyendo movimiento, rotación
 * y control de posición.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class ZShape extends Tetromino {

    /**
     * Inicializa la forma y el color de la pieza tipo Z.
     * <p>
     * La pieza presenta una disposición escalonada de bloques
     * en sentido opuesto a la pieza tipo S y utiliza el color
     * rojo para su representación visual.
     * </p>
     */
    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,1,0},
            {0,1,1}
        };

        setColor(Color.RED);
    }
}