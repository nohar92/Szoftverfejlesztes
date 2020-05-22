package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class SecondaryController implements Initializable {

    @FXML
    private Label score;
    @FXML
    private Label timer;
    @FXML
    private Canvas canvas;

    @FXML
    public void restart() {
        modell = new Modell();
        modell.score = 0;
        gameView.Restart();
        gameView.setText(modell.getScore());

    }

    public void restartIfWon() {


    }

    GameView gameView;
    Modell modell;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modell = new Modell();
        gameView = new GameView(score, timer, canvas);
        canvas.setOnMouseClicked(mouseEvent -> {
            switch (mouseEvent.getButton()) {
                case PRIMARY:
                    handlePrimaryClick(mouseEvent.getX(), mouseEvent.getY());
                    break;
                case SECONDARY:
                    handleSecondaryClick(mouseEvent.getX(), mouseEvent.getY());
                    break;
            }
        });
    }

    private void handlePrimaryClick(double x, double y) {

        gameView.gc.setFill(Color.WHITE);
        if (gameView.inboxX((int) x, (int) y) != -1 && gameView.inboxY((int) x, (int) y) != -1 && !modell.getRevealed(gameView.inboxX((int) x, (int) y), gameView.inboxY((int) x, (int) y))) {
            if (modell.IsThereAbomb(gameView.inboxX((int) x, (int) y), gameView.inboxY((int) x, (int) y))) {
                for (int i = 0; i < 12; i++)
                    for (int j = 0; j < 12; j++)
                        if (modell.IsThereAbomb(i, j)) {
                            gameView.drawRedSquare(i, j);
                        }
                gameView.ShowAlertLose();
                gameView.stopTimer();
                restart();
            } else {
                gameView.gc.fillRect(GameView.spacing + gameView.inboxX((int) x, (int) y) * 40 - 2, GameView.spacing + gameView.inboxY((int) x, (int) y) * 40 + 40, 40 - GameView.spacing, 40 - GameView.spacing);
                neighbour(gameView.inboxX((int) x, (int) y), gameView.inboxY((int) x, (int) y));
                gameView.setText(modell.getScore());
                if (modell.getScore() == 268) {
                    gameView.ShowAlertWin();
                    restart();
                }
            }
        }
    }

    private void handleSecondaryClick(double x, double y) {
        if (gameView.inboxX((int) x, (int) y) != -1 && gameView.inboxY((int) x, (int) y) != -1 &&
                !modell.getRevealed(gameView.inboxX((int) x, (int) y), gameView.inboxY((int) x, (int) y)) &&
                !modell.getFlagged(gameView.inboxX((int) x, (int) y), gameView.inboxY((int) x, (int) y))) {
            gameView.drawFlag(gameView.inboxX((int) x, (int) y), gameView.inboxY((int) x, (int) y));
            modell.setFlagged(gameView.inboxX((int) x, (int) y), gameView.inboxY((int) x, (int) y), true);

        } else if (gameView.inboxX((int) x, (int) y) != -1 && gameView.inboxY((int) x, (int) y) != -1 &&
                !modell.getRevealed(gameView.inboxX((int) x, (int) y), gameView.inboxY((int) x, (int) y)) &&
                modell.getFlagged(gameView.inboxX((int) x, (int) y), gameView.inboxY((int) x, (int) y))) {
            modell.setFlagged(gameView.inboxX((int) x, (int) y), gameView.inboxY((int) x, (int) y), false);
            gameView.getFlag(gameView.inboxX((int) x, (int) y), gameView.inboxY((int) x, (int) y));

        }

    }

    public void neighbour(int x, int y) {


        int number = modell.neighbournumber(x, y);
        if (number > 0) {
            switch (number) {
                case 1:
                    gameView.setGCBlue();
                    break;
                case 2:
                    gameView.setGCGreen();
                    break;
                case 3:
                    gameView.setGCRed();
                    break;
                case 4:
                    gameView.setGCDarkBlue();
                    break;
                case 5:
                    gameView.setGCBrown();
                    break;
                case 6:
                    gameView.setGCCyan();
                    break;
                case 7:
                    gameView.setGCPurple();
                    break;
                case 8:
                    gameView.setGCDarkGray();
                    break;

            }


            if (modell.getRevealed(x, y) == false) {

                gameView.DrawRevealed(x, y);
                gameView.DrawNeighbour(number, x, y);
                modell.setRevealed(x, y);
                modell.setScore(2);

            }
        } else {
            while (number == 0 && modell.getRevealed(x, y) == false) {

                modell.setRevealed(x, y);
                gameView.DrawRevealed(x, y);
                modell.setScore(2);

                if (x == 0 && y == 11) {
                    neighbour(x, y - 1);
                    neighbour(x + 1, y - 1);
                    neighbour(x + 1, y);
                    break;
                } else if (x == 11 && y == 0) {
                    neighbour(x, y + 1);
                    neighbour(x - 1, y + 1);
                    neighbour(x - 1, y);
                    break;
                } else if (x == 0 && y == 0) {
                    neighbour(x, y + 1);
                    neighbour(x + 1, y + 1);
                    neighbour(x + 1, y);
                    break;
                } else if (x == 11 && y == 11) {
                    neighbour(x, y - 1);
                    neighbour(x - 1, y - 1);
                    neighbour(x - 1, y);
                    break;

                } else if (x == 11 && y != 0) {
                    neighbour(x, y - 1);
                    neighbour(x, y + 1);
                    neighbour(x - 1, y - 1);
                    neighbour(x - 1, y);
                    neighbour(x - 1, y + 1);
                    break;
                } else if (x != 0 && y == 11) {
                    neighbour(x, y - 1);
                    neighbour(x - 1, y - 1);
                    neighbour(x - 1, y);
                    neighbour(x + 1, y - 1);
                    neighbour(x + 1, y);
                    break;
                } else if (x == 0 && y != 11) {
                    neighbour(x, y - 1);
                    neighbour(x, y + 1);
                    neighbour(x + 1, y - 1);
                    neighbour(x + 1, y);
                    neighbour(x + 1, y + 1);
                    break;
                } else if (y == 0 && x != 11) {
                    neighbour(x + 1, y);
                    neighbour(x - 1, y);
                    neighbour(x + 1, y + 1);
                    neighbour(x - 1, y + 1);
                    neighbour(x, y + 1);
                    break;
                } else if (y > 0 && x < 11 && y < 11 && x > 0) {
                    neighbour(x - 1, y - 1);
                    neighbour(x - 1, y);
                    neighbour(x - 1, y + 1);
                    neighbour(x, y - 1);
                    neighbour(x, y + 1);
                    neighbour(x + 1, y - 1);
                    neighbour(x + 1, y);
                    neighbour(x + 1, y + 1);
                    break;
                } else {
                    break;
                }

            }
        }

    }


}
