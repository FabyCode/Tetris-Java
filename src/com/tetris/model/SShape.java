package com.tetris.model;

import javafx.scene.paint.Color;

public class SShape extends Tetromino {

    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {0,1,1},
            {1,1,0}
        };

        setColor(Color.GREEN);
    }
}