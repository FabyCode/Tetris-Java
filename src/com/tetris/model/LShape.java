package com.tetris.model;

public class LShape extends Tetromino {

    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,0},
            {1,0},
            {1,0},
            {1,1}
        };
    }
}