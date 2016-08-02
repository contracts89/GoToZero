package sample.controllers.interfaces;

import sample.models.interfaces.MathOperator;
import sample.stages.PlayState;

public interface OperatorSwitcher {

    void switchOperator(MathOperator mathOperator, PlayState playState);

}
