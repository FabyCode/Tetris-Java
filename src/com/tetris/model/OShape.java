package com.tetris.model;

public class OShape extends Tetromino {

    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,1},
            {1,1}
        };
    }
}