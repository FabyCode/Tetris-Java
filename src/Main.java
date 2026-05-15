import com.tetris.ui.TetrisApp;

import javafx.application.Application;

/**
 * Clase principal del proyecto Tetris.
 * <p>
 * Esta clase actúa como punto de entrada de la aplicación y es la encargada
 * de iniciar el entorno gráfico de JavaFX, lanzando la clase principal
 * de la interfaz del juego.
 * </p>
 *
 * @author Diego
 * @author Fabrizio
 * @version 1.0
 */
public class Main {

    /**
     * Método principal de ejecución del programa.
     * <p>
     * Inicializa la aplicación JavaFX y delega el control a la clase
     * {@code TetrisApp}, encargada de gestionar la interfaz gráfica
     * y el flujo principal del juego.
     * </p>
     *
     * @param args argumentos enviados por línea de comandos al iniciar
     *             la aplicación.
     */
    public static void main(String[] args) {
        Application.launch(
                TetrisApp.class,
                args
        );
    }
}