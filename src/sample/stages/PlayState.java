package sample.stages;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.collisions.CollisionDetector;
import sample.constants.Constants;
import sample.input.PlayerInputHandler;
import sample.models.playmodels.*;
import sample.models.playmodels.Number;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javafx.application.Application.STYLESHEET_MODENA;

public class PlayState extends AbstractStage {

    private Image background;
    // set the background Image if you run on MacOSX just replace "\\" with "/"
    private ImageView imageView;
    private Player player;
    private StopWatch stopWatch;
    private Label stopWatchLabel;
    private Pane pane;
    private List<FallingObject> fallingSymbolsAndNumbers;
    private List<MathOperator> mathOperators;
    private FallingObject fallingObject;
    private Text scoreText;
    private Text currentOperationText;
    private LongProperty score; // Set the starting Score (default is 128)
    private String currentOperation;
    private Random randomGenerator;
    AnimationTimer animationTimer;
    private PlayerInputHandler playerInputHandler;
    private CollisionDetector collisionDetector;

    public PlayState(Stage stage, Scene scene) {
        super(stage, scene);
        this.background = new Image(getClass().getResourceAsStream("../resources/background1.jpg"));
        this.imageView = new ImageView(this.background);
        this.player = new Player();
        this.score = new SimpleLongProperty(1);
        this.currentOperation = "Subtract";
        this.randomGenerator = new Random();
        this.pane = new Pane();
        this.fallingSymbolsAndNumbers = new ArrayList<>();
        this.mathOperators = new ArrayList<>();
        this.stopWatch = new StopWatch();
        this.collisionDetector = new CollisionDetector();
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public List<FallingObject> getFallingSymbolsAndNumbers() {
        return fallingSymbolsAndNumbers;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }


    public void setCurrentOperation(String currentOperation) {
        this.currentOperation = currentOperation;
    }

    private void update() {
        //If score is Zero || Infinity end dialog window is shown
        if (checkForEnd()) return;

        player.animate();
        this.playerInputHandler.processSinglePlayerInput();
        this.generateFallingObject();

        // Game collission: intersection between falling numbers and Player
        this.collisionDetector.checkForCollisionWithNumbers(this.fallingSymbolsAndNumbers
                , this.player, this.pane, this.currentOperation, this.score);
        this.collisionDetector.checkForCollisionWithOperators(this.mathOperators, this.player, this.pane, this);
    }

    private boolean checkForEnd() {
        if (score.get() == 0 || score.get() == 999999999) {
            showEndDialog();
            return true;
        }
        return false;
    }

    private void generateFallingObject() {
        if (System.nanoTime() % 60 == 0) {
            fallingObject = new Number();
            fallingSymbolsAndNumbers.add(fallingObject);
            pane.getChildren().add(fallingObject);
        }
        if (System.nanoTime() % 90 == 0) {
            fallingObject = new Symbol();
            fallingSymbolsAndNumbers.add(fallingObject);
            pane.getChildren().add(fallingObject);
        }
        if (System.nanoTime() % 120 == 0) {
            fallingObject = new MathOperator();
            mathOperators.add((MathOperator) fallingObject);
            pane.getChildren().add(fallingObject);
        }
    }

    private void clearFallingObjects() {
        for (FallingObject fallingObject : this.fallingSymbolsAndNumbers) {
            this.pane.getChildren().remove(fallingObject);
        }
        for (MathOperator mathoperator : this.mathOperators) {
            this.pane.getChildren().remove(mathoperator);
        }
    }


    private void showEndDialog() {
        try {
            this.animationTimer.stop();
            clearFallingObjects();
            this.player.stopAnimation();
            this.stopWatch.stopTimer();
            if (score.get() == 0) {
                new WinDialog(stage, scene).visualize(); // WIN
            } else {
                new GameOverDialog(stage, scene).visualize(); //LOSS
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //draw the current Score on the scene
    private void drawScore() {
        scoreText.textProperty().bind(Bindings.createStringBinding(() -> ("Score: " + score.get()), score));
        //replace infinity score with String INFINITY
        if (score.get() == 999999999) {
            scoreText.textProperty().bind(Bindings.createStringBinding(() -> ("Score: INFINITY...")));
        }
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


    private void drawThePlayScene() {
        this.pane.setPrefSize(Constants.WIDTH, Constants.HEIGHT); // set the scene dimensions

        // call the Timer class

        this.stopWatchLabel = this.stopWatch.getStopwatch();

        this.scoreText = createText("score");
        this.currentOperationText = createText("currentOp");

        this.pane.getChildren()
                .addAll(this.imageView,
                        this.scoreText,
                        this.currentOperationText,
                        this.player, this.stopWatchLabel); // add objects in the scene
    }


    @Override
    public void visualize() {
        this.drawThePlayScene();
        Scene scene = new Scene(this.pane);
        this.playerInputHandler = new PlayerInputHandler(scene, this.player);


        stage.setScene(scene);

        stage.show();

        animationTimer = new AnimationTimer() {
            @Override
            public synchronized void handle(long now) {
                update();
                drawScore();
                drawCurrentOperation();
            }
        };
        animationTimer.start();
    }
}

