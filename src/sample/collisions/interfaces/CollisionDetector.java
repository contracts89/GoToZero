package sample.collisions.interfaces;

import javafx.beans.property.LongProperty;
import sample.controllers.MathOperation;
import sample.models.interfaces.Fallable;
import sample.models.playmodels.PlayerImpl;

import java.util.List;

public interface CollisionDetector {


    Fallable returnCollidedObject(List<Fallable> fallables, PlayerImpl player, MathOperation currentOperation,
                                  LongProperty score);

    int getCollidedObjectsCount();
}
