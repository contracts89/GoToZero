package sample.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.collisions.CollsionDetectorImpl;
import sample.stages.PlayState;
import sample.stages.WinDialog;

public class WinController {
private WinDialog winDialog;

    public WinController(WinDialog winDialog) {
        this.winDialog = winDialog;
    }

    @FXML
    private Button playAgainButton;
    @FXML
    private Button backToMenu;

    public WinController() {
        this.backToMenu = new Button();
    }

    public void playAgain(ActionEvent actionEvent) {
        Stage stage = (Stage) playAgainButton.getScene().getWindow();
        this.winDialog.getStage().close();
       this.winDialog.playAgain();
    }

    public void exitGame(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void goBackToMenu(Event event) {
        //WinDialog.goToMenu(event);
        this.winDialog.goToMenu();
    }
    public  void playAgain() {
        new PlayState(winDialog.getStage(), winDialog.getScene()).visualize();
        CollsionDetectorImpl.setCollidedObjects(0);
    }
    public void goToMenu() {
        winDialog.getWinStage().close();
        winDialog.getStage().setScene(winDialog.getScene());
    }
}
