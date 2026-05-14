package com.tetris.model;

public class ZShape extends Tetromino {

    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,1,0},
            {0,1,1}
        };
    }
}