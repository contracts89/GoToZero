package sample.stages;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.constants.Constants;
import sample.models.playmodels.Number;
import sample.models.playmodels.Player;
import sample.models.playmodels.StopWatch;

import java.io.IOException;
import java.util.*;

import static javafx.application.Application.STYLESHEET_MODENA;

public class Singleplayer extends AbstractStage{

    public Image background = new Image(getClass().getResourceAsStream("../resources/background1.jpg"));// set the
    // background Image if you run on MacOSX just replace "\\" with "/"
    private Player player;
    private StopWatch stopWatch;
    private Label stopWatchTimer;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private Pane pane;
    private List<Number> numberList;
    private Text scoreText;
    private Text currentOperationText;
    private LongProperty score = new SimpleLongProperty(0); // Set the starting Score (default is 128)
    private String currentOperation = "Subtract";
    private Random randomGenerator = new Random();

    public Singleplayer(Stage stage , Scene scene) {
        super(stage,scene);
    }

    private void update() {
        player.animate();
        if (isPressed(KeyCode.RIGHT)) {
            player.setScaleX(1);
            player.moveRight();
        } else if (isPressed(KeyCode.LEFT)) {
            player.setScaleX(-1);
            player.moveLeft();
        } else {
            player.stayAtPos();
        }
        if (System.nanoTime() % 60 == 0) {
            Number numbers = new Number();
            numberList.add(numbers);
            pane.getChildren().add(numbers);
        }

        // Game collission: intersection between falling numbers and Player
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

                //If score is Zero the Game Over window is displayed as Winner
                if (score.get() == 0) {
                    try {
                        WinDialog winDialog = new WinDialog();
                        winDialog.show();
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                }
            }


        }
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    private void createPlayScene() {
    }

    //draw the current Score on the scene
    private void drawScore() {
        scoreText.textProperty().bind(Bindings.createStringBinding(() -> ("Score: " + score.get()), score));
    }

    // draw the current Math operation
    private void drawCurrentOperation() {
        currentOperationText.textProperty().bind(Bindings.createStringBinding(() -> ("Current Operation: " +
                currentOperation)));
    }

    private Text createText(String type) {
        Text testText = new Text();
        testText.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 20));

        if (type.equals("score")) {
            testText.setY(20); // position of the Score text on the scene
            testText.setX(0);
        } else {
            testText.setY(50); // position of the Current operation text on the scene
            testText.setX(0);
        }
        testText.setFill(Color.WHITE); // color of the Text
        testText.setOpacity(111);
        return testText;
    }

    // method to swithch between current math operators
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

    //generator to change the time of the Current operator
    private int genRndNCorrespondingToStringOperation() {
        if (System.nanoTime() % 250 == 0) {
            return randomGenerator.nextInt(4) + 1;
        }
        return 0;
    }

    @Override
    public void visualize() {

        pane = new Pane();
        pane.setPrefSize(Constants.WIDTH, Constants.HEIGHT); // set the scene dimensions
        numberList = new ArrayList<>();
        Number fallingNumbers = new Number();
        numberList.add(fallingNumbers);

        // call the Timer class
        StopWatch stopWatch = new StopWatch();
        stopWatch.stopwatch.setTranslateX(0);// X position of Timer
        stopWatch.stopwatch.setTranslateY(60); // Y postion of Timer
        stopWatch.stopwatch.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 20));
        stopWatch.stopwatch.setTextFill(Color.WHITE);

        stopWatchTimer = stopWatch.stopwatch;

        //call the Player class
        player = new Player();
        player.setTranslateX(500); //set the X start position of the Player
        player.setTranslateY(540); //set the Y start position of the Player
        ImageView imageView = new ImageView(background);
        scoreText = createText("score");
        currentOperationText = createText("currentOp");

        pane.getChildren().addAll(imageView, scoreText, currentOperationText, player, fallingNumbers, stopWatchTimer)
        ; // add objects in the scene

        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });
        this.stage.setScene(scene);
        this.stage.show();

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
}

