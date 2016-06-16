package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WinController extends AnchorPane {
    public Button yesButton;
    public Button noButton;
    public AnchorPane winDialog;
    public WinController(){
        winDialog = new AnchorPane();
        yesButton = new Button();
        noButton = new Button();
    }
    public void onYes(ActionEvent actionEvent) {
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }

    public void onNo(ActionEvent actionEvent) {
        Platform.exit();
    }
}
