package sample.collisions.interfaces;

import javafx.beans.property.LongProperty;
import sample.controllers.MathOperation;
import sample.models.interfaces.Fallable;
import sample.models.playmodels.Player;
import sample.stages.PlayState;

import java.util.List;

public interface CollisionDetector {


    Fallable returnCollidedObject(List<Fallable> fallables, Player player, MathOperation currentOperation,
                                  LongProperty score, PlayState playState);

    int getCollidedObjectsCount();
}
