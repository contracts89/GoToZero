package sample.stages;

import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.collisions.CollsionDetectorImpl;
import sample.collisions.interfaces.CollisionDetector;
import sample.controllers.OperationSwitcherImpl;
import sample.controllers.ScoreHandlerImpl;
import sample.controllers.interfaces.OperationSwitcher;
import sample.controllers.interfaces.ScoreHandler;
import sample.graphical.PlayStateRenderer;
import sample.graphical.interfaces.Renderer;
import sample.input.PlayerHandlerImpl;
import sample.input.interfaces.PlayerHandler;
import sample.models.interfaces.Fallable;
import sample.models.interfaces.Player;
import sample.models.playmodels.FallingObject;
import sample.models.playmodels.PlayerImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PlayState extends AbstractStage {

    private static final String FALLABLE_PATH = "sample.models.playmodels.";

    private PlayerImpl player;
    private Pane pane;
    private List<Fallable> fallables;
    private LongProperty score;
    private AnimationTimer gameTimer;
    private PlayerHandler playerInputHandler;
    private CollisionDetector collisionDetector;
    private boolean isPaused;
    private ScoreHandler scoreHandler;
    private OperationSwitcher operationSwitcher;
    private Renderer playStateRenderer;

    public PlayState(Stage stage, Scene scene) throws ReflectiveOperationException {
        super(stage, scene);
        this.player = new PlayerImpl();
        this.score = new SimpleLongProperty(5);
        this.pane = new Pane();
        this.fallables = new ArrayList<>();
        this.scoreHandler = new ScoreHandlerImpl();
        this.operationSwitcher = new OperationSwitcherImpl();
        this.collisionDetector = new CollsionDetectorImpl(this.scoreHandler, this.operationSwitcher);
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }

    public List<Fallable> getFallingSymbolsAndNumbers() {
        return this.fallables;
    }

    public AnimationTimer getGameTimer() {
        return this.gameTimer;
    }

    private void update() throws ReflectiveOperationException {

        if (this.checkForEnd() || this.isPaused) {
            return; // Pause or End of game
        }
        this.player.getAnimator().animate();

        this.playStateRenderer.render();
        this.generateFallingObject();

        FallingObject collidedObject = (FallingObject) this.collisionDetector.returnCollidedObject(
                this.fallables,
                this.player,
                this.operationSwitcher.getMathOperation(),
                this.score);

        this.pane.getChildren().remove(collidedObject);
    }

    private boolean checkForEnd() {
        if (this.score.get() == 0 || this.score.get() == 999999999) {
            showEndDialog();
            return true;
        }
        return false;
    }

/*
using reflection to generate falling objects
*/
    private void generateFallingObject() throws ReflectiveOperationException {
        if (System.nanoTime() % 60 == 0) {
            this.generateFallable("Number");
        }
        if (System.nanoTime() % 90 == 0) {
            this.generateFallable("Symbol");
        }
        if (System.nanoTime() % 120 == 0) {
            this.generateFallable("MathOperatorImpl");
        }
    }

    @SuppressWarnings("unchecked")
    private void generateFallable(String type) throws ReflectiveOperationException {
        Class<Fallable> fallableClass = (Class<Fallable>) Class.forName(FALLABLE_PATH + type);
        FallingObject fallingObject = (FallingObject) fallableClass.getConstructor().newInstance();
        this.fallables.add(fallingObject);
        this.pane.getChildren().add(fallingObject);
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

                int result = this.collisionDetector.getCollidedObjectsCount();
                new WinDialog(stage, scene, result).visualize(); // WIN
            } else {

                new GameOverDialog(stage, scene).visualize(); //LOSS
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void visualize() throws ReflectiveOperationException {

        this.playStateRenderer = new PlayStateRenderer(this.collisionDetector, this.textCreator, this.pane
                , this.player, this.score, this.operationSwitcher);
        this.playStateRenderer.render();
        Scene scene = new Scene(this.pane);
        this.playerInputHandler = new PlayerHandlerImpl(scene, this.player);
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
                try {
                    update();
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            }
        };
        this.gameTimer.start();
    }


}

