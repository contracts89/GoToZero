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
    private LongProperty score; // Set the starting Score (default is 128)
    private String currentOperation;
    private Random randomGenerator;
    AnimationTimer gameTimer;
    AnimationTimer inputTimer;
    private boolean isPaused;
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

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public AnimationTimer getInputTimer() {
        return inputTimer;
    }

    public List<MathOperator> getMathOperators() {
        return mathOperators;
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public List<FallingObject> getFallingSymbolsAndNumbers() {
        return fallingSymbolsAndNumbers;
    }

    public AnimationTimer getGameTimer() {
        return gameTimer;
    }


    public void setCurrentOperation(String currentOperation) {
        this.currentOperation = currentOperation;
    }

    private void update() {
        //If score is Zero || Infinity end dialog window is shown
        if (checkForEnd()) return;

        player.animate();
        this.generateFallingObject();
//        playerInputHandler.processSinglePlayerInput(this);
        // Game collission: intersection between falling numbers and Player
        this.collisionDetector.checkForCollisionWithNumbers(this.fallingSymbolsAndNumbers
                , this.player, this.pane, this.currentOperation, this.score);
        this.collisionDetector.checkForCollisionWithOperators(this.mathOperators, this.player, this.pane, this);
        this.drawScoreAndOperation();
    }

    private boolean checkForEnd() {
        if (this.score.get() == 0 || this.score.get() == 999999999) {
            showEndDialog();
            return true;
        }
        return false;
    }

    private void generateFallingObject() {
        if (System.nanoTime() % 60 == 0) {
            this.fallingObject = new Number();
            this.fallingSymbolsAndNumbers.add(this.fallingObject);
            this.pane.getChildren().add(this.fallingObject);
        }
        if (System.nanoTime() % 90 == 0) {
            this.fallingObject = new Symbol();
            this.fallingSymbolsAndNumbers.add(this.fallingObject);
            this.pane.getChildren().add(this.fallingObject);
        }
        if (System.nanoTime() % 120 == 0) {
            this.fallingObject = new MathOperator();
            this.mathOperators.add((MathOperator) this.fallingObject);
            this.pane.getChildren().add(this.fallingObject);
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
            this.gameTimer.stop();
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

    //draw the current Score And Operation on the scene
    private void drawScoreAndOperation() {
        Constants.SCORE_TEXT.textProperty().bind(Bindings.createStringBinding(() -> ("SCORE: " + score.get()), score));
        //replace infinity score with String INFINITY
        if (this.score.get() == 999999999) {
            Constants.SCORE_TEXT.textProperty().bind(Bindings.createStringBinding(() -> ("SCORE: INFINITY...")));
        }
        Constants.OPERATION_TEXT.textProperty().bind(Bindings.createStringBinding(() -> ("CURRENT OPERATION = " +
                this.currentOperation)));
    }

    private void drawThePlayScene() {
        this.pane.setPrefSize(Constants.WIDTH, Constants.HEIGHT); // set the scene dimensions

        // call the Timer class
        this.stopWatchLabel = this.stopWatch.getStopwatch();

        this.pane.getChildren()
                .addAll(this.imageView,
                        Constants.SCORE_TEXT,
                        Constants.OPERATION_TEXT,
                        this.player, this.stopWatchLabel); // add objects in the scene
    }


    @Override
    public void visualize() {
        this.drawThePlayScene();
        Scene scene = new Scene(this.pane);
        this.playerInputHandler = new PlayerInputHandler(scene, this.player);
        processInputInGame(scene);
        gameLoop();
        stage.setScene(scene);

        stage.show();

        inputLoop(scene);
    }

    public Player getPlayer() {
        return player;
    }

    private void inputLoop(final Scene scene) {
        inputTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                processInputInGame(scene);
            }
        };
        inputTimer.start();
    }

    private void processInputInGame(Scene scene) {
        this.playerInputHandler.processSinglePlayerInput(this);
    }

    private void gameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public synchronized void handle(long now) {
                update();
            }
        };
        gameTimer.start();
    }
}

