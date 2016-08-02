package sample.controllers;

import sample.controllers.interfaces.OperatorSwitcher;
import sample.models.interfaces.MathOperator;
import sample.stages.PlayState;

public class OperatorSwitcherImpl implements OperatorSwitcher {

    @Override
    public void switchOperator(MathOperator mathOperator, PlayState playState) {
        MathOperation toBeSwitched = mathOperator.getMathOperation();
        playState.setCurrentOperation(toBeSwitched);
    }
}
