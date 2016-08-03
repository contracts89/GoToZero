
package sample.models.playmodels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;

import static sample.constants.MenuConstants.NUMBER_FONT;
import static sample.constants.PlayStateConstants.BOMB_VALUE;

public class Number extends FallingObject {

    public Number() {
        super();
        this.setNumberScore(this.getGeneratedNum());
        this.getTextLabel().setFont(NUMBER_FONT);
        this.getTextLabel().setTextFill(Color.WHITESMOKE);
    }

    @Override
    protected void setFallingObject() {
        //generate numbers from 1 to 10
        this.setGeneratedNum(this.getRandomNumber().nextInt(20) + 1);

        if (this.getGeneratedNum() == 20) {
            this.setGeneratedNum(BOMB_VALUE);
            this.getTextLabel().textProperty().bind(new SimpleIntegerProperty(BOMB_VALUE).asString());
        }

        if (this.getGeneratedNum() < 11) {
            this.getTextLabel().textProperty().bind(new SimpleIntegerProperty(this.getGeneratedNum()).asString());
            this.setNumberScore(this.getGeneratedNum());
        }
    }
}