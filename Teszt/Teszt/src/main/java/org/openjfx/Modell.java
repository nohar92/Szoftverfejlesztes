package org.openjfx;

import java.util.Random;

public class Modell {


    public Modell(){

        generateBombs();
    }

    boolean[][] bombs = new boolean[12][12];
    boolean[][] revealed = new boolean[12][12];
    boolean[][] flagged = new boolean[12][12];
    public String username;
    int score = 0;



    public void generateBombs() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.println(i + " " + j);

                bombs[i][j] = false;
                revealed[i][j] = false;
                flagged[i][j] = false;

            }
            }

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
            System.out.println(counter);

        }



    public boolean IsThereAbomb(int mx,int my){

        return bombs[mx][my];
    }

    /**
     *  This method define
     * @param x
     * @param y
     * @return
     */

    int neighbournumber(int x, int y) {
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



