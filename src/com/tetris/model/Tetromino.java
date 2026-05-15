package com.tetris.model;

import javafx.scene.paint.Color;

/**
 * Clase abstracta que representa un tetrominó dentro del juego Tetris.
 * <p>
 * Un tetrominó es una pieza compuesta por bloques organizados en una
 * matriz bidimensional. Esta clase define el comportamiento común de
 * todas las piezas del juego, como movimiento, rotación, posición inicial
 * y manejo de color.
 * </p>
 * <p>
 * Las clases hijas deben implementar la inicialización de su forma
 * específica mediante el método {@code initializeShape()}.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public abstract class Tetromino {

    /**
     * Matriz que representa la forma de la pieza.
     * Los valores indican la presencia o ausencia de bloques.
     */
    protected int[][] shape;

    /**
     * Posición actual de la pieza dentro del tablero.
     */
    protected Position position;

    /**
     * Color asignado a la pieza.
     */
    private Color color;

    /**
     * Constructor de la pieza.
     * <p>
     * Inicializa la forma específica del tetrominó y establece
     * su posición inicial en la parte superior del tablero.
     * </p>
     */
    public Tetromino() {
        initializeShape();
        initializeSpawnPosition();
    }

    /**
     * Inicializa la forma de la pieza.
     * <p>
     * Este método debe ser implementado por cada subtipo de tetrominó
     * para definir su estructura específica.
     * </p>
     */
    protected abstract void initializeShape();

    /**
     * Calcula y establece la posición inicial de aparición de la pieza.
     * <p>
     * La pieza se genera centrada horizontalmente en la parte superior
     * del tablero.
     * </p>
     */
    private void initializeSpawnPosition() {
        int boardWidth = 10;
        int pieceWidth = shape[0].length;
        int centeredCol = (boardWidth - pieceWidth) / 2;
        this.position = new Position(0, centeredCol);
    }

    /**
     * Obtiene la matriz que representa la forma actual de la pieza.
     *
     * @return matriz bidimensional con la forma del tetrominó.
     */
    public int[][] getShape() {
        return shape;
    }

    /**
     * Obtiene la posición actual de la pieza en el tablero.
     *
     * @return objeto con la fila y columna actuales.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Obtiene el color asignado a la pieza.
     *
     * @return color del tetrominó.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Asigna un color a la pieza.
     *
     * @param color color que se asignará al tetrominó.
     */
    protected void setColor(Color color) {
        this.color = color;
    }

    /**
     * Desplaza la pieza una columna hacia la izquierda.
     */
    public void moveLeft() {
        position.setCol(position.getCol() - 1);
    }

    /**
     * Desplaza la pieza una columna hacia la derecha.
     */
    public void moveRight() {
        position.setCol(position.getCol() + 1);
    }

    /**
     * Desplaza la pieza una fila hacia abajo.
     */
    public void moveDown() {
        position.setRow(position.getRow() + 1);
    }

    /**
     * Rota la pieza 90 grados en sentido horario.
     */
    public void rotate() {
        shape = rotateMatrix(shape);
    }

    /**
     * Genera una nueva matriz rotada 90 grados en sentido horario.
     *
     * @param matrix matriz original de la pieza.
     * @return nueva matriz rotada.
     */
    private int[][] rotateMatrix(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] rotated = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = matrix[i][j];
            }
        }

        return rotated;
    }

    /**
     * Reinicia la pieza a su estado original.
     * <p>
     * Restablece la forma inicial y reposiciona la pieza
     * en la parte superior del tablero.
     * </p>
     */
    public void reset() {
        initializeShape();
        position = new Position(0, 3);
    }
}