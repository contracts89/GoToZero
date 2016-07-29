package sample.interfaces;

import javafx.beans.property.LongProperty;
import javafx.scene.layout.Pane;
import sample.models.playmodels.Player;
import sample.stages.PlayState;

import java.util.List;

public interface CollisionDetectable {
    void checkForCollisionWithNumbers(List<Fallable> fallingObjects,
                                      Player player, Pane pane,
                                      String currentOperation,
                                      LongProperty score);

    void checkForCollisionWithOperators(List<MathOperatorImpl> mathOperators,
                                        Player player,
                                        Pane pane,
                                        PlayState playState);
}
