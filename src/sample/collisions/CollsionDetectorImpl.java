package sample.collisions;

import javafx.beans.property.LongProperty;
import sample.collisions.interfaces.CollisionDetector;
import sample.controllers.MathOperation;
import sample.controllers.interfaces.OperatorSwitcher;
import sample.controllers.interfaces.ScoreHandler;
import sample.models.interfaces.Fallable;
import sample.models.interfaces.MathOperator;
import sample.models.playmodels.FallingObject;
import sample.models.playmodels.Player;
import sample.stages.PlayState;

import java.util.Collections;
import java.util.List;

public class CollsionDetectorImpl implements CollisionDetector {

    private ScoreHandler scoreHandler;
    private OperatorSwitcher operatorSwitcher;
    private int collidedObjects = 0;

    public CollsionDetectorImpl(ScoreHandler scoreHandler, OperatorSwitcher operatorSwitcher) {
        this.scoreHandler = scoreHandler;
        this.operatorSwitcher = operatorSwitcher;
    }

    @Override
    public int getCollidedObjectsCount() {
        return this.collidedObjects;
    }

    @Override
    public Fallable returnCollidedObject(List<Fallable> fallables,
                                         Player player,
                                         MathOperation currentOperation,
                                         LongProperty score,
                                         PlayState playState) {
        for (int index = 0; index < fallables.size(); index++) {
            FallingObject currentObject = (FallingObject) fallables.get(index);
            if (currentObject.getTextLabel().getBoundsInParent().intersects(player.getBoundsInParent())) {
                if (currentObject instanceof MathOperator) {
                    this.operatorSwitcher.switchOperator((MathOperator) currentObject, playState);
                    return currentObject;
                }
                this.collidedObjects++;
                this.scoreHandler.handleScore(currentObject, currentOperation, score);
                fallables.removeAll(Collections.singleton(currentObject));
                return currentObject;
            }
        }
        return null;
    }

}

