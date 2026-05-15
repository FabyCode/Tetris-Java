package com.tetris.model;

/**
 * Representa el tablero principal del juego Tetris.
 * <p>
 * Esta clase administra el estado de la cuadrícula del juego, incluyendo
 * la colocación de piezas, validación de movimientos, detección y eliminación
 * de líneas completas, así como la representación visual del tablero
 * en consola para pruebas o depuración.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class Board {

    /**
     * Número total de filas del tablero.
     */
    private final int ROWS = 20;

    /**
     * Número total de columnas del tablero.
     */
    private final int COLS = 10;

    /**
     * Matriz que representa el estado actual del tablero.
     * <p>
     * Un valor de 0 indica una celda vacía y un valor de 1
     * indica una celda ocupada.
     * </p>
     */
    private int[][] grid;

    /**
     * Constructor del tablero.
     * <p>
     * Inicializa la matriz del tablero con todas sus posiciones vacías.
     * </p>
     */
    public Board() {
        grid = new int[ROWS][COLS];
    }

    /**
     * Renderiza una pieza temporalmente sobre el tablero sin modificar
     * su estado original.
     * <p>
     * Se utiliza principalmente para visualización o depuración.
     * </p>
     *
     * @param piece pieza que será representada sobre el tablero.
     */
    public void render(Tetromino piece) {
        int[][] temp = copyGrid();
        drawPiece(temp, piece);
        printGrid(temp);
    }

    /**
     * Crea una copia del estado actual del tablero.
     *
     * @return copia bidimensional de la cuadrícula actual.
     */
    private int[][] copyGrid() {

        int[][] copy = new int[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, COLS);
        }

        return copy;
    }

    /**
     * Dibuja una pieza sobre una cuadrícula temporal.
     *
     * @param temp cuadrícula temporal donde se dibujará la pieza.
     * @param piece pieza que será representada.
     */
    private void drawPiece(int[][] temp, Tetromino piece) {

        int[][] shape = piece.getShape();

        int startRow = piece.getPosition().getRow();
        int startCol = piece.getPosition().getCol();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    temp[startRow + i][startCol + j] = 1;
                }
            }
        }
    }

    /**
     * Verifica si una pieza puede moverse a una nueva posición.
     * <p>
     * Comprueba límites del tablero y posibles colisiones
     * con bloques ya colocados.
     * </p>
     *
     * @param piece pieza a evaluar.
     * @param newRow nueva fila propuesta.
     * @param newCol nueva columna propuesta.
     * @return {@code true} si el movimiento es válido,
     *         {@code false} en caso contrario.
     */
    public boolean canMove(Tetromino piece, int newRow, int newCol) {
        int[][] shape = piece.getShape();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 0)
                    continue;

                int boardRow = newRow + i;
                int boardCol = newCol + j;

                if (boardCol < 0 || boardCol >= COLS)
                    return false;

                if (boardRow >= ROWS)
                    return false;

                if (grid[boardRow][boardCol] == 1)
                    return false;
            }
        }

        return true;
    }

    /**
     * Coloca de manera permanente una pieza en el tablero.
     *
     * @param piece pieza que será fijada en la cuadrícula.
     */
    public void placePiece(Tetromino piece) {
        int[][] shape = piece.getShape();

        int startRow = piece.getPosition().getRow();
        int startCol = piece.getPosition().getCol();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    grid[startRow + i][startCol + j] = 1;
                }
            }
        }
    }

    /**
     * Verifica si una fila está completamente llena.
     *
     * @param row fila a evaluar.
     * @return {@code true} si la fila está completa,
     *         {@code false} en caso contrario.
     */
    private boolean isLineFull(int row) {
        for (int col = 0; col < COLS; col++) {
            if (grid[row][col] == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Elimina el contenido de una fila específica.
     *
     * @param row fila que será vaciada.
     */
    private void removeLine(int row) {
        for (int col = 0; col < COLS; col++) {
            grid[row][col] = 0;
        }
    }

    /**
     * Desplaza todas las filas superiores una posición hacia abajo
     * después de eliminar una línea.
     *
     * @param deletedRow fila eliminada que servirá como referencia.
     */
    private void shiftLinesDown(int deletedRow) {
        for (int row = deletedRow; row > 0; row--) {
            for (int col = 0; col < COLS; col++) {
                grid[row][col] = grid[row - 1][col];
            }
        }

        for (int col = 0; col < COLS; col++) {
            grid[0][col] = 0;
        }
    }

    /**
     * Busca y elimina todas las líneas completas del tablero.
     * <p>
     * Cada línea eliminada provoca que las filas superiores
     * desciendan una posición.
     * </p>
     *
     * @return cantidad total de líneas eliminadas.
     */
    public int clearFullLines() {
        int clearedLines = 0;

        for(int row = ROWS - 1; row >= 0; row--) {
            if(isLineFull(row)) {
                removeLine(row);
                shiftLinesDown(row);
                clearedLines++;
                row++;
            }
        }

        return clearedLines;
    }

    /**
     * Obtiene la cuadrícula actual del tablero.
     *
     * @return matriz que representa el estado del tablero.
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * Imprime una representación visual del tablero en consola.
     * <p>
     * Utiliza símbolos para representar espacios vacíos
     * y bloques ocupados.
     * </p>
     *
     * @param temp cuadrícula que será impresa.
     */
    private void printGrid(int[][] temp) {

        for (int[] row : temp) {

            for (int cell : row) {

                System.out.print(
                        cell == 0 ? "□ " : "■ ");
            }

            System.out.println();
        }
    }
}