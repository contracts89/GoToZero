package sample.collisions;

import javafx.beans.property.LongProperty;
import javafx.scene.layout.Pane;
import sample.models.playmodels.FallingObject;
import sample.models.playmodels.MathOperator;
import sample.models.playmodels.Player;
import sample.stages.PlayState;

import java.util.Collections;
import java.util.List;

public class CollisionDetector {
    private static int collidedObjects = 0;

    public static int getCollidedObjects() {
        return collidedObjects;
    }

    public static void setCollidedObjects(int collidedObjects) {
        CollisionDetector.collidedObjects = collidedObjects;
    }

    public void checkForCollisionWithNumbers(List<FallingObject> fallingObjects,
                                             Player player, Pane pane,
                                             String currentOperation,
                                             LongProperty score) {
        for (int i = 0; i < fallingObjects.size(); i++) {
            if (fallingObjects.get(i).getTextLabel().getBoundsInParent().intersects(player.getBoundsInParent())) {
                pane.getChildren().remove(fallingObjects.get(i));
                switch (currentOperation) {
                    case "Divide":

                        //Catch the impossible operation Devide to Zero
                        try {
                            score.set(score.get() / (fallingObjects.get(i).getNumberScore()));
                            break;
                        } catch (ArithmeticException ex) {
                            score.set(999999999);
                        }
                    case "Add":
                        score.set(score.get() + (fallingObjects.get(i).getNumberScore()));
                        break;
                    case "Multiply":
                        score.set(score.get() * (fallingObjects.get(i).getNumberScore()));
                        break;
                    case "Subtract":
                        score.set(score.get() - (fallingObjects.get(i).getNumberScore()));
                        break;
                }
                collidedObjects++;
                fallingObjects.removeAll(Collections.singleton(fallingObjects.get(i)));
            }
        }
    }

    public void checkForCollisionWithOperators(List<MathOperator> mathOperators, Player player, Pane pane, PlayState playState) {
        for (int i = 0; i < mathOperators.size(); i++) {
            if (mathOperators.get(i).getTextLabel().getBoundsInParent().intersects(player.getBoundsInParent())) {
                pane.getChildren().remove(mathOperators.get(i));
                switch (mathOperators.get(i).getMathOperator()){
                    case "+": playState.setCurrentOperation("Add");break;
                    case "-": playState.setCurrentOperation("Subtract");break;
                    case "*": playState.setCurrentOperation("Multiply");break;
                    case "/": playState.setCurrentOperation("Divide");break;
                }
                mathOperators.removeAll(Collections.singleton(mathOperators.get(i)));
                collidedObjects++;
            }
        }
    }
}

