package sample.models.playmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import sample.constants.Constants;
import sample.models.interfaces.MathOperator;

import static sample.constants.PlayStateConstants.MATH_OPERATORS;

public class MathOperatorImpl extends FallingObject implements MathOperator {

    private String mathOperator;

    public MathOperatorImpl() {
        super();
        this.getTextLabel().setFont(Constants.NUMBER_FONT);
        this.getTextLabel().setTextFill(Color.RED);
    }

    public String getMathOperator() {
        return mathOperator;
    }

    @Override
    protected void setFallingObject() {

        this.mathOperator = String.valueOf(MATH_OPERATORS.charAt(this.getRandomNumber().nextInt(4)));
        this.getTextLabel().textProperty().bind(new SimpleStringProperty(this.mathOperator));
    }
}
