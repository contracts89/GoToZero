package sample.collisions.interfaces;

import javafx.beans.property.LongProperty;
import sample.models.interfaces.Fallable;
import sample.models.playmodels.Player;

import java.util.List;

public interface CollisionDetector {



    Fallable returnCollidedObject(List<Fallable> fallables, Player player,String currentOperation,
                                  LongProperty score);

    int getCollidedObjectsCount();
}
