package com.tetris.game;

/**
 * Gestiona el sistema de puntuación, líneas completadas
 * y progresión de nivel dentro del juego Tetris.
 * <p>
 * Esta clase se encarga de actualizar la puntuación del jugador
 * según la cantidad de líneas eliminadas en una jugada,
 * así como calcular automáticamente el nivel actual
 * en función del progreso acumulado.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class ScoreManager {

    /**
     * Puntuación total acumulada por el jugador.
     */
    private int score;

    /**
     * Cantidad total de líneas eliminadas durante la partida.
     */
    private int totalLines;

    /**
     * Nivel actual del jugador.
     * <p>
     * El nivel aumenta cada diez líneas eliminadas.
     * </p>
     */
    private int level;

    /**
     * Inicializa el sistema de puntuación con valores base.
     * <p>
     * La puntuación comienza en cero, sin líneas eliminadas
     * y en el nivel inicial.
     * </p>
     */
    public ScoreManager() {
        score = 0;
        totalLines = 0;
        level = 1;
    }

    /**
     * Actualiza la puntuación, las líneas acumuladas
     * y el nivel del jugador.
     * <p>
     * Si no se eliminó ninguna línea, no se realiza
     * ninguna actualización.
     * </p>
     *
     * @param clearedLines cantidad de líneas eliminadas
     *                     en la jugada actual.
     */
    public void update(int clearedLines) {

        if(clearedLines <= 0)
            return;

        totalLines += clearedLines;

        score += calculatePoints(clearedLines);

        updateLevel();
    }

    /**
     * Calcula los puntos obtenidos según la cantidad
     * de líneas eliminadas en una sola jugada.
     * <p>
     * El sistema de puntuación sigue las reglas clásicas
     * de Tetris:
     * </p>
     * <ul>
     *   <li>1 línea: 100 puntos</li>
     *   <li>2 líneas: 300 puntos</li>
     *   <li>3 líneas: 500 puntos</li>
     *   <li>4 líneas: 800 puntos</li>
     * </ul>
     *
     * @param clearedLines cantidad de líneas eliminadas.
     * @return puntos obtenidos.
     */
    private int calculatePoints(int clearedLines) {

        return switch(clearedLines) {

            case 1 -> 100;
            case 2 -> 300;
            case 3 -> 500;
            case 4 -> 800;
            default -> 0;
        };
    }

    /**
     * Recalcula el nivel del jugador.
     * <p>
     * El nivel aumenta automáticamente cada diez
     * líneas eliminadas.
     * </p>
     */
    private void updateLevel() {
        level = (totalLines / 10) + 1;
    }

    /**
     * Obtiene la puntuación actual del jugador.
     *
     * @return puntuación acumulada.
     */
    public int getScore() {
        return score;
    }

    /**
     * Obtiene la cantidad total de líneas eliminadas.
     *
     * @return total de líneas completadas.
     */
    public int getTotalLines() {
        return totalLines;
    }

    /**
     * Obtiene el nivel actual del jugador.
     *
     * @return nivel actual.
     */
    public int getLevel() {
        return level;
    }
}