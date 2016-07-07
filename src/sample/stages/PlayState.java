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
    private Pane pane;
    private List<FallingObject> fallingSymbolsAndNumbers;
    private List<MathOperator> mathOperators;
    private FallingObject fallingObject;
    private LongProperty score; // Set the starting Score (default is 128)
    private String currentOperation;
    private AnimationTimer gameTimer;
    private PlayerInputHandler playerInputHandler;
    private CollisionDetector collisionDetector;
    private boolean isPaused;
    private Label fallenObjectsAcquired;

    public PlayState(Stage stage, Scene scene, boolean hasTwoPlayers) {
        super(stage, scene);
        this.hasTwoPayers = hasTwoPlayers;
        this.background = new Image(getClass().getResourceAsStream("../resources/background1.jpg"));
        this.imageView = new ImageView(this.background);
        this.player = new Player(hasTwoPlayers);
        this.score = new SimpleLongProperty(5);
        this.currentOperation = "Subtract";
        this.pane = new Pane();
        this.fallingSymbolsAndNumbers = new ArrayList<>();
        this.mathOperators = new ArrayList<>();
        this.collisionDetector = new CollisionDetector();
        this.fallenObjectsAcquired = new Label();
    }


    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }


    public Player getPlayer() {
        return player;
    }

    public List<MathOperator> getMathOperators() {
        return mathOperators;
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
        this.generateFallingObject();
        this.drawScoreAndCurrentOperation();
        this.drawObjectsAcquired();
        this.playerInputHandler.processSinglePlayerInput(this);
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
            if (score.get() == 0) {
                new WinDialog(stage, scene).visualize(); // WIN
            } else {
                new GameOverDialog(stage, scene).visualize(); //LOSS
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void drawObjectsAcquired() {
        int currentCount = CollisionDetector.getCollidedObjects();
        this.fallenObjectsAcquired.setText(String.format("CURRENT COUNT: %d", currentCount));
        this.fallenObjectsAcquired.setFont(Constants.GAME_FONT);
        this.fallenObjectsAcquired.setTextFill(Color.YELLOW);
        this.fallenObjectsAcquired.setTranslateX(5);
        this.fallenObjectsAcquired.setTranslateY(60);
        this.fallenObjectsAcquired.setOpacity(5);
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

        this.pane.getChildren()
                .addAll(this.imageView,
                        Constants.SCORE_TEXT,
                        Constants.OPERATION_TEXT,
                        this.fallenObjectsAcquired,
                        this.player); // add objects in the scene
    }

    @Override
    public void visualize() {
        this.drawThePlayScene();

        Scene scene = new Scene(this.pane);
        this.playerInputHandler = new PlayerInputHandler(scene, this.player);

        gameTimer = new AnimationTimer() {
            @Override
            public synchronized void handle(long now) {
                update();
            }
        };
        gameTimer.start();

        stage.setScene(scene);
        stage.show();
    }
}

