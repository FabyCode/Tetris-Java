package com.tetris.model;
import javafx.scene.paint.Color;

public abstract class Tetromino {

    protected int[][] shape;
    protected Position position;
    private Color color;

    public Tetromino() {
        initializeShape();
        initializeSpawnPosition();
    }

    protected abstract void initializeShape();

    private void initializeSpawnPosition() {
        int boardWidth = 10;
        int pieceWidth = shape[0].length;
        int centeredCol = (boardWidth - pieceWidth) / 2;
        this.position = new Position(0, centeredCol);
    }

    public int[][] getShape() {
        return shape;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    protected void setColor(Color color) {
        this.color = color;
    }

    public void moveLeft() {
        position.setCol(position.getCol() - 1);
    }

    public void moveRight() {
        position.setCol(position.getCol() + 1);
    }

    public void moveDown() {
        position.setRow(position.getRow() + 1);
    }

    public void rotate() {
        shape = rotateMatrix(shape);
    }

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

    public void reset() {
        initializeShape();
        position = new Position(0, 3);
    }
}