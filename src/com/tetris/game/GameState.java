package com.tetris.game;

/**
 * Define los posibles estados de ejecución del juego Tetris.
 * <p>
 * Este enumerador permite controlar el flujo general
 * de la partida, indicando si el juego continúa activo
 * o si ha finalizado.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public enum GameState {

    /**
     * Indica que la partida está en curso
     * y el jugador puede seguir interactuando.
     */
    PLAYING,

    /**
     * Indica que la partida ha terminado,
     * generalmente porque ya no es posible
     * generar nuevas piezas en el tablero.
     */
    GAME_OVER
}