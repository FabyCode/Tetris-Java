package com.tetris.util;

import java.util.Random;
import com.tetris.model.*;

/**
 * Fábrica encargada de generar piezas aleatorias del juego Tetris.
 * <p>
 * Esta clase implementa el patrón de diseño Factory, permitiendo
 * centralizar la creación de objetos de tipo {@code Tetromino}.
 * Cada invocación genera una pieza aleatoria entre los siete
 * tetrominós clásicos del juego.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class TetrominoFactory {

    /**
     * Generador de números aleatorios utilizado para seleccionar
     * el tipo de pieza que será creada.
     */
    private static final Random random = new Random();

    /**
     * Genera una pieza aleatoria del juego Tetris.
     * <p>
     * Selecciona una de las siete formas disponibles:
     * I, Z, S, J, L, T u O.
     * </p>
     *
     * @return una nueva instancia de un tetrominó generado aleatoriamente.
     */
    public static Tetromino createRandom() {

        return switch(random.nextInt(7)) {
            case 0 -> new IShape();
            case 1 -> new ZShape();
            case 2 -> new SShape();
            case 3 -> new JShape();
            case 4 -> new LShape();
            case 5 -> new TShape();
            default -> new OShape();
        };
    }
}