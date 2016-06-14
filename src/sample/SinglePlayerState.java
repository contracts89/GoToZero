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

//    private Image background = new Image(getClass().getResourceAsStream("resources/background.jpg"));
    private Player player;
    private Numbers fallingNumbers;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    public SinglePlayerState() {
        this.createPlayScene();
    }

    private void update() {
        player.useTheAnimation();
        if (isPressed(KeyCode.RIGHT)) {
            player.isMovingRight=true;
            player.setScaleX(1);
            player.move();

        } else if (isPressed(KeyCode.LEFT)) {
            player.isMovingRight=false;
            player.setScaleX(-1);
            player.move();
        }
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    private void createPlayScene() {
        Pane pane = new Pane();
        pane.setPrefSize(900, 600);

        fallingNumbers = new Numbers();

        player = new Player();
        player.setTranslateX(400);
        player.setTranslateY(485);
//        ImageView imageView = new ImageView(background);
        pane.getChildren().addAll( player, fallingNumbers);
        Stage stage = new Stage();
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
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
