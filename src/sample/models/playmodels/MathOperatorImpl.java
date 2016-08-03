package sample.models.playmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import sample.controllers.MathOperation;
import sample.models.interfaces.MathOperator;

import static sample.constants.MenuConstants.NUMBER_FONT;

public class MathOperatorImpl extends FallingObject implements MathOperator {

    private MathOperation mathOperation;

    public MathOperatorImpl() {
        super();
        this.getTextLabel().setFont(NUMBER_FONT);
        this.getTextLabel().setTextFill(Color.RED);
    }

    public MathOperation getMathOperation() {
        return this.mathOperation;
    }

    @Override
    protected void setFallingObject() {
        int randomIndex = this.getRandomNumber().nextInt(4);
        for (MathOperation operation : MathOperation.values()) {
            if (randomIndex == operation.ordinal()) {
                this.mathOperation = operation;
            }
        }
        this.getTextLabel().textProperty().bind(new SimpleStringProperty(String.valueOf(this.mathOperation)));
    }
}