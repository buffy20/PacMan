package pacman;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PacMan {

    //Declaración de atributos privados
    private char[][] screen;
    private int yRow;
    private int xColumn;
    private ArrayList<Point> ghostList;
    private byte direction;
    private byte ghostDirection;
    private Random random = new Random();
    private Point playerPosition, newPlayerPosition;
    private int score = 0;
    BufferedReader bufferedReader;
    
    //Declaración de atributos públicos
    /*Iconos para representar los personajes */
    public static final char BALL = '.';
    public static final char GHOST = '&';
    public static final char GHOST1 = '@';
    public static final char GHOST2 = '$';
    public static final char GHOST3 = '=';
    public static final char PLAYER = 'C';
    public static final char WALL = '#';
    public static final char PRIZE = '*';
    public static final char EMPTY = ' ';

    /*Constantes para representar los posibles movimientos */
    public static final byte RIGHT = 0;
    public static final byte LEFT = 1;
    public static final byte UP = 2;
    public static final byte DOWN = 3;

    //Constructor
    public PacMan(String fileName) {
        ghostList = new ArrayList();
        bufferedReader = null;

        //Crear el lector del fichero
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String levelLine = bufferedReader.readLine();
            this.xColumn = levelLine.length();
            while (levelLine != null) {
                this.yRow++;
                levelLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException ex) {
            Logger.getLogger(PacMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        ghostList = new ArrayList();
        direction = RIGHT;
        screen = new char[yRow][xColumn];
        
        //Generar la fruta
        generatePrize();
    }
    
    public void paintScreen(){
       int row = 0;
        int column = 0;
        //Abrir el fichero para cargar la pantalla que contiene el juego
        try {
            bufferedReader = new BufferedReader(new FileReader("level.dat"));
            String levelLine = bufferedReader.readLine();
            while (levelLine != null) {
                column = 0;
                for (int i = 0; i < levelLine.length(); i++) {
                    screen[row][column] = levelLine.charAt(i);

                    if (screen[row][column] == PLAYER) {
                        ghostList.add(0, new Point(column, row));
                    } else if (screen[row][column] == PRIZE) {
                        ghostList.add(new Point(column, row));
                    }
                    column++;
                }
                levelLine = bufferedReader.readLine();
                row++;
            }
            bufferedReader.close();
        } catch (IOException ex) {
            Logger.getLogger(PacMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public char[][] getScreen() {
        return screen;
    }

    public int getRowSize() {
        return yRow;
    }

    public int getColumnSize() {
        return xColumn;
    }

    public void setDirection(byte direction) {
        this.direction = direction;
    }

    //Generar la fruta cuando se hayan conseguido 100 puntos
    private void generatePrize() {
        int row;
        int column;
        do {
            row = random.nextInt(yRow);
            column = random.nextInt(xColumn);
        } while (screen[row][column] == WALL || screen[row][column] == BALL);
        screen[row][column] = PRIZE;

    }

    @Override
    public String toString() {
        String message = "";
        for (int f = 0; f < yRow; f++) {
            for (int c = 0; c < xColumn; c++) {
                message += screen[f][c];
            }
            message += '\n';
        }
        return message;
    }

    //Mover los fantasmas
    public boolean moveGhost() {
        //Guardar posición actual del fantasma
        Point ghost = new Point(3,16);
        Point ghost1 = new Point(13,30);
        Point ghost2 = new Point(35,16);
        Point ghost3 = new Point(0,16);
        
        Point oldGhostPosition = ghostList.get(ghostList.size() - 1);
        Point newGhost1 = null;

        if (ghost1.y > yRow / 2 && ghost1.x > xColumn / 2) {
            ghostDirection = LEFT;
            newGhost1 = new Point(ghost1.x - 1, ghost1.y);
        } else {
            ghostDirection = RIGHT;
            newGhost1 = new Point(ghost1.x + 1, ghost1.y);
        }
        screen[newGhost1.y][newGhost1.x] = GHOST1;
        ghostList.add(ghostList.size(), newGhost1);
        return true;
    }

    //Mover el Pacman según indique el jugador
    public void move() {
        playerPosition = ghostList.get(0);
        newPlayerPosition = playerPosition;

        if (!this.isDead()) {
            switch (direction) {
                case RIGHT:
                    newPlayerPosition = new Point(playerPosition.x + 1, playerPosition.y);
                    break;
                case LEFT:
                    newPlayerPosition = new Point(playerPosition.x - 1, playerPosition.y);
                    break;
                case UP:
                    newPlayerPosition = new Point(playerPosition.x, playerPosition.y - 1);
                    break;
                case DOWN:
                    newPlayerPosition = new Point(playerPosition.x, playerPosition.y + 1);
                    break;
            }
            //Mostrar el pacman en la posición indicada según se vaya moviendo
            screen[newPlayerPosition.y][newPlayerPosition.x] = PLAYER;
            ghostList.add(0, newPlayerPosition);
        } else {
            JOptionPane.showMessageDialog(null, "Game Over", "Try it again", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public byte getDirection() {
        return direction;
    }

    //Saber si el Pacman se ha chocado con algún muro
    public boolean isDead() {
        return screen[playerPosition.y][playerPosition.x] == WALL;
    }

    //Obtener la puntuación
    public int getScore() {
        //Sumar 10 puntos cada vez que el Pacman se coma una bola
        if (screen[playerPosition.y][playerPosition.x] == BALL) {
            score += 10;
        } else if (screen[playerPosition.y][playerPosition.x] == PRIZE) {
            score += 100;
        }
        //Borrar el pacman de la posición antigua
        screen[playerPosition.y][playerPosition.x] = EMPTY;
        return score;
    }
}
