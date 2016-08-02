package sample.collisions;

import javafx.beans.property.LongProperty;
import sample.collisions.interfaces.CollisionDetector;
import sample.models.interfaces.Fallable;
import sample.models.interfaces.MathOperator;
import sample.controllers.interfaces.ScoreHandler;
import sample.models.playmodels.FallingObject;
import sample.models.playmodels.Player;

import java.util.Collections;
import java.util.List;

public class CollsionDetectorImpl implements CollisionDetector {

    private ScoreHandler scoreHandler;
    private int collidedObjects = 0;

    public CollsionDetectorImpl(ScoreHandler scoreHandler) {
        this.scoreHandler = scoreHandler;
    }

//
//    public void checkForCollisionWithNumbers(List<FallingObject> fallingObjects,
//                                             Player player, Pane pane,
//                                             String currentOperation,
//                                             LongProperty score) {
//        for (int i = 0; i < fallingObjects.size(); i++) {
//            if (fallingObjects.get(i).getTextLabel().getBoundsInParent().intersects(player.getBoundsInParent())) {
//                pane.getChildren().remove(fallingObjects.get(i));
//                switch (currentOperation) {
//                    case "Divide":
//
//                        //Catch the impossible operation Devide to Zero
//                        try {
//                            score.set(score.get() / (fallingObjects.get(i).getNumberScore()));
//                            break;
//                        } catch (ArithmeticException ex) {
//                            score.set(999999999);
//                        }
//                    case "Add":
//                        score.set(score.get() + (fallingObjects.get(i).getNumberScore()));
//                        break;
//                    case "Multiply":
//                        score.set(score.get() * (fallingObjects.get(i).getNumberScore()));
//                        break;
//                    case "Subtract":
//                        score.set(score.get() - (fallingObjects.get(i).getNumberScore()));
//                        break;
//                }
//                collidedObjects++;
//                fallingObjects.removeAll(Collections.singleton(fallingObjects.get(i)));
//            }
//        }
//    }
//
//    public void checkForCollisionWithOperators(List<MathOperatorImpl> mathOperators, Player player, Pane pane,
//                                               PlayState playState) {
//        for (int i = 0; i < mathOperators.size(); i++) {
//            if (mathOperators.get(i).getTextLabel().getBoundsInParent().intersects(player.getBoundsInParent())) {
//                pane.getChildren().remove(mathOperators.get(i));
//                switch (mathOperators.get(i).getMathOperator()) {
//                    case "+":
//                        playState.setCurrentOperation("Add");
//                        break;
//                    case "-":
//                        playState.setCurrentOperation("Subtract");
//                        break;
//                    case "*":
//                        playState.setCurrentOperation("Multiply");
//                        break;
//                    case "/":
//                        playState.setCurrentOperation("Divide");
//                        break;
//                }
//                mathOperators.removeAll(Collections.singleton(mathOperators.get(i)));
//                //collidedObjects++; // don't count the math operators to current count
//            }
//        }
//    }

    @Override
    public int getCollidedObjectsCount() {
        return this.collidedObjects;
    }

    @Override
    public Fallable returnCollidedObject(List<Fallable> fallables,
                                         Player player,
                                         String currentOperation,
                                         LongProperty score) {
        for (int index = 0; index < fallables.size(); index++) {
            FallingObject currentObject = (FallingObject) fallables.get(index);
            if (currentObject.getTextLabel().getBoundsInParent().intersects(player.getBoundsInParent())) {
                if (currentObject instanceof MathOperator) {
                    //TODO operatorhandler change operation
                    continue;
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

