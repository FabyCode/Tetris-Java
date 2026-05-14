package com.tetris.util;

import java.util.Random;
import com.tetris.model.*;

public class TetrominoFactory {

    private static final Random random = new Random();

    public static Tetromino createRandom() {

        return switch(random.nextInt(7)) {

            case 0 -> new IShape();
            case 1 -> new ZShape();
            case 2 -> new SShape();
            case 3 -> new JShape();
            case 4 -> new LShape();
            case 5 -> new TShape();
            default -> new OShape();
        };
    }
}