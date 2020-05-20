package org.openjfx;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GameView {


    public GraphicsContext gc;
    private Label currentTime = new Label();
    private Label currentScore = new Label();
    private int seconds = 0;
    private Timeline time;

    public GameView() {
    }

    /**
     * Set the timer and  the score fields
     */
    public GameView(Label score, Label timer, Canvas canvas) {

        int spacing = 4;
        currentTime = timer;
        currentScore = score;
        currentTime.setText("Time: 0");
        currentScore.setText("Score: 0");
        time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            seconds++;
            currentTime.setText("Time: " + seconds);
        }));
        time.play();


        gc = canvas.getGraphicsContext2D();

        gc.fillRect(0, 0, 640, 640);
        gc.setFill(Color.DARKGRAY);
        gc.setFill(Color.GRAY);

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {

           /*     if (mx >= spacing + i * 40 - 2 && mx < spacing + j * 40 + 40 - spacing &&
                        my >= spacing + j * 40 + 40 && j * 80 + 80 - spacing) {

                    gc.setFill(Color.GRAY);

                }

            */
                gc.fillRect(spacing + i * 40 - 2, spacing + j * 40 + 40, 40 - spacing, 40 - spacing);
            }
        }


    }


}






