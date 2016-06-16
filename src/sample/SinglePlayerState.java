package sample;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.font.TextLabel;

import java.util.*;

import static javafx.application.Application.STYLESHEET_MODENA;

public class SinglePlayerState {

    private Image background = new Image(getClass().getResourceAsStream("resources\\background1.jpg"));
    private Player player;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private Pane pane;
    private List<Number> numberList;
    private Text scoreText;
    private Text currentOperationText;
    private LongProperty score = new SimpleLongProperty(128);
    private String currentOperation = "Subtract";
    private Random randomGenerator = new Random();

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
            numberList.add(numbers);
            pane.getChildren().add(numbers);
        }
        for (int i = 0; i < numberList.size(); i++) {
            if (numberList.get(i).getTextLabel().getBoundsInParent().intersects(player.getBoundsInParent())) {
                pane.getChildren().remove(numberList.get(i));
                switch (currentOperation) {
                    case "Divide":
                        score.set(score.get() / (numberList.get(i).getNumberScore()));
                        break;
                    case "Add":
                        score.set(score.get() + (numberList.get(i).getNumberScore()));
                        break;
                    case "Multiply":
                        score.set(score.get() * (numberList.get(i).getNumberScore()));
                        break;
                    case "Subtract":
                        score.set(score.get() - (numberList.get(i).getNumberScore()));
                        break;
                }
                numberList.removeAll(Collections.singleton(numberList.get(i)));
            }

            if(score.get() == 0){
                
            }
        }
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    private void createPlayScene() {
        pane = new Pane();
        pane.setPrefSize(900, 600);
        numberList = new ArrayList<>();
        Number fallingNumbers = new Number();
        numberList.add(fallingNumbers);
        player = new Player();
        player.setTranslateX(500);
        player.setTranslateY(540);
        ImageView imageView = new ImageView(background);
        scoreText = createText("score");
        currentOperationText = createText("currentOp");
        pane.getChildren().addAll(imageView, scoreText, currentOperationText, player, fallingNumbers);
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
                drawScore();
                drawCurrentOperation();
                generateOperator();
            }
        };
        timer.start();


    }

    private void drawScore() {
        scoreText.textProperty().bind(Bindings.createStringBinding(() -> ("Score: " + score.get()), score));
    }

    private void drawCurrentOperation() {
        currentOperationText.textProperty().bind(Bindings.createStringBinding(() -> ("Current Operation: " + currentOperation)));
    }

    private Text createText(String type) {
        Text testText = new Text();
        testText.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 20));
        if (type.equals("score")) {
            testText.setY(20);
            testText.setX(0);
        } else {
            testText.setY(50);
            testText.setX(0);
        }
        testText.setFill(Color.WHITE);
        testText.setOpacity(111);
        return testText;
    }

    private void generateOperator() {
        int number = genRndNCorrespondingToStringOperation();
        switch (number) {
            case 1:
                currentOperation = "Add";
                break;
            case 2:
                currentOperation = "Subtract";
                break;
            case 3:
                currentOperation = "Multiply";
                break;
            case 4:
                currentOperation = "Divide";
                break;
        }
    }

    private int genRndNCorrespondingToStringOperation() {
        if (System.nanoTime() % 250 == 0) {
            return randomGenerator.nextInt(4) + 1;
        }
        return 0;
    }
}

