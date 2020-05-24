package org.openjfx;

import java.util.Random;
import org.tinylog.Logger;

public class Modell {


    public Modell(){

        generateBombs();
    }

    boolean[][] bombs = new boolean[12][12];
    boolean[][] revealed = new boolean[12][12];
    boolean[][] flagged = new boolean[12][12];

    int score = 0;
    public static int spacing = 4;


    /**
     * This method randomly generate the places of the bombs.
     */
    public void generateBombs() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
              //  System.out.println(i + " " + j);

                bombs[i][j] = false;
                revealed[i][j] = false;
                flagged[i][j] = false;
            }}

            Random randX = new Random();
            Random randY = new Random();

            int counter=0;
            do {
               int rand1 = randX.nextInt(12);
               int rand2 = randY.nextInt(12);

               if (!bombs[rand1][rand2]) {
                   bombs[rand1][rand2] = true;
                   counter++;
               }
           }while(counter != 10);

            Logger.info("Bombs generating!");
        }



    public boolean bombValue(int mx,int my){

        return bombs[mx][my];
    }

    /**
     * @param mx The x coordinate of the mouse.
     * @param my The y coordinate of the mouse.
     * @return return The <code>row</code> where we clicked with the mouse.
     */


    public int inboxX(int mx, int my) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if ((mx >= spacing + i * 40 - 2) && (mx < spacing + i * 40 + 40 - spacing) &&
                        (my >= spacing + j * 40 + 40) && (my < j * 40 + 80 - spacing)) {

                    return i;
                }
            }
        }Logger.info(" Defining the row's serial number");

        return -1;
    }
    /**
     * @param mx The x coordinate of the mouse.
     * @param my The y coordinate of the mouse.
     * @return The column where we clicked with the mouse.
     */
    public int inboxY(int mx, int my) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if ((mx >= spacing + i * 40 - 2) && (mx < spacing + i * 40 + 40 - spacing) &&
                        (my >= spacing + j * 40 + 40) && (my < j * 40 + 80 - spacing)) {

                    return j;
                }
            }
        }

        return -1;
    }






    /**
     *  This method define how many bombs has the field's neighbours.
     * @param x Serial number of the row.
     * @param y Serial number of the column.
     * @return The number of the bombs.
     */

    int neighbourNumber(int x, int y) {
        int i = 0;
        if (x == 0 && y == 11) {
            if (bombs[x][y - 1])
                i++;
            if (bombs[x + 1][y - 1])
                i++;
            if (bombs[x + 1][y])
                i++;
        } else if (x == 11 && y == 0) {
            if (bombs[x][y + 1])
                i++;
            if (bombs[x - 1][y + 1])
                i++;
            if (bombs[x - 1][y])
                i++;
        } else if (x == 0 && y == 0) {
            if (bombs[x][y + 1])
                i++;
            if (bombs[x + 1][y + 1])
                i++;
            if (bombs[x + 1][y])
                i++;
        } else if (x == 11 && y == 11) {
            if (bombs[x][y - 1])
                i++;
            if (bombs[x - 1][y - 1])
                i++;
            if (bombs[x - 1][y])
                i++;
        } else if (x == 11 && y != 0) {

            if (bombs[x][y - 1])
                i++;
            if (bombs[x][y + 1])
                i++;
            if (bombs[x - 1][y - 1])
                i++;
            if (bombs[x - 1][y + 1])
                i++;
            if (bombs[x - 1][y])
                i++;
        } else if (x != 0 && y == 11) {

            if (bombs[x][y - 1])
                i++;
            if (bombs[x + 1][y - 1])
                i++;
            if (bombs[x - 1][y - 1])
                i++;
            if (bombs[x - 1][y])
                i++;
            if (bombs[x + 1][y])
                i++;
        } else if (x == 0 && y != 11) {
            if (bombs[x][y - 1])
                i++;
            if (bombs[x][y + 1])
                i++;
            if (bombs[x + 1][y - 1])
                i++;
            if (bombs[x + 1][y])
                i++;
            if (bombs[x + 1][y + 1])
                i++;
        } else if (y == 0 && x != 11) {
            if (bombs[x + 1][y])
                i++;
            if (bombs[x - 1][y])
                i++;
            if (bombs[x + 1][y + 1])
                i++;
            if (bombs[x - 1][y + 1])
                i++;
            if (bombs[x][y + 1])
                i++;
        } else {
            if (bombs[x - 1][y - 1])
                i++;
            if (bombs[x - 1][y])
                i++;
            if (bombs[x - 1][y + 1])
                i++;
            if (bombs[x][y - 1])
                i++;
            if (bombs[x][y + 1])
                i++;
            if (bombs[x + 1][y - 1])
                i++;
            if (bombs[x + 1][y])
                i++;
            if (bombs[x + 1][y + 1])
                i++;

        }
        return i;

    }

    public boolean getRevealed(int x, int y) {
        return revealed [x][y];
    }

    public void setRevealed(int x, int y) {
        revealed [x][y] = true;
    }

    public boolean getFlagged(int x, int y) {
        return flagged[x][y];
    }

    public void setFlagged(int x, int y, boolean b) {
         flagged[x][y] = b;
    }

    public int getScore() { return score; }

    public void setScore(int score) { this.score += score; }
}



