package com.tetris.model;

import javafx.scene.paint.Color;

/**
 * Representa la pieza tipo O del juego Tetris.
 * <p>
 * Esta pieza está compuesta por cuatro bloques organizados
 * en forma de cuadrado. Debido a su simetría, es la única
 * pieza del juego cuya apariencia no cambia al rotar.
 * </p>
 * <p>
 * Hereda el comportamiento base definido en la clase
 * {@code Tetromino}, incluyendo movimiento y gestión
 * de posición dentro del tablero.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class OShape extends Tetromino {

    /**
     * Inicializa la forma y el color de la pieza tipo O.
     * <p>
     * La pieza tiene una estructura cuadrada de dos por dos
     * bloques y utiliza el color amarillo para su representación
     * visual.
     * </p>
     */
    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,1},
            {1,1}
        };

        setColor(Color.YELLOW);
    }
}