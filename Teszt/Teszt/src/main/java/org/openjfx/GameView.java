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

public class GameView {


    public static int spacing = 4;
    public static Image bomb;
    public static Image flag;

     static {
        bomb = new Image(GameView.class.getResourceAsStream("bomba.png"));
        flag = new Image(GameView.class.getResourceAsStream("flag.png"));
    }

    public GraphicsContext gc;
    private int seconds = 0;
    private Timeline time;
    private Label currentTime = new Label();
    private Label currentScore = new Label();
    Alert alert = new Alert(Alert.AlertType.WARNING);
    Alert alertwin = new Alert(Alert.AlertType.WARNING);


    public GameView() {

    }

    /**
     * Set the gameboard
     * @param score The Label where we write the score
     * @param timer The Label where we write the time
     * @param canvas The Canvas what we use as a gameboard
     */
    public GameView(Label score, Label timer, Canvas canvas) {


        currentTime = timer;
        currentScore = score;
        currentTime.setText("Time: 0");
        currentScore.setText("Score: 0");
        alert.setTitle("Pop-up window!");
        alert.setHeaderText("You lost!");
        alert.setContentText("You clicked to the bomb field!");
        alertwin.setTitle("Pop-up window!");
        alertwin.setHeaderText("You won!");
        alertwin.setContentText("Congratulations!");
        startTimer();

        gc = canvas.getGraphicsContext2D();
        gc.fillRect(0, 0, 640, 640);
        gc.setFill(Color.DARKGRAY);
        gc.setFill(Color.GRAY);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                gc.fillRect(spacing + i * 40 - 2, spacing + j * 40 + 40, 40 - spacing, 40 - spacing);
            }
        }

    }

    /**
     * @param mx the x coordinate of the mouse
     * @param my the y coordinate of the mouse
     * @return return the row where we clicked with the mouse
     */


    public int inboxX(int mx, int my) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if ((mx >= spacing + i * 40 - 2) && (mx < spacing + i * 40 + 40 - spacing) &&
                        (my >= spacing + j * 40 + 40) && (my < j * 40 + 80 - spacing)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @param mx the x coordinate of the mouse
     * @param my the y coordinate of the mouse
     * @return return the column where we clicked with the mouse
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
     * The squares where you click will turn red. (The bomb's place)
     *
     * @param x the x coordinate of the bomb
     * @param y the y coordinate of the bomb
     */
    public void drawRedSquare(int x, int y) {
        gc.setFill(Color.RED);
        gc.fillRect(spacing + x * 40 - 2, spacing + y * 40 + 40, 40 - spacing, 40 - spacing);
        DrawBomb(x, y);

    }

    /**
     * Set the number's colour which are show the number of the bombs what the square has as its neighbors
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
     * Draw the number of the bombs what the square has as its neighbors
     *
     * @param number The number of bombs
     * @param x      Number of the row
     * @param y      Number of the column
     */
    public void DrawNeighbour(int number, int x, int y) {
        gc.strokeText(String.valueOf(number), spacing + x * 40 - 2 + 14, spacing + y * 40 + 40 + 23);

    }

    /**
     * Set the colour for the square which are appear due to our clicking (turn the square)
     *
     * @param x Number of the row
     * @param y Number of the column
     */
    public void DrawRevealed(int x, int y) {
        gc.setFill(Color.WHITE);
        gc.fillRect(spacing + x * 40 - 2, spacing + y * 40 + 40, 40 - spacing, 40 - spacing);

    }

    /**
     * Draw the bomb image to the square
     *
     * @param x Number of the row
     * @param y Number of the column
     */
    public void DrawBomb(int x, int y) {

        gc.drawImage(bomb, spacing + x * 40 - 2, spacing + y * 40 + 40);

    }

    /**
     * Draw the flag image to the square, what we get from x,y coordinates
     *
     * @param x Number of the row
     * @param y Number of the column
     */

    public void drawFlag(int x, int y) {
        gc.drawImage(flag, spacing + x * 40 - 2, spacing + y * 40 + 40 + 3);

    }



    public void getFlag(int x, int y) {
        gc.setFill(Color.GRAY);
        gc.fillRect(spacing + x * 40, spacing + y * 40 + 40, 40 - 2 * spacing, 40 - 2 * spacing);
    }


    public void setText(int score) {

        currentScore.setText(String.valueOf(score));

    }

    /**
     * Implementation of the timer
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

    public void stopTimer() {
        time.stop();

    }


    /**
     * Set the game
     */
    public void Restart() {

        gc.setFill(Color.DARKGRAY);
        gc.setFill(Color.GRAY);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                gc.fillRect(spacing + i * 40 - 2, spacing + j * 40 + 40, 40 - spacing, 40 - spacing);
            }
        }
        stopTimer();
        startTimer();

    }

    public void setScore(int score1) {
        currentScore.setText("Score: " + score1);
    }

    public int getSeconds() {
        return seconds;
    }

    public void ShowAlertWin() {
        stopTimer();
        alertwin.showAndWait();

    }

    public void ShowAlertLose() {
        stopTimer();
        alert.showAndWait();

    }


}






