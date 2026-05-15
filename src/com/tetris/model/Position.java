package com.tetris.model;

/**
 * Representa una posición dentro del tablero del juego Tetris.
 * <p>
 * Esta clase encapsula las coordenadas de una pieza o bloque
 * mediante una fila y una columna, permitiendo ubicar elementos
 * dentro de la cuadrícula del juego.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class Position {

    /**
     * Fila actual dentro del tablero.
     */
    private int row;

    /**
     * Columna actual dentro del tablero.
     */
    private int col;

    /**
     * Construye una nueva posición con coordenadas específicas.
     *
     * @param row fila inicial.
     * @param col columna inicial.
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Obtiene la fila actual.
     *
     * @return número de fila.
     */
    public int getRow() {
        return row;
    }

    /**
     * Obtiene la columna actual.
     *
     * @return número de columna.
     */
    public int getCol() {
        return col;
    }

    /**
     * Actualiza la fila de la posición.
     *
     * @param row nueva fila.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Actualiza la columna de la posición.
     *
     * @param col nueva columna.
     */
    public void setCol(int col) {
        this.col = col;
    }
}