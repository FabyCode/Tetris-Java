package com.tetris.ui;

import javafx.scene.paint.Color;

public class ColorMapper {

    public static Color getColor(int pieceId) {
        return switch(pieceId) {
            case 1 -> Color.CYAN;
            case 2 -> Color.YELLOW;
            case 3 -> Color.PURPLE;
            case 4 -> Color.ORANGE;
            case 5 -> Color.BLUE;
            case 6 -> Color.GREEN;
            case 7 -> Color.RED;
            default -> Color.web("#111111");
        };
    }
}