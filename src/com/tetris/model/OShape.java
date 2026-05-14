package com.tetris.model;

import javafx.scene.paint.Color;

public class OShape extends Tetromino {

    @Override
    protected void initializeShape() {

        shape = new int[][]{
            {1,1},
            {1,1}
        };

        setColor(Color.YELLOW);
    }
}