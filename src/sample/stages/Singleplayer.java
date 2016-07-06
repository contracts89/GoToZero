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
import sample.models.playmodels.Number;
import sample.models.playmodels.Player;
import sample.models.playmodels.StopWatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javafx.application.Application.STYLESHEET_MODENA;

public class Singleplayer extends AbstractStage {

    private Image background;
    // set the background Image if you run on MacOSX just replace "\\" with "/"
    private ImageView imageView;
    private Player player;
    private StopWatch stopWatch;
    private Label stopWatchLabel;
    private Pane pane;
    private List<Number> numberList;
    private Number fallingNumber;
    private Text scoreText;
    private Text currentOperationText;
    private LongProperty score; // Set the starting Score (default is 128)
    private String currentOperation;
    private Random randomGenerator;
    AnimationTimer animationTimer;
    private PlayerInputHandler playerInputHandler;
    private CollisionDetector collisionDetector;
    private volatile boolean isPaused;

    public Singleplayer(Stage stage, Scene scene) {
        super(stage, scene);
        this.background = new Image(getClass().getResourceAsStream("../resources/background1.jpg"));
        this.imageView = new ImageView(this.background);
        this.player = new Player();
        this.score = new SimpleLongProperty(1);
        this.currentOperation = "Subtract";
        this.randomGenerator = new Random();
        this.pane = new Pane();
        this.numberList = new ArrayList<>();
        this.stopWatch = new StopWatch();
        this.collisionDetector = new CollisionDetector();
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public List<Number> getNumberList() {
        return numberList;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    private void update()  {
        //If score is Zero the dialog window is displayed as Winner
        if (score.get() == 0) {
            showWinDialog();
            return;
        }
        // if score is Infinity the dialog window is displayed as Loser (Game Over.)
        if (score.get()==999999999){
            showWinDialogGameOver();
            return;
        }

        player.animate();
        this.playerInputHandler.processSinglePlayerInput();
        this.generateFallingNumber();

        // Game collission: intersection between falling numbers and Player
        this.collisionDetector.checkForCollisionWithNumbers(this.numberList
                ,this.player,this.pane,this.currentOperation,this.score);
    }


    private void generateFallingNumber() {
        if (System.nanoTime() % 60 == 0) {
            fallingNumber = new Number();
            numberList.add(fallingNumber);
            pane.getChildren().add(fallingNumber);
        }
    }

    private void clearNumbers() {
        for (Number number : this.numberList) {
            this.pane.getChildren().remove(number);
        }
    }

    // Win the game dialog Window
    private void showWinDialog() {
        try {
            this.animationTimer.stop();
            clearNumbers();
            this.player.stopAnimation();
            this.stopWatch.stopTimer();
            WinDialog winDialog = new WinDialog(stage, scene);
            winDialog.visualize();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Add game over dialog Window
    private void showWinDialogGameOver() {
        try {
            this.animationTimer.stop();
            clearNumbers();
            this.player.stopAnimation();
            this.stopWatch.stopTimer();
            WinDialogGameOver winDialogGameOver = new WinDialogGameOver(stage, scene);
            winDialogGameOver.visualize();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //draw the current Score on the scene
    private void drawScore() {
        scoreText.textProperty().bind(Bindings.createStringBinding(() -> ("Score: " + score.get()), score));
        //replace infinity score with String INFINITY
        if (score.get()==999999999){
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

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    @Override
    public void visualize() {
        this.drawThePlayScene();
        Scene scene = new Scene(this.pane);
        this.playerInputHandler = new PlayerInputHandler(scene, this.player,this);


        stage.setScene(scene);

        stage.show();

        animationTimer = new AnimationTimer() {
            @Override
            public synchronized void handle(long now) {
                update();
                drawScore();
                drawCurrentOperation();
                generateOperator();
            }
        };
        animationTimer.start();
    }
}

