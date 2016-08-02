package sample.controllers.interfaces;

import javafx.beans.property.LongProperty;
import sample.models.interfaces.Fallable;

public interface ScoreHandler {

    void handleScore(Fallable fallable, String currentOperation, LongProperty score);

}
