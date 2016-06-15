package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SinglePlayerState {

    private Image background = new Image(getClass().getResourceAsStream("resources\\background1.jpg"));
    private Player player;
    private Number fallingNumbers;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private Pane pane;
    private AnimationTimer timer;
    private List<Number> numberses;

    public SinglePlayerState() {
        this.createPlayScene();
    }

    private void update() {
        player.useTheAnimation();
        if (isPressed(KeyCode.RIGHT)) {
            player.isMovingRight = true;
            player.setScaleX(1);
            player.move();
            player.isMovingLeft = false;
        } else if (isPressed(KeyCode.LEFT)) {
            player.isMovingRight = false;
            player.isMovingLeft = true;
            player.setScaleX(-1);
            player.move();
        } else {
            player.isMovingRight = false;
            player.isMovingLeft = false;
        }
        if (System.nanoTime() % 60 == 0) {
            Number numbers = new Number();
            numberses.add(numbers);
            pane.getChildren().add(numbers);
        }
        for (Number numberse : numberses) {
            if (numberse.getTextLabel().getBoundsInParent().intersects(player.getBoundsInParent())) {
                pane.getChildren().remove(numberse);
                System.out.println(numberse.getNumberScore());
                return;
            }
        }
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    private void createPlayScene() {
        pane = new Pane();
        pane.setPrefSize(900, 600);
        numberses = new ArrayList<>();
        fallingNumbers = new Number();
        numberses.add(fallingNumbers);
        player = new Player();
        player.setTranslateX(400);
        player.setTranslateY(485);
        ImageView imageView = new ImageView(background);
        pane.getChildren().addAll(imageView,player, fallingNumbers);
        Stage stage = new Stage();
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });
        stage.setScene(scene);
        stage.show();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();


    }

}
