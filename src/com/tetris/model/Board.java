package com.tetris.model;

public class Board {

    private final int ROWS = 20;
    private final int COLS = 10;

    private int[][] grid;

    public Board() {
        grid = new int[ROWS][COLS];
    }

    public void render(Tetromino piece) {

        int[][] temp = copyGrid();

        drawPiece(temp, piece);

        printGrid(temp);
    }

    private int[][] copyGrid() {

        int[][] copy = new int[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, COLS);
        }

        return copy;
    }

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

    private boolean isLineFull(int row) {
        for (int col = 0; col < COLS; col++) {
            if (grid[row][col] == 0) {
                return false;
            }
        }

        return true;
    }

    private void removeLine(int row) {
        for (int col = 0; col < COLS; col++) {
            grid[row][col] = 0;
        }
    }

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