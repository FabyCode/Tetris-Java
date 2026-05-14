package com.tetris.model;

import javafx.scene.paint.Color;

public class ZShape extends Tetromino {

    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,1,0},
            {0,1,1}
        };

        setColor(Color.RED);
    }
}