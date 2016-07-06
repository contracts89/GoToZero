package sample.collisions;

import javafx.beans.property.LongProperty;
import javafx.scene.layout.Pane;
import sample.models.playmodels.Number;
import sample.models.playmodels.Player;

import java.util.Collections;
import java.util.List;

public class CollisionDetector {

    public void checkForCollisionWithNumbers(List<Number> numberList,
                                             Player player, Pane pane,
                                             String currentOperation,
                                             LongProperty score) {
        for (int i = 0; i < numberList.size(); i++) {
            if (numberList.get(i).getTextLabel().getBoundsInParent().intersects(player.getBoundsInParent())) {
                pane.getChildren().remove(numberList.get(i));
                switch (currentOperation) {
                    case "Divide":

                        //Catch the impossible operation Devide to Zero
                        try {
                            score.set(score.get() / (numberList.get(i).getNumberScore()));
                            break;
                        } catch (ArithmeticException ex) {
                            score.set(999999999);
                        }
                    case "Add":
                        score.set(score.get() + (numberList.get(i).getNumberScore()));
                        break;
                    case "Multiply":
                        score.set(score.get() * (numberList.get(i).getNumberScore()));
                        break;
                    case "Subtract":
                        score.set(score.get() - (numberList.get(i).getNumberScore()));
                        break;
                }
                numberList.removeAll(Collections.singleton(numberList.get(i)));
            }
        }
    }
}
