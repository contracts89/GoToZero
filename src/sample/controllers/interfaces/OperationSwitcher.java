package sample.controllers.interfaces;

import sample.controllers.MathOperation;
import sample.models.interfaces.MathOperator;

public interface OperationSwitcher {

    void switchOperator(MathOperator mathOperator);

    MathOperation getMathOperation();
}
