package sample.models.playmodels;

import javafx.beans.property.SimpleIntegerProperty;
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
        this.getChildren().addAll(this.textLabel);
        Random randomNumber = new Random();
        int generatedNum = randomNumber.nextInt(10) + 1; // the values of falling numbers (change to double (10)

        this.textLabel.textProperty().bind(new SimpleIntegerProperty(generatedNum).asString());

        this.textLabel.setFont(Constants.NUMBER_FONT);
        this.textLabel.setTextFill(Color.WHITESMOKE);

        this.setNumberScore(generatedNum);
        this.fallTransition = new FallTransition(Math.random() * 900, -100);
        this.fallTransition.useFallAnimation(this.textLabel,10,Constants.NUMBER_ANIMATION_DROP_POINT);
    }
    public void clear(){
        this.clear();
    }
}