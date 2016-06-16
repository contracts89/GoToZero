package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WinController {
    public Button yesButton;
    public Button noButton;

    public void onYes(ActionEvent actionEvent) {
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }

    public void onNo(ActionEvent actionEvent) {
        Platform.exit();
    }
}
