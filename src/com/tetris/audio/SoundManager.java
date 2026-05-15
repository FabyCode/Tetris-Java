package com.tetris.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Gestiona la reproducción de audio dentro del juego Tetris.
 * <p>
 * Esta clase es responsable de cargar, reproducir y detener
 * la música de fondo del juego utilizando las herramientas
 * multimedia proporcionadas por JavaFX.
 * </p>
 * <p>
 * Actualmente se encarga de reproducir el tema principal
 * del juego en bucle continuo durante la ejecución.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class SoundManager {

    /**
     * Reproductor multimedia utilizado para la música de fondo.
     */
    private MediaPlayer backgroundMusic;

    /**
     * Carga y reproduce la música de fondo del juego.
     * <p>
     * El archivo de audio se obtiene desde los recursos
     * del proyecto, se configura para reproducirse de forma
     * indefinida y se ajusta a un volumen moderado.
     * </p>
     * <p>
     * En caso de error durante la carga o reproducción,
     * se muestra un mensaje en consola para facilitar
     * la depuración.
     * </p>
     */
    public void playBackgroundMusic() {
        try {
            String musicPath =
                    getClass()
                            .getClassLoader()
                            .getResource("resources/com/tetris/audio/theme.wav")
                            .toExternalForm();

            Media music = new Media(musicPath);

            backgroundMusic = new MediaPlayer(music);

            // Repetir indefinidamente
            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);

            // Volumen (0.0 a 1.0)
            backgroundMusic.setVolume(0.35);
            backgroundMusic.play();
        }
        catch(Exception e) {
            System.out.println(getClass().getResource("/"));

            System.out.println(
                    "Error cargando música: "
                    + e.getMessage()
            );
        }
    }

    /**
     * Detiene la reproducción de la música de fondo.
     * <p>
     * Si existe una instancia activa del reproductor,
     * la reproducción se detiene de forma segura.
     * </p>
     */
    public void stopBackgroundMusic() {

        if(backgroundMusic != null) {

            backgroundMusic.stop();
        }
    }
}