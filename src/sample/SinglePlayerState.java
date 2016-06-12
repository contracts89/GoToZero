package sample;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SinglePlayerState {

    public SinglePlayerState() {
        this.createPlayScene();
    }

    private void createPlayScene() {
        Pane pane = new Pane();
        pane.setPrefSize(900, 600);
        Stage stage = new Stage();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
