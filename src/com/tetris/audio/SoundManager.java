package com.tetris.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {

    private MediaPlayer backgroundMusic;

    public void playBackgroundMusic() {

        try {

            String musicPath =
                    getClass()
                            .getClassLoader()
                            .getResource("/resources/com/tetris/audio/theme.mp3")
                            .toExternalForm();

            Media music =
                    new Media(musicPath);

            backgroundMusic =
                    new MediaPlayer(music);

            // Repetir indefinidamente
            backgroundMusic.setCycleCount(
                    MediaPlayer.INDEFINITE
            );

            // Volumen (0.0 a 1.0)
            backgroundMusic.setVolume(0.35);

            backgroundMusic.play();

        }
        catch(Exception e) {

            System.out.println(
                    "Error cargando música: "
                    + e.getMessage()
            );
        }
    }

    public void stopBackgroundMusic() {

        if(backgroundMusic != null) {

            backgroundMusic.stop();
        }
    }
}