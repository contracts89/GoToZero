package sample;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SinglePlayerState {

    Player player;
    public SinglePlayerState() {
        this.createPlayScene();
    }

    private void createPlayScene() {
        Pane pane = new Pane();
        pane.setPrefSize(900, 600);
        Image image = new Image(getClass().getResourceAsStream("resources/background.jpg"));
        player = new Player();
        player.setTranslateX(400);
        player.setTranslateY(300);
        ImageView imageView = new ImageView(image);
        pane.getChildren().addAll(imageView,player);
        Stage stage = new Stage();
        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
