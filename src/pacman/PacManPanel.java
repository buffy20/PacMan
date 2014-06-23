package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static pacman.PacMan.DOWN;
import static pacman.PacMan.LEFT;
import static pacman.PacMan.RIGHT;
import static pacman.PacMan.UP;

public class PacManPanel extends javax.swing.JPanel {
    
    //Declaración de atributos privados
    private PacMan pacman;
    private final int CELLSIZE = 14;
    private Image image;
    private int width;
    private int height;

    public PacManPanel() {
        initComponents();
        //Ajustar el panel con las medidas del juego
        Dimension dimension = new Dimension(40 * CELLSIZE, 32 * CELLSIZE);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
    }

    public void setPacman(PacMan pacman) {
        this.pacman = pacman;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (pacman != null) {
            for (int f = 0; f < pacman.getRowSize(); f++) {
                for (int c = 0; c < pacman.getColumnSize(); c++) {
                    char contenido = pacman.getScreen()[f][c];
                    //Pintar las imágenes o iconos según su valor en la matriz
                    switch (contenido) {
                        case PacMan.EMPTY:
                            break;
                        //Pintar las bolitas
                        case PacMan.BALL:
                            try {
                                image = ImageIO.read(this.getClass().getResource("/resources/ball.png"));
                                width = image.getWidth(null);
                                height = image.getHeight(null);
                                g.drawImage(image, c * CELLSIZE, f * CELLSIZE, width, height, null);
                            } catch (IOException ex) {
                                Logger.getLogger(PacManPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        //Pintar los fantastmas
                        case PacMan.GHOST:
                            try {
                                image = ImageIO.read(this.getClass().getResource("/resources/ghost.png"));
                                width = image.getWidth(null);
                                height = image.getHeight(null);
                                g.drawImage(image, c * CELLSIZE, f * CELLSIZE, width, height, null);
                            } catch (IOException ex) {
                                Logger.getLogger(PacManPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case PacMan.GHOST1:
                            try {
                                image = ImageIO.read(this.getClass().getResource("/resources/ghost1.png"));
                                width = image.getWidth(null);
                                height = image.getHeight(null);
                                g.drawImage(image, c * CELLSIZE, f * CELLSIZE, width, height, null);
                            } catch (IOException ex) {
                                Logger.getLogger(PacManPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case PacMan.GHOST2:
                            try {
                                image = ImageIO.read(this.getClass().getResource("/resources/ghost2.png"));
                                width = image.getWidth(null);
                                height = image.getHeight(null);
                                g.drawImage(image, c * CELLSIZE, f * CELLSIZE, width, height, null);
                            } catch (IOException ex) {
                                Logger.getLogger(PacManPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case PacMan.GHOST3:
                            try {
                                image = ImageIO.read(this.getClass().getResource("/resources/ghost3.png"));
                                width = image.getWidth(null);
                                height = image.getHeight(null);
                                g.drawImage(image, c * CELLSIZE, f * CELLSIZE, width, height, null);
                            } catch (IOException ex) {
                                Logger.getLogger(PacManPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        //Dependiendo de la dirección del movimiento del Pacman, la imagen irá girada en dicha posición
                        case PacMan.PLAYER:
                            try {
                                image = ImageIO.read(this.getClass().getResource("/resources/pacman.png"));
                                width = image.getWidth(null);
                                height = image.getHeight(null);
                                switch (pacman.getDirection()) {
                                    case RIGHT:
                                        image = ImageIO.read(this.getClass().getResource("/resources/pacman.png"));
                                        g.drawImage(image, c * CELLSIZE, f * CELLSIZE, width, height, null);
                                        break;
                                    case LEFT:
                                        image = ImageIO.read(this.getClass().getResource("/resources/pacmanLeft.png"));
                                        g.drawImage(image, c * CELLSIZE, f * CELLSIZE, width, height, null);
                                        break;
                                    case UP:
                                        image = ImageIO.read(this.getClass().getResource("/resources/pacmanUp.png"));
                                        g.drawImage(image, c * CELLSIZE, f * CELLSIZE, width, height, null);
                                        break;
                                    case DOWN:
                                        image = ImageIO.read(this.getClass().getResource("/resources/pacmanDown.png"));
                                        g.drawImage(image, c * CELLSIZE, f * CELLSIZE, width, height, null);
                                        break;
                                }

                            } catch (IOException ex) {
                                Logger.getLogger(PacManPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        //Pintar los muros
                        case PacMan.WALL:
                            g.setColor(Color.PINK);
                            g.fillRect(c * CELLSIZE, f * CELLSIZE, CELLSIZE, CELLSIZE);
                            break;
                        //Pintar la fruta
                        case PacMan.PRIZE:
                            try {
                                image = ImageIO.read(this.getClass().getResource("/resources/fruta.png"));
                                width = image.getWidth(null);
                                height = image.getHeight(null);
                                g.drawImage(image, c * CELLSIZE, f * CELLSIZE, width, height, null);
                            } catch (IOException ex) {
                                Logger.getLogger(PacManPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                    }
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 901, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
