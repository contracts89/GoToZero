package sample.models.playmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import sample.constants.Constants;
import sample.controllers.MathOperation;
import sample.models.interfaces.MathOperator;

public class MathOperatorImpl extends FallingObject implements MathOperator {

    private MathOperation mathOperation;

    public MathOperatorImpl() {
        super();
        Constants constants = new Constants();
        this.getTextLabel().setFont(constants.getNUMBER_FONT());
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