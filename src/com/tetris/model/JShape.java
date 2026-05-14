package com.tetris.model;

public class JShape extends Tetromino {

    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {0,1},
            {0,1},
            {0,1},
            {1,1}
        };
    }
}