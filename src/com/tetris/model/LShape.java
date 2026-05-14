package com.tetris.model;

import javafx.scene.paint.Color;

public class LShape extends Tetromino {

    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,0},
            {1,0},
            {1,1}
        };
        setColor(Color.ORANGE);
    }
}