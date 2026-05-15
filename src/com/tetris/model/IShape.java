package com.tetris.model;

import javafx.scene.paint.Color;

/**
 * Representa la pieza tipo I del juego Tetris.
 * <p>
 * Esta pieza está formada por cuatro bloques alineados
 * horizontalmente. Es una de las piezas más largas del juego
 * y permite completar líneas de manera eficiente.
 * </p>
 * <p>
 * Hereda el comportamiento base de la clase {@code Tetromino},
 * incluyendo movimiento, rotación y posicionamiento.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class IShape extends Tetromino {

    /**
     * Inicializa la forma y el color de la pieza tipo I.
     * <p>
     * La pieza se compone de una fila de cuatro bloques y
     * utiliza el color cian para su representación visual.
     * </p>
     */
    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,1,1,1}
        };

        setColor(Color.CYAN);
    }
}