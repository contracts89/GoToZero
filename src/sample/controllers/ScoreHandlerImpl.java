package sample.controllers;

import javafx.beans.property.LongProperty;
import sample.controllers.interfaces.ScoreHandler;
import sample.models.interfaces.Fallable;
import sample.models.playmodels.FallingObject;

public class ScoreHandlerImpl implements ScoreHandler {


    @Override
    public void handleScore(Fallable fallable,
                            String currentOperation,
                            LongProperty score) {
            FallingObject fallableWithScore = (FallingObject) fallable;
            switch (currentOperation) {
                case "Divide":
                    //Catch the impossible operation Divide to Zero
                    try {
                        score.set(score.get() / fallableWithScore.getNumberScore());
                        break;
                    } catch (ArithmeticException ex) {
                        score.set(999999999);
                    }
                case "Add":
                    score.set(score.get() + fallableWithScore.getNumberScore());
                    break;
                case "Multiply":
                    score.set(score.get() * fallableWithScore.getNumberScore());
                    break;
                case "Subtract":
                    score.set(score.get() - fallableWithScore.getNumberScore());
                    break;
            }

        }
    }

