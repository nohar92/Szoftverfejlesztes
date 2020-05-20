package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class SecondaryController implements Initializable {

    @FXML
    private Label score;
    @FXML
    private Label timer;
    @FXML
    private Canvas canvas;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    GameView gameView;
    Button button;
    public double mx = -100;
    public double my = -100;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //     gc = img.getGraphicsContext2D();
        gameView = new GameView(score, timer, canvas);

        /*
        button = new Button();
        button.setOnMouseClicked(event -> {
            MouseButton button = event.getButton();
            if (button == MouseButton.PRIMARY) {
                mx = event.getX();
                my = event.getY();
            } else if (button == MouseButton.SECONDARY) {
                mx = event.getX();
                my = event.getY();
            }
        });

         */

    /*    img.setOnMouseClicked(mouseEvent -> {
            switch (mouseEvent.getButton()) {
                case PRIMARY:
                    handlePrimaryClick(mouseEvent.getX(), mouseEvent.getY());
                    break;
                case SECONDARY:
                    handleSecondaryClick(mouseEvent.getX(), mouseEvent.getY());
                    break;
            }


        });
*/
    }


}