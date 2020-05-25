package org.openjfx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModellTest {

    Modell modell = new Modell();

    @Test
    public void doYouWinTest(){

        modell.score = modell.max_score;
        assertTrue(modell.doYouWin());
    }

    @Test
    public  void generateBombsTest(){
        modell.generateBombs();
        int k= 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
            if(modell.getBombs(i,j))
                k++;
            }
        }
        assertEquals(modell.max_bombs, k);
    }

    @Test
    public void inboxXTest(){
        int p= modell.inboxX(0,0);
        assertEquals(-1,p);
        int q= modell.inboxX(2,44);
        assertEquals(0,q);
    }

    @Test
    public void inboxYTest(){
        int r= modell.inboxY(0,0);
        assertEquals(-1,r);
        int s= modell.inboxX(2,44);
        assertEquals(0,s);
    }

}