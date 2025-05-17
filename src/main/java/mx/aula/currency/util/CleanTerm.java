package mx.aula.currency.util;

import java.io.IOException;

public class CleanTerm {

    private static final String WINDOWS_OS = "Windows";
    private static final String CMD_COMMAND = "cmd";
    private static final String CLS_COMMAND = "cls";
    private static final String CLEAR_COMMAND = "clear";

    /**
     * Limpia la consola dependiendo del sistema operativo.
     * Si el sistema es Windows, se ejecuta el comando 'cls', y si es
     * Unix/Linux/macOS,
     * se ejecuta el comando 'clear'.
     * 
     * En caso de que ocurra algún error al intentar limpiar la consola, se registra
     * el mensaje de error correspondiente.
     */
    public void start() {
        try {
            String sistemaOperativo = System.getProperty("os.name");
            // Para Windows
            if (sistemaOperativo.contains(WINDOWS_OS)) {
                new ProcessBuilder(CMD_COMMAND, "/c", CLS_COMMAND).inheritIO().start().waitFor();
            } else {
                // Para Unix/Linux/macOS
                new ProcessBuilder(CLEAR_COMMAND).inheritIO().start().waitFor();
            }
        } catch (IOException e) {
            System.out.println("[WARNING] . "+e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaurar estado de interrupción
            System.out.println("[SERVER] : Proceso Interrumpido");
        }
    }

}
