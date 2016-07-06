package sample.models.playmodels;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import sample.animations.FallTransition;
import sample.constants.Constants;

import java.util.Random;

public abstract class FallingObject extends StackPane {

    private Label textLabel;
    private int numberScore;
    private FallTransition fallTransition;
//    final String symbols = "*&^%$"; // list of symbols
    private int generatedNum;
    private Random randomNumber;

    public void setGeneratedNum(int generatedNum) {
        this.generatedNum = generatedNum;
    }

    //generate random numbers from 1 to 20
    public FallingObject()

    {
        this.randomNumber = new Random();

        this.textLabel = new Label();
        this.getChildren().addAll(this.textLabel);

//        this.textLabel.setFont(Constants.NUMBER_FONT);
//        this.textLabel.setTextFill(Color.WHITESMOKE);
        setFallingObject();
        this.setNumberScore(generatedNum);
        this.fallTransition = new FallTransition(Math.random() * 900, -100);
        this.fallTransition.useFallAnimation(this.textLabel, 10, Constants.NUMBER_ANIMATION_DROP_POINT);
    }

    protected abstract void setFallingObject();

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

    public void setNumberScore(int numberScore) {
        this.numberScore = numberScore;
    }

}
