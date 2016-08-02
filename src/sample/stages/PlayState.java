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
import sample.collisions.CollsionDetectorImpl;
import sample.collisions.interfaces.CollisionDetector;
import sample.constants.Constants;
import sample.controllers.OperationSwitcherImpl;
import sample.controllers.ScoreHandlerImpl;
import sample.controllers.interfaces.OperationSwitcher;
import sample.controllers.interfaces.ScoreHandler;
import sample.input.PlayerInputHandler;
import sample.models.interfaces.Fallable;
import sample.models.interfaces.Player;
import sample.models.playmodels.*;
import sample.models.playmodels.Number;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sample.constants.MenuConstants.*;


public class PlayState extends AbstractStage {

    private Image background;
    private ImageView imageView;
    private PlayerImpl player;
    private Pane pane;
    private List<Fallable> fallables;
    private LongProperty score; // Set the starting Score (default is 128)
    //    private String currentOperation;
    private AnimationTimer gameTimer;
    private PlayerInputHandler playerInputHandler;
    private CollisionDetector collisionDetector;
    private boolean isPaused;
    private Label fallenObjectsAcquired;
    private ScoreHandler scoreHandler;
    private OperationSwitcher operationSwitcher;

    public PlayState(Stage stage, Scene scene) {
        super(stage, scene);
        this.background = new Image(getClass().getResourceAsStream(BACKGROUND_PATH));
        this.imageView = new ImageView(this.background);
        this.player = new PlayerImpl();
        this.score = new SimpleLongProperty(5);
        this.pane = new Pane();
        this.fallables = new ArrayList<>();
        this.scoreHandler = new ScoreHandlerImpl();
        this.operationSwitcher = new OperationSwitcherImpl();
        this.collisionDetector = new CollsionDetectorImpl(this.scoreHandler, this.operationSwitcher);
        this.fallenObjectsAcquired = new Label();
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
        //System.out.println("Is Pused:" + isPaused); // use for debugging
    }

    public List<Fallable> getFallingSymbolsAndNumbers() {
        return fallables;
    }

    public AnimationTimer getGameTimer() {
        return gameTimer;
    }

    private void update() {

        if (this.checkForEnd() || this.isPaused) {
            return;
        }
        this.player.getAnimator().animate();

        this.generateFallingObject();
        this.drawScoreAndCurrentOperation();
        this.drawObjectsAcquired();

        FallingObject toBeRemoved = (FallingObject) this.collisionDetector.returnCollidedObject(
                this.fallables,
                this.player,
                this.operationSwitcher.getMathOperation(),
                this.score);

        this.pane.getChildren().remove(toBeRemoved);
    }

    private boolean checkForEnd() {
        if (score.get() == 0 || score.get() == 999999999) {
            showEndDialog();
            return true;
        }
        return false;
    }

    private void generateFallingObject() {
        FallingObject fallingObject;
        if (System.nanoTime() % 60 == 0) {
            fallingObject = new Number();
            fallables.add(fallingObject);
            pane.getChildren().add(fallingObject);
        }
        if (System.nanoTime() % 90 == 0) {
            fallingObject = new Symbol();
            fallables.add(fallingObject);
            pane.getChildren().add(fallingObject);
        }
        if (System.nanoTime() % 120 == 0) {
            fallingObject = new MathOperatorImpl();
            fallables.add(fallingObject);
            pane.getChildren().add(fallingObject);
        }
    }

    private void clearFallingObjects() {
        for (Fallable fallingObject : this.fallables) {
            this.pane.getChildren().remove(fallingObject);
        }
    }

    private void showEndDialog() {
        try {
            this.gameTimer.stop();
            this.clearFallingObjects();
            this.player.stayAtPos();
            if (score.get() == 0) {
                drawObjectsAcquired();
                int result = this.collisionDetector.getCollidedObjectsCount();
                new WinDialog(stage, scene, result).visualize(); // WIN
            } else {
                drawScoreAndCurrentOperation();
                new GameOverDialog(stage, scene).visualize(); //LOSS
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void drawObjectsAcquired() {
        int currentCount = this.collisionDetector.getCollidedObjectsCount();
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
                this.operationSwitcher.getMathOperation())));
    }


    private void drawThePlayScene() {
        this.pane.setPrefSize(WIDTH, HEIGHT); // set the scene dimensions

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
        this.processInputInGame();
        this.gameLoop();
        this.gameTimer.start();
        this.inputLoop(scene);

        stage.setScene(scene);
        stage.show();

    }

    private void processInputInGame() {
        this.playerInputHandler.processSinglePlayerInput(this);
    }

    public Player getPlayer() {
        return this.player;
    }

    private void inputLoop(final Scene scene) {
        AnimationTimer inputTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                processInputInGame();
            }
        };
        inputTimer.start();
    }

    private void gameLoop() {
        this.gameTimer = new AnimationTimer() {
            @Override
            public synchronized void handle(long now) {
                update();
            }
        };
        this.gameTimer.start();
    }


}

