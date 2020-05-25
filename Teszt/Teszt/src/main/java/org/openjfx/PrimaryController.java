package org.openjfx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {

    static String user;
    @FXML
    private TextField Username;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot();
        user =  Username.getText();
        System.out.println(user);



    }




}
