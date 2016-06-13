package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class SinglePlayerState {

    Image background = new Image(getClass().getResourceAsStream("resources/background.jpg"));
    Player player;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    public SinglePlayerState() {
        this.createPlayScene();
    }
    private void update(){

        if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.isMovingRight=true;
            player.move();

        }else if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.isMovingRight=false;
            player.move();
        }else {

            player.animation.play();
            player.move();
        }
    }
    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    private void createPlayScene() {
        Pane pane = new Pane();
        pane.setPrefSize(900, 600);
        player = new Player();
        player.setTranslateX(400);
        player.setTranslateY(300);
        ImageView imageView = new ImageView(background);
        pane.getChildren().addAll(imageView,player);
        Stage stage = new Stage();
        Scene scene = new Scene(pane);

        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
            player.animation.stop();
        });
        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }
}
