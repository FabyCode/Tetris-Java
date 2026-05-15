package com.tetris.ui;

import com.tetris.game.Game;

import javafx.animation.KeyFrame;
import com.tetris.audio.SoundManager;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Clase principal de la interfaz gráfica del juego Tetris.
 * <p>
 * Esta clase extiende {@code Application} de JavaFX y se encarga
 * de inicializar, configurar y ejecutar toda la interfaz visual
 * del juego.
 * </p>
 * <p>
 * Sus responsabilidades incluyen:
 * </p>
 * <ul>
 *   <li>Construcción de la ventana principal.</li>
 *   <li>Configuración del tablero y panel lateral.</li>
 *   <li>Gestión de entradas del teclado.</li>
 *   <li>Actualización visual del juego.</li>
 *   <li>Ejecución del ciclo automático de caída de piezas.</li>
 *   <li>Integración del sistema de audio.</li>
 * </ul>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class TetrisApp extends Application {

    /**
     * Tamaño en píxeles de cada celda del tablero.
     */
    private static final int CELL_SIZE = 35;

    /**
     * Número de columnas del tablero.
     */
    private static final int BOARD_COLS = 10;

    /**
     * Número de filas del tablero.
     */
    private static final int BOARD_ROWS = 20;

    /**
     * Inicializa y ejecuta la interfaz gráfica del juego.
     * <p>
     * Este método crea todos los componentes visuales,
     * configura el sistema de entrada por teclado,
     * inicia el bucle principal del juego y muestra
     * la ventana principal.
     * </p>
     *
     * @param stage ventana principal proporcionada por JavaFX.
     */
    @Override
    public void start(Stage stage) {

        Game game = new Game();

        SoundManager soundManager = new SoundManager();

        GameRenderer renderer = new GameRenderer();

        PreviewRenderer previewRenderer =
                new PreviewRenderer();

        // ===== TABLERO =====
        Pane gameLayer = new Pane();

        gameLayer.setPrefSize(
                BOARD_COLS * CELL_SIZE,
                BOARD_ROWS * CELL_SIZE);

        gameLayer.setStyle(
                "-fx-background-color: black;");

        // ===== HUD =====
        Label scoreLabel = new Label();

        scoreLabel
                .getStyleClass()
                .add("stat-value");

        Pane holdPreview = new Pane();

        holdPreview.setPrefSize(
                120,
                120);

        holdPreview
                .getStyleClass()
                .add("preview-box");

        Label holdLabel =
                new Label("HOLD");

        holdLabel
                .getStyleClass()
                .add("panel-title");

        Label nextLabel =
                new Label("NEXT");

        nextLabel
                .getStyleClass()
                .add("panel-title");

        Pane nextPreview = new Pane();

        nextPreview.setPrefSize(
                120,
                120);

        nextPreview
                .getStyleClass()
                .add("preview-box");

        VBox hudPanel = new VBox(20);

        hudPanel.setPadding(
                new Insets(20));

        hudPanel.setAlignment(
                Pos.TOP_CENTER);

        hudPanel.setPrefWidth(220);

        hudPanel
                .getStyleClass()
                .add("side-panel");

        hudPanel.getChildren().addAll(
                holdLabel,
                holdPreview,
                nextLabel,
                nextPreview,
                scoreLabel
        );

        // ===== GAME OVER =====
        Text gameOverText = new Text();

        gameOverText
                .getStyleClass()
                .add("game-over-text");

        gameOverText.setVisible(false);

        StackPane boardContainer =
                new StackPane(
                        gameLayer,
                        gameOverText);

        boardContainer
                .getStyleClass()
                .add("board-container");

        // ===== ROOT =====
        BorderPane root =
                new BorderPane();

        root.getStyleClass()
                .add("root");

        root.setCenter(
                boardContainer);

        root.setLeft(
                hudPanel);

        Scene scene =
                new Scene(
                        root,
                        600,
                        760);

        scene.getStylesheets().add(
                getClass()
                        .getResource(
                                "/resources/com/tetris/styles/tetris.css")
                        .toExternalForm());

        // ===== PRIMER RENDER =====
        renderer.render(
                gameLayer,
                game);

        previewRenderer.render(
                holdPreview,
                game.getHeldPiece());

        previewRenderer.render(
                nextPreview,
                game.getNextPiece());

        updateHud(
                scoreLabel,
                game);

        // ===== TECLADO =====
        scene.setOnKeyPressed(
                event -> {

                    if (game.isGameOver()) {

                        soundManager
                                .stopBackgroundMusic();

                        return;
                    }

                    switch (event.getCode()) {

                        case LEFT ->
                            game.moveLeft();

                        case RIGHT ->
                            game.moveRight();

                        case DOWN ->
                            game.moveDown();

                        case UP ->
                            game.rotatePiece();

                        case SPACE ->
                            game.holdPiece();

                        default -> {
                        }
                    }

                    renderer.render(
                            gameLayer,
                            game);

                    previewRenderer.render(
                            holdPreview,
                            game.getHeldPiece());

                    previewRenderer.render(
                            nextPreview,
                            game.getNextPiece());

                    updateHud(
                            scoreLabel,
                            game);
                });

        // ===== LOOP PRINCIPAL =====
        Timeline loop = new Timeline(

                new KeyFrame(

                        Duration.millis(
                                game.getDropSpeed()),

                        e -> {

                            if (game.isGameOver()) {

                                gameOverText.setText(
                                        "GAME OVER");

                                gameOverText.setVisible(
                                        true);

                                soundManager
                                        .stopBackgroundMusic();

                                return;
                            }

                            game.moveDown();

                            renderer.render(
                                    gameLayer,
                                    game);

                            previewRenderer.render(
                                    holdPreview,
                                    game.getHeldPiece());

                            previewRenderer.render(
                                    nextPreview,
                                    game.getNextPiece());

                            updateHud(
                                    scoreLabel,
                                    game);
                        }));

        loop.setCycleCount(
                Timeline.INDEFINITE);

        loop.play();

        stage.setTitle("Tetris");

        stage.setScene(scene);

        stage.show();

        soundManager.playBackgroundMusic();
    }

    /**
     * Actualiza la información visual del panel lateral.
     * <p>
     * Muestra la puntuación actual, cantidad de líneas
     * eliminadas y nivel del jugador.
     * </p>
     *
     * @param label etiqueta donde se mostrará la información.
     * @param game instancia actual del juego.
     */
    private void updateHud(
            Label label,
            Game game) {

        label.setText(

                "SCORE\n"
                        + game.getScore()

                        + "\n\nLINES\n"
                        + game.getLines()

                        + "\n\nLEVEL\n"
                        + game.getLevel());
    }
}