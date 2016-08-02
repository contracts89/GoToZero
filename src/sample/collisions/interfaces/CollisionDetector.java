package sample.collisions.interfaces;

import javafx.beans.property.LongProperty;
import javafx.scene.layout.Pane;
import sample.models.playmodels.FallingObject;
import sample.models.playmodels.MathOperatorImpl;
import sample.models.playmodels.Player;
import sample.stages.PlayState;

import java.util.List;

public interface CollisionDetector {
    void checkForCollisionWithNumbers(List<FallingObject> fallingObjects,
                                      Player player, Pane pane,
                                      String currentOperation,
                                      LongProperty score);

    void checkForCollisionWithOperators(List<MathOperatorImpl> mathOperators,
                                        Player player,
                                        Pane pane,
                                        PlayState playState);
}
