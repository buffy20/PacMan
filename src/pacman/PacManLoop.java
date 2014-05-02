package pacman;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PacManLoop implements Runnable {

    private PacMan pacman;
    private boolean running = false;
    private long pauseSpeed;
    private PacManPanel pacManPanel;

    public PacManLoop(PacMan pacman, long pauseSpeed, PacManPanel pacManPanel) {
        this.pacman = pacman;
        this.pauseSpeed = pauseSpeed;
        this.pacManPanel = pacManPanel;
    }

    /**
     * Permite cambiar el tiempo de pausa que se utiliza en el bucle
     * @param pauseSpeed 
     */
    public void setPauseSpeed(long pauseSpeed) {
        this.pauseSpeed = pauseSpeed;
    }
    
    /**
     * Ejecuta el método run como un hilo de ejecución paralelo
     */
    public void start() {
        running = true;
        //Crear un nuevo hilo de ejecución para esta clase
        Thread thread = new Thread(this);   
        //Llamada automática al método run
        thread.start();
    }
    
    /**
     * Permite finalizar la ejecución de este proceso
     */
    public void stop() {
        running = false;
    }
    
    /**
     * Este método es llamado de manera automática por el método start
     */
    @Override
    public void run() {
        while(running) {
            //Mover el fantasma
            pacman.moveGhost();
            //Pintar el juego
            pacManPanel.repaint();
            //Pausar el juego durante el tiempo establecido
            try {
                Thread.sleep(pauseSpeed);
            } catch (InterruptedException ex) {
                Logger.getLogger(PacManLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
