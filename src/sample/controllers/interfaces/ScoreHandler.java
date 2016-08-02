package sample.controllers.interfaces;

import javafx.beans.property.LongProperty;
import sample.controllers.MathOperation;
import sample.models.interfaces.Fallable;

public interface ScoreHandler {

    void handleScore(Fallable fallable, MathOperation currentOperation, LongProperty score);

}
