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

public class PlayState extends AbstractStage {
    private boolean hasTwoPayers;
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
    private AnimationTimer gameTimer;
    private AnimationTimer inputTimer;
    private PlayerInputHandler playerInputHandler;
    private CollisionDetector collisionDetector;
    private boolean isPaused;


    public PlayState(Stage stage, Scene scene, boolean hasTwoPlayers) {
        super(stage, scene);
        this.hasTwoPayers=hasTwoPlayers;
        this.background = new Image(getClass().getResourceAsStream("../resources/background1.jpg"));
        this.imageView = new ImageView(this.background);
        this.player = new Player(hasTwoPlayers);
        this.score = new SimpleLongProperty(1);
        this.currentOperation = "Subtract";
        this.pane = new Pane();
        this.fallingSymbolsAndNumbers = new ArrayList<>();
        this.mathOperators = new ArrayList<>();
        this.stopWatch = new StopWatch();
        this.collisionDetector = new CollisionDetector();
    }

    public AnimationTimer getGameTimer() {
        return gameTimer;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void setHasTwoPayers(boolean hasTwoPayers) {
        this.hasTwoPayers = hasTwoPayers;
    }

    public boolean hasTwoPayers() {

        return hasTwoPayers;
    }

    public Player getPlayer() {
        return player;
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


    public void setCurrentOperation(String currentOperation) {
        this.currentOperation = currentOperation;
    }

    private void update() {
        //If score is Zero || Infinity end dialog window is shown
        if (checkForEnd()) return;

        player.animate();
        this.playerInputHandler.processSinglePlayerInput(this);
        this.generateFallingObject();
        this.drawScoreAndCurrentOperation();
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

    //draw the current Score on the scene
    private void drawScoreAndCurrentOperation() {
        Constants.SCORE_TEXT.textProperty().bind(Bindings.createStringBinding(() -> ("SCORE: " + score.get()), score));
        //replace infinity score with String INFINITY
        if (this.score.get() == 999999999) {
            Constants.SCORE_TEXT.textProperty().bind(Bindings.createStringBinding(() -> ("SCORE: INFINITY...")));
        }
        Constants.OPERATION_TEXT.textProperty().bind(Bindings.createStringBinding(() -> ("CURRENT OPERATION: " +
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
    private void processInput(){
        this.playerInputHandler.processSinglePlayerInput(this);
    }

    private void inputLoop() {
        inputTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                processInput();
            }
        };
        inputTimer.start();
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
    @Override
    public void visualize() {
        this.drawThePlayScene();

        Scene scene = new Scene(this.pane);
        this.playerInputHandler = new PlayerInputHandler(scene, this.player);

        inputLoop();
        gameLoop();

        stage.setScene(scene);
        stage.show();
    }
}

