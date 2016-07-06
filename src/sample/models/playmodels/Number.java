package sample.models.playmodels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import sample.animations.FallTransition;
import sample.constants.Constants;

import java.util.Random;

public class Number extends StackPane {

    private Label textLabel = new Label();
    private int numberScore;
    private FallTransition fallTransition;
    final String symbols = "*&^%$"; // list of symbols
    final int Bomb = 0; // add zero value as bomb

    public int getNumberScore() {
        return numberScore;
    }

    public void setNumberScore(int numberScore) {
        this.numberScore = numberScore;
    }

    public Label getTextLabel() {
        return textLabel;
    }

    public Number() {

        //generate random numbers from 1 to 20
        this.getChildren().addAll(this.textLabel);
        Random randomNumber = new Random();
        int generatedNum = randomNumber.nextInt(20)+1; // the values of falling numbers (change to double (20) -- clear +1 to generate 0 value

        // generate symbols from symbols list (symbols = "*&^%$")
        Random randomSymbol = new Random();
        String generateSymbols = String.valueOf(symbols.charAt(randomSymbol.nextInt(5)));

        // add symbols and numbers as falling objects
        this.textLabel.textProperty().bind(new SimpleIntegerProperty(generatedNum).asString());

        //If generator trying to push numbers greater than 10 it replace it with random symbols
        if (generatedNum > 10) {
            this.textLabel.textProperty().bind(new SimpleStringProperty(generateSymbols));
        }

        // If generator trying to push number 20 it replace it with 0.
        if (generatedNum == 20) {
            this.textLabel.textProperty().bind(new SimpleIntegerProperty(Bomb).asString());
            generatedNum = 0;
        }

        this.textLabel.setFont(Constants.NUMBER_FONT);
        this.textLabel.setTextFill(Color.WHITESMOKE);

        this.setNumberScore(generatedNum);
        this.fallTransition = new FallTransition(Math.random() * 900, -100);
        this.fallTransition.useFallAnimation(this.textLabel, 10, Constants.NUMBER_ANIMATION_DROP_POINT);
    }

    public void stopAnimation() {
        this.fallTransition.getPathTransition().stop();
    }

    public void resumeAnimation(){
        this.fallTransition.getPathTransition().play();
    }
}