package com.tetris.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.tetris.model.Board;
import com.tetris.model.Tetromino;
import com.tetris.util.TetrominoFactory;

public class Game {

    private Board board;
    private Tetromino currentPiece;
    //private Scanner scanner;
    //private Map<Character, Runnable> controls;

    private ScoreManager scoreManager;
    private GameState state;

    public Game() {
        board = new Board();
        currentPiece = TetrominoFactory.createRandom();
        //scanner = new Scanner(System.in);
        //controls = new HashMap<>();
        scoreManager = new ScoreManager();
        state = GameState.PLAYING;

        //initializeControls();
    }

    /*private void initializeControls() {
        controls.put('a', () -> moveLeft());
        controls.put('d', () -> moveRight());
        controls.put('s', () -> moveDown());
        controls.put('w', () -> rotatePiece());

        controls.put('q', () -> state = GameState.GAME_OVER);
    }*/

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

    public void rotatePiece() {
        currentPiece.rotate();
        int row = currentPiece.getPosition().getRow();
        int originalCol = currentPiece.getPosition().getCol();

        // 1. Rotación normal
        if (board.canMove(currentPiece, row, originalCol)) {
            return;
        }

        // 2. Intentos de wall kick
        int[] offsets = { 1, -1, 2, -2 };

        for (int offset : offsets) {
            int testCol = originalCol + offset;
            if (board.canMove(currentPiece, row, testCol)) {
                currentPiece.getPosition().setCol(testCol);
                return;
            }
        }

        // 3. Si nada funciona, revertir rotación
        undoRotation();
    }

    private void undoRotation() {
        for (int i = 0; i < 3; i++) {
            currentPiece.rotate();
        }
    }

    public void start() {

        while (state == GameState.PLAYING) {
            clearConsole();
            board.render(currentPiece);

            /*
             * System.out.println();
             * System.out.println("A=Izq D=Der S=Abajo W=Rotar Q=Salir");
             */

            
            System.out.println();
            System.out.println("Score: " + scoreManager.getScore());
            System.out.println("Lines: " + scoreManager.getTotalLines());
            System.out.println("Level: " + scoreManager.getLevel());

            //char input = scanner.next().toLowerCase().charAt(0);
            //executeInput(input);
        }

        System.out.println();
        System.out.println("GAME OVER");
    }

    private void spawnNewPiece() {
        currentPiece = TetrominoFactory.createRandom();
        int row = currentPiece.getPosition().getRow();
        int col = currentPiece.getPosition().getCol();
        if (!board.canMove(
                currentPiece,
                row,
                col)) {

            state = GameState.GAME_OVER;
        }
    }

    /*private void executeInput(char input) {

        Runnable action = controls.get(input);

        if (action != null) {
            action.run();
        }
    }*/

    public boolean isGameOver() {
        return state == GameState.GAME_OVER;
    }

    private void clearConsole() {

        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public Tetromino getCurrentPiece() {
        return currentPiece;
    }

    public Board getBoard() {
        return board;
    }

    public int getLevel() {
        return scoreManager.getLevel();
    }

    public int getDropSpeed() {
        int speed = 900 - (getLevel() - 1) * 100;
        return Math.max(speed, 150);
    }
}