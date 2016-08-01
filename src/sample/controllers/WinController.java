package sample.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.stages.WinDialog;

public class WinController {

    @FXML
    private Button playAgainButton;
    @FXML
    private Button backToMenu;

    public WinController() {
        this.backToMenu = new Button();
    }

    public void playAgain(ActionEvent actionEvent) {
        Stage stage = (Stage) playAgainButton.getScene().getWindow();
        stage.close();
        WinDialog.playAgain();
    }

    public void exitGame(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void goBackToMenu(Event event) {
        WinDialog.goToMenu();
    }
}
