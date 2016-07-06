package sample.models.playmodels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;
import sample.constants.Constants;

public class Number extends FallingObject {

    //    private Label textLabel = new Label();
//    private int numberScore;
//    private FallTransition fallTransition;
//    final String symbols = "*&^%$"; // list of symbols
//    final int Bomb = 0; // add zero value as bomb
//
//    public int getNumberScore() {
//        return numberScore;
//    }
//
//    public void setNumberScore(int numberScore) {
//        this.numberScore = numberScore;
//    }
//
//    public Label getTextLabel() {
//        return textLabel;
//    }
    public Number() {
        super();
        this.getTextLabel().setFont(Constants.NUMBER_FONT);
        this.getTextLabel().setTextFill(Color.WHITESMOKE);
    }

    @Override
    protected void setFallingObject() {

        this.setGeneratedNum(this.getRandomNumber().nextInt(10) + 1);

        // add symbols and numbers as falling objects
        this.getTextLabel().textProperty().bind(new SimpleIntegerProperty(this.getGeneratedNum()).asString());
        this.setNumberScore(this.getGeneratedNum());
    }
}