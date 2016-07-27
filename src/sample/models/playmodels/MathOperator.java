package sample.models.playmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import sample.constants.Constants;
import sample.interfaces.MathOperatorImpl;

public class MathOperator extends FallingObject implements MathOperatorImpl {

    private String mathOperator;

    public MathOperator() {
        super();
        this.getTextLabel().setFont(Constants.NUMBER_FONT);
        this.getTextLabel().setTextFill(Color.RED);
    }

    public String getMathOperator() {
        return mathOperator;
    }

    @Override
    protected void setFallingObject() {

        this.mathOperator = String.valueOf(Constants.MATH_OPERATORS.charAt(this.getRandomNumber().nextInt(4)));
        this.getTextLabel().textProperty().bind(new SimpleStringProperty(this.mathOperator));
    }
}
