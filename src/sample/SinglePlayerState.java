package sample;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SinglePlayerState {

    public SinglePlayerState() {
        this.createPlayScene();
    }

    private void createPlayScene() {
        Pane pane = new Pane();
        pane.setPrefSize(901, 601);
        Image image = new Image(getClass().getResourceAsStream("resources/background.jpg"));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        Stage stage = new Stage();
        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
