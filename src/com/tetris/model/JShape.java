package com.tetris.model;

import javafx.scene.paint.Color;

public class JShape extends Tetromino {

    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {0,1},
            {0,1},
            {1,1}
        };
        setColor(Color.BLUE);
    }
}