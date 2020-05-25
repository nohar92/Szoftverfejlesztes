package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;
import org.tinylog.Logger;

public class SecondaryController implements Initializable {

    GameView gameView;
    Modell modell  = new Modell();
    @FXML
    private Label score;
    @FXML
    private Label timer;
    @FXML
    private Canvas canvas;
    public String username;

    @FXML
    public void restart() {

        modell.score = 0;
        gameView.gameRestart();
        gameView.setText(modell.getScore());
        modell.restart();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modell = new Modell();
        gameView = new GameView(score, timer, canvas);
        modell.read();
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

    /**
     * This method turns up the field where we click with a left mouse button.
     * If the field hide a bomb than the method draw all of the bombs and we get a pop-up message which is tell that we lost.
     * If we close the message window, we will get a new chance for win. (game reset).
     * But if we turns up every field except bombs, we will get a pop-up  message which is tell that we won.
     * @param x The x coordinate where we clicked.
     * @param y The y coordinate where we clicked.
     */
    private void handlePrimaryClick(double x, double y) {

        gameView.gc.setFill(Color.WHITE);
        if (modell.inboxX((int) x, (int) y) != -1 && modell.inboxY((int) x, (int) y) != -1 &&
                !modell.getRevealed(modell.inboxX((int) x, (int) y), modell.inboxY((int) x, (int) y))) {
            if (modell.bombValue(modell.inboxX((int) x, (int) y), modell.inboxY((int) x, (int) y))) {
                for (int i = 0; i < 12; i++)
                    for (int j = 0; j < 12; j++)
                        if (modell.bombValue(i, j)) {
                            gameView.drawRedSquare(i, j);
                        }
                modell.addToRecords(gameView.seconds,modell.getScore(),PrimaryController.user);
                modell.write();
                gameView.showAlertLose();
                gameView.stopTimer();
                restart();
            } else {
                gameView.gc.fillRect(GameView.spacing + modell.inboxX((int) x, (int) y) * 40 - 2,
                        GameView.spacing + modell.inboxY((int) x, (int) y) * 40 + 40, 40 - GameView.spacing, 40 - GameView.spacing);
                neighbour(modell.inboxX((int) x, (int) y), modell.inboxY((int) x, (int) y));
                gameView.setText(modell.getScore());

                if (modell.doYouWin()) {
                    modell.addToRecords(gameView.seconds,modell.getScore(),PrimaryController.user);
                    modell.write();
                    gameView.showAlertWin();


                    restart();
                }
            }
        }
    }

    /**
     * This method take care of the right mouse button.
     * If we click to the field which is not revealed and there is no flag on it,then put down one flag
     * If the field already has a flag then the click will pick it up.
     * (-1)
     * @param x The x coordinate where we clicked.
     * @param y The y coordinate where we clicked.
     */
    private void handleSecondaryClick(double x, double y) {
        if (modell.inboxX((int) x, (int) y) != -1 && modell.inboxY((int) x, (int) y) != -1 &&
                !modell.getRevealed(modell.inboxX((int) x, (int) y), modell.inboxY((int) x, (int) y)) &&
                !modell.getFlagged(modell.inboxX((int) x, (int) y), modell.inboxY((int) x, (int) y))) {
            gameView.drawFlag(modell.inboxX((int) x, (int) y), modell.inboxY((int) x, (int) y));
            Logger.info("Placing the flag");
            modell.setFlagged(modell.inboxX((int) x, (int) y), modell.inboxY((int) x, (int) y), true);

        } else if (modell.inboxX((int) x, (int) y) != -1 && modell.inboxY((int) x, (int) y) != -1 &&
                !modell.getRevealed(modell.inboxX((int) x, (int) y), modell.inboxY((int) x, (int) y)) &&
                modell.getFlagged(modell.inboxX((int) x, (int) y), modell.inboxY((int) x, (int) y))) {
            modell.setFlagged(modell.inboxX((int) x, (int) y), modell.inboxY((int) x, (int) y), false);
            gameView.getFlag(modell.inboxX((int) x, (int) y), modell.inboxY((int) x, (int) y));

        }

    }

    /**
     *This method firstly get the number of adjacent bombs.
     *Flip the field and draw the number of the bomb with a proper colour but only if there is at least one bomb.
     *If there is no bomb (number=0) then flip the fields until we do not find a field which has a neighbour with bomb
     * and then the method draw the number of the bomb with a proper colour.
     * @param x The field's row number.
     * @param y The field's column number.
     */
    public void neighbour(int x, int y) {


        int number = modell.neighbourNumber(x, y);
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

                gameView.drawRevealed(x, y);
                gameView.drawNeighbourBombs(number, x, y);
                modell.setRevealed(x, y);
                modell.setScore(2);

            }
        } else {
            while (number == 0 && !modell.getRevealed(x, y)) {

                modell.setRevealed(x, y);
                gameView.drawRevealed(x, y);
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
