package com.tetris.game;

import com.tetris.model.Board;
import com.tetris.model.Tetromino;
import com.tetris.util.TetrominoFactory;

/**
 * Gestiona la lógica principal del juego Tetris.
 * <p>
 * Esta clase coordina todos los componentes centrales del juego,
 * incluyendo el tablero, las piezas activas, la siguiente pieza,
 * la pieza almacenada, el sistema de puntuación y el estado general
 * de la partida.
 * </p>
 * <p>
 * También controla las acciones del jugador, como mover piezas,
 * rotarlas, almacenarlas y generar nuevas piezas cuando una
 * pieza actual se fija en el tablero.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class Game {

    /**
     * Tablero principal donde se desarrolla la partida.
     */
    private Board board;

    /**
     * Pieza que el jugador está controlando actualmente.
     */
    private Tetromino currentPiece;

    /**
     * Próxima pieza que aparecerá en el tablero.
     */
    private Tetromino nextPiece;

    /**
     * Pieza almacenada por el jugador mediante la mecánica hold.
     */
    private Tetromino heldPiece;

    /**
     * Indica si el jugador puede usar la mecánica hold
     * durante el turno actual.
     */
    private boolean canHold;

    /**
     * Gestiona la puntuación, líneas eliminadas y nivel.
     */
    private ScoreManager scoreManager;

    /**
     * Estado actual de la partida.
     */
    private GameState state;

    /**
     * Inicializa una nueva partida de Tetris.
     * <p>
     * Crea el tablero, genera las piezas iniciales,
     * configura el sistema de puntuación y establece
     * el estado inicial del juego.
     * </p>
     */
    public Game() {
        board = new Board();
        currentPiece = TetrominoFactory.createRandom();
        nextPiece = TetrominoFactory.createRandom();

        heldPiece = null;
        canHold = true;

        scoreManager = new ScoreManager();
        state = GameState.PLAYING;
    }

    /**
     * Intenta mover la pieza actual una columna hacia la izquierda.
     * <p>
     * El movimiento solo se realiza si no existe colisión
     * ni se exceden los límites del tablero.
     * </p>
     */
    public void moveLeft() {
        int newCol = currentPiece.getPosition().getCol() - 1;
        int row = currentPiece.getPosition().getRow();

        if (board.canMove(
                currentPiece,
                row,
                newCol)) {
            currentPiece.moveLeft();
        }
    }

    /**
     * Intenta mover la pieza actual una columna hacia la derecha.
     * <p>
     * El movimiento solo se realiza si la nueva posición es válida.
     * </p>
     */
    public void moveRight() {
        int newCol = currentPiece.getPosition().getCol() + 1;
        int row = currentPiece.getPosition().getRow();

        if (board.canMove(
                currentPiece,
                row,
                newCol)) {
            currentPiece.moveRight();
        }
    }

    /**
     * Intenta mover la pieza actual una fila hacia abajo.
     * <p>
     * Si el movimiento no es posible, la pieza se fija
     * permanentemente en el tablero, se eliminan líneas completas
     * si existen y se genera una nueva pieza.
     * </p>
     */
    public void moveDown() {
        int newRow = currentPiece.getPosition().getRow() + 1;
        int col = currentPiece.getPosition().getCol();

        if (board.canMove(
                currentPiece,
                newRow,
                col)) {
            currentPiece.moveDown();

        } else {
            board.placePiece(currentPiece);

            int cleared = board.clearFullLines();

            scoreManager.update(cleared);

            spawnNewPiece();
        }
    }

    /**
     * Intenta rotar la pieza actual 90 grados.
     * <p>
     * Si la rotación provoca colisión, se aplican intentos
     * de ajuste lateral (wall kick). Si ninguna posición es válida,
     * la rotación se revierte.
     * </p>
     */
    public void rotatePiece() {
        currentPiece.rotate();

        int row = currentPiece.getPosition().getRow();
        int originalCol = currentPiece.getPosition().getCol();

        // Rotación normal
        if (board.canMove(currentPiece, row, originalCol)) {
            return;
        }

        // Intentos de wall kick
        int[] offsets = { 1, -1, 2, -2 };

        for (int offset : offsets) {

            int testCol = originalCol + offset;

            if (board.canMove(
                    currentPiece,
                    row,
                    testCol)) {

                currentPiece
                        .getPosition()
                        .setCol(testCol);

                return;
            }
        }

        // Revertir rotación si falla
        undoRotation();
    }

    /**
     * Revierte la última rotación realizada.
     * <p>
     * La reversión se logra aplicando tres rotaciones adicionales,
     * equivalentes a una rotación inversa.
     * </p>
     */
    private void undoRotation() {

        for (int i = 0; i < 3; i++) {
            currentPiece.rotate();
        }
    }

    /**
     * Inicia el ciclo principal del juego.
     * <p>
     * Mientras la partida esté activa, se renderiza
     * el estado del tablero y se muestran estadísticas
     * del jugador.
     * </p>
     */
    public void start() {

        while (state == GameState.PLAYING) {

            clearConsole();

            board.render(currentPiece);

            System.out.println();
            System.out.println(
                    "Score: "
                            + scoreManager.getScore()
            );

            System.out.println(
                    "Lines: "
                            + scoreManager.getTotalLines()
            );

            System.out.println(
                    "Level: "
                            + scoreManager.getLevel()
            );
        }

        System.out.println();
        System.out.println("GAME OVER");
    }

    /**
     * Genera una nueva pieza para continuar la partida.
     * <p>
     * La pieza siguiente pasa a ser la pieza actual,
     * se genera una nueva pieza futura y se restablece
     * la disponibilidad del sistema hold.
     * </p>
     * <p>
     * Si la nueva pieza no puede aparecer en el tablero,
     * la partida termina.
     * </p>
     */
    private void spawnNewPiece() {

        currentPiece = nextPiece;

        currentPiece.reset();

        nextPiece = TetrominoFactory.createRandom();

        canHold = true;

        if (!board.canMove(
                currentPiece,
                currentPiece.getPosition().getRow(),
                currentPiece.getPosition().getCol())) {

            state = GameState.GAME_OVER;
        }
    }

    /**
     * Permite almacenar o intercambiar la pieza actual
     * utilizando la mecánica hold.
     * <p>
     * Esta acción solo puede realizarse una vez por turno
     * hasta que una nueva pieza sea colocada.
     * </p>
     */
    public void holdPiece() {

        if (!canHold) {
            return;
        }

        canHold = false;

        if (heldPiece == null) {

            heldPiece = currentPiece;

            currentPiece = nextPiece;

            currentPiece.reset();

            nextPiece = TetrominoFactory.createRandom();

        } else {

            Tetromino temp = currentPiece;

            currentPiece = heldPiece;

            heldPiece = temp;

            currentPiece.reset();
        }
    }

    /**
     * Verifica si la partida ha terminado.
     *
     * @return {@code true} si el juego terminó,
     *         {@code false} en caso contrario.
     */
    public boolean isGameOver() {
        return state == GameState.GAME_OVER;
    }

    /**
     * Limpia visualmente la consola.
     * <p>
     * Utilizado para simular actualización de pantalla
     * durante la ejecución en consola.
     * </p>
     */
    private void clearConsole() {

        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    /**
     * Obtiene la pieza actualmente activa.
     *
     * @return pieza actual del jugador.
     */
    public Tetromino getCurrentPiece() {
        return currentPiece;
    }

    /**
     * Obtiene el tablero del juego.
     *
     * @return tablero principal.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Obtiene el nivel actual del jugador.
     *
     * @return nivel actual.
     */
    public int getLevel() {
        return scoreManager.getLevel();
    }

    /**
     * Calcula la velocidad de caída automática de las piezas.
     * <p>
     * A mayor nivel, menor tiempo entre caídas.
     * </p>
     *
     * @return velocidad en milisegundos.
     */
    public int getDropSpeed() {

        int speed =
                900 - (getLevel() - 1) * 100;

        return Math.max(speed, 150);
    }

    /**
     * Obtiene la puntuación actual.
     *
     * @return puntuación acumulada.
     */
    public int getScore() {
        return scoreManager.getScore();
    }

    /**
     * Obtiene la cantidad total de líneas eliminadas.
     *
     * @return total de líneas.
     */
    public int getLines() {
        return scoreManager.getTotalLines();
    }

    /**
     * Obtiene la siguiente pieza que aparecerá.
     *
     * @return siguiente tetrominó.
     */
    public Tetromino getNextPiece() {
        return nextPiece;
    }

    /**
     * Obtiene la pieza almacenada mediante hold.
     *
     * @return pieza almacenada o {@code null}
     *         si no existe ninguna.
     */
    public Tetromino getHeldPiece() {
        return heldPiece;
    }
}