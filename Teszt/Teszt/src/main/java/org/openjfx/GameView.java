package org.openjfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.tinylog.Logger;

public class GameView {


    public static int spacing = 4;
    public static Image bomb;
    public static Image flag;

    static {
        bomb = new Image(GameView.class.getResourceAsStream("bomb.png"));
        flag = new Image(GameView.class.getResourceAsStream("flag2.png"));
    }

    public GraphicsContext gc;
    Alert alertWin = new Alert(Alert.AlertType.WARNING);
    Alert alertLose = new Alert(Alert.AlertType.WARNING);
    public int seconds = 0;
    private Timeline time;
    private Label currentTime = new Label();
    private Label currentScore = new Label();


    public GameView() {

    }

    /**
     * Set the gameboard, set the pop-up window messages.
     *
     * @param score  The Label where we write the score.
     * @param timer  The Label where we write the time.
     * @param canvas The Canvas what we use as a gameboard.
     */
    public GameView(Label score, Label timer, Canvas canvas) {


        currentTime = timer;
        currentScore = score;
        currentTime.setText("Time: 0");
        currentScore.setText("Score: 0");
        alertLose.setTitle("Pop-up window!");
        alertLose.setHeaderText("You clicked to the bomb field!");
        alertLose.setContentText("You lost! Try again!");
        alertWin.setTitle("Pop-up window!");
        alertWin.setHeaderText("You won!");
        alertWin.setContentText("Congratulations!");
        startTimer();


        gc = canvas.getGraphicsContext2D();
        gc.fillRect(0, 0, 640, 640);
        gc.setFill(Color.GRAY);
        Logger.info("The board drawing");
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                gc.fillRect(spacing + i * 40 - 2, spacing + j * 40 + 40, 40 - spacing, 40 - spacing);
            }
        }

    }


    /**
     * Draw the bombs.
     *
     * @param x The x coordinate of the bomb.
     * @param y The y coordinate of the bomb.
     */
    public void drawRedSquare(int x, int y) {
        gc.setFill(Color.RED);
        gc.fillRect(spacing + x * 40 - 2, spacing + y * 40 + 40, 40 - spacing, 40 - spacing);
        Logger.info( "Drawing the bombs");
        drawBomb(x, y);

    }

    /**
     * Set the number's colour which are show the number of the bombs what the square has as its neighbors.
     */

    public void setGCBlue() {
        gc.setStroke(Color.BLUE);
    }

    public void setGCGreen() {
        gc.setStroke(Color.GREEN);

    }

    public void setGCRed() {
        gc.setStroke(Color.RED);
    }

    public void setGCDarkBlue() {
        gc.setStroke(Color.DARKBLUE);
    }

    public void setGCBrown() {
        gc.setStroke(Color.BROWN);
    }

    public void setGCCyan() {
        gc.setStroke(Color.CYAN);
    }

    public void setGCPurple() {
        gc.setStroke(Color.PURPLE);
    }

    public void setGCDarkGray() {
        gc.setStroke(Color.DARKGRAY);
    }

    /**
     * Draw the number of the bombs what the square has as its neighbors.
     *
     * @param number The number of bombs.
     * @param x      Number of the row.
     * @param y      Number of the column.
     */
    public void drawNeighbourBombs(int number, int x, int y) {
        gc.strokeText(String.valueOf(number), spacing + x * 40 - 2 + 14, spacing + y * 40 + 40 + 23);

    }

    /**
     * Set the colour for the square which are appear due to our clicking (flip the field).
     *
     * @param x Number of the row.
     * @param y Number of the column.
     */
    public void drawRevealed(int x, int y) {
        gc.setFill(Color.WHITE);
        gc.fillRect(spacing + x * 40 - 2, spacing + y * 40 + 40, 40 - spacing, 40 - spacing);

    }

    /**
     * Draw the bomb image to the square.
     *
     * @param x Number of the row.
     * @param y Number of the column.
     */
    public void drawBomb(int x, int y) {

        gc.drawImage(bomb, spacing + x * 40 - 2, spacing + y * 40 + 40);

    }

    /**
     * Draw the flag image to the square, what we get from x,y coordinates.
     *
     * @param x Number of the row.
     * @param y Number of the column.
     */

    public void drawFlag(int x, int y) {
        gc.drawImage(flag, spacing + x * 40 - 2, spacing + y * 40 + 40);

    }

    /**
     * Picks up the flag.
     *
     * @param x Number of the row.
     * @param y Number of the column.
     */

    public void getFlag(int x, int y) {
        gc.setFill(Color.GRAY);
        gc.fillRect(spacing + x * 40-1, spacing + y * 40 + 40, 40 -  spacing-1, 40 - spacing);
        Logger.info("Picking up the flag");
    }


    public void setText(int score) {

        currentScore.setText(String.valueOf(score));

    }

    /**
     * Implementation of the timer.
     */
    public void startTimer() {

        seconds = 0;
        time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            seconds++;
            currentTime.setText("Time: " + seconds);
        }));
        time.play();
    }

    /**
     *  Stop the timer.
     */
    public void stopTimer() {
        time.stop();
    }


    /**
     * Set the game to the starting state (game reset).
     */
    public void gameRestart() {


        gc.setFill(Color.GRAY);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                gc.fillRect(spacing + i * 40 - 2, spacing + j * 40 + 40, 40 - spacing, 40 - spacing);
            }
        }
        stopTimer();
        startTimer();

    }

    /**
     *  The alert window pops up if you win.
     */
    public void showAlertWin() {
        stopTimer();
        alertWin.showAndWait();

    }

    /**
     *  The alert window pops up if you lose.
     */
    public void showAlertLose() {
        stopTimer();
        alertLose.showAndWait();

    }


}






