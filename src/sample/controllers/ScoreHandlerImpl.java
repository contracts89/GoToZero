package sample.controllers;

import javafx.beans.property.LongProperty;
import sample.controllers.interfaces.ScoreHandler;
import sample.models.interfaces.Fallable;
import sample.models.playmodels.FallingObject;

public class ScoreHandlerImpl implements ScoreHandler {


    @Override
    public void handleScore(Fallable fallable,
                            MathOperation currentOperation,
                            LongProperty score) {
            FallingObject fallableWithScore = (FallingObject) fallable;
            switch (currentOperation) {
                case DIVIDE:
                    //Catch the impossible operation Divide to Zero
                    try {
                        score.set(score.get() / fallableWithScore.getNumberScore());
                        break;
                    } catch (ArithmeticException ex) {
                        score.set(999999999);
                    }
                case ADD:
                    score.set(score.get() + fallableWithScore.getNumberScore());
                    break;
                case MULTIPLY:
                    score.set(score.get() * fallableWithScore.getNumberScore());
                    break;
                case SUBTRACT:
                    score.set(score.get() - fallableWithScore.getNumberScore());
                    break;
            }

        }
    }

