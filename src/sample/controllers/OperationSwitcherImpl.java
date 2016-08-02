package sample.controllers;

import sample.controllers.interfaces.OperationSwitcher;
import sample.models.interfaces.MathOperator;

public class OperationSwitcherImpl implements OperationSwitcher {

    MathOperation mathOperation;

    public OperationSwitcherImpl() {
        this.mathOperation = MathOperation.SUBTRACT;
    }

    @Override
    public void switchOperator(MathOperator mathOperator) {
        MathOperation toBeSwitched = mathOperator.getMathOperation();
        this.mathOperation = toBeSwitched;
    }

    @Override
    public MathOperation getMathOperation() {
        return this.mathOperation;
    }


}
