package sample.models.playmodels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;
import sample.constants.Constants;

public class Number extends FallingObject {


    public Number() {
        super();
        this.setNumberScore(this.getGeneratedNum());
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