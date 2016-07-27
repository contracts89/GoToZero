package sample.models.playmodels;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import sample.animations.FallTransition;
import sample.constants.Constants;
import sample.interfaces.Fallable;

import java.util.Random;

public abstract class FallingObject extends StackPane implements Fallable {

    private Label textLabel;
    private int numberScore;
    private FallTransition fallTransition;
    private int generatedNum;
    private Random randomNumber;


    //generate random numbers from 1 to 20
    public FallingObject() {
        this.randomNumber = new Random();

        this.textLabel = new Label();
        this.getChildren().addAll(this.textLabel);
        setFallingObject();
        this.fallTransition = new FallTransition(Math.random() * 900, -100);
        this.fallTransition.useFallAnimation(this.textLabel, 10, Constants.NUMBER_ANIMATION_DROP_POINT);
    }

    protected void setGeneratedNum(int generatedNum) {
        this.generatedNum = generatedNum;
    }

    public FallTransition getFallTransition() {
        return fallTransition;
    }

    protected void setTextLabel(Label textLabel) {
        this.textLabel = textLabel;
    }

    public int getGeneratedNum() {
        return generatedNum;
    }

    public Random getRandomNumber() {
        return randomNumber;
    }

    public Label getTextLabel() {
        return textLabel;
    }

    public int getNumberScore() {
        return numberScore;
    }

    protected void setNumberScore(int numberScore) {
        this.numberScore = numberScore;
    }

    protected abstract void setFallingObject();

}
