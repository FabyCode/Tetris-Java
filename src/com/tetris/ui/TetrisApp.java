package com.tetris.ui;

import com.tetris.game.Game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TetrisApp extends Application {

    private static final int CELL_SIZE = 35;
    private static final int BOARD_COLS = 10;
    private static final int BOARD_ROWS = 20;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, BOARD_COLS * CELL_SIZE, BOARD_ROWS * CELL_SIZE);

        stage.setTitle("Tetris");
        stage.setScene(scene);

        // Lógica del juego
        Game game = new Game();

        // Render
        GameRenderer renderer = new GameRenderer();
        renderer.render(root, game);

        // Input de teclado
        scene.setOnKeyPressed(
                event -> {
                    if (game.isGameOver())
                        return;
                    
                    switch (event.getCode()) {
                        case LEFT -> game.moveLeft();
                        case RIGHT -> game.moveRight();
                        case DOWN -> game.moveDown();
                        case UP -> game.rotatePiece();
                    }

                    renderer.render(root, game);
                });

        Timeline gameLoop = new Timeline(

                new KeyFrame(

                        Duration.millis(
                                game.getDropSpeed()),

                        event -> {

                            if (game.isGameOver()) {
                                return;
                            }

                            game.moveDown();

                            renderer.render(
                                    root,
                                    game);
                        }));

        // Repetir infinitamente
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        gameLoop.play();
        stage.show();
    }
}