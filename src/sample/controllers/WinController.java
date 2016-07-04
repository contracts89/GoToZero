package sample.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WinController {
    @FXML
    private Label resulField;
    @FXML
    private Button playAgainButton;


    public void playAgain(ActionEvent actionEvent) {
        Stage stage = (Stage) playAgainButton.getScene().getWindow();
        stage.close();
    }

    public void exitGame(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void backToMenuAction(ActionEvent actionEvent) {

    }

}
