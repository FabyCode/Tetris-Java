package com.tetris.model;

public class IShape extends Tetromino {

    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,1,1,1}
        };
    }
}