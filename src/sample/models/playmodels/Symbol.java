package sample.models.playmodels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import sample.constants.Constants;

public class Symbol extends FallingObject {

    //    final String symbols = "*&^%$";
    private String generatedSymbol;

    public Symbol() {
        super();
        this.getTextLabel().setFont(Constants.NUMBER_FONT);
        this.getTextLabel().setTextFill(Color.AQUAMARINE);
    }

    @Override
    protected void setFallingObject() {
        this.setGeneratedNum(this.getRandomNumber().nextInt(10) + 10);

        this.getTextLabel().textProperty().bind(new SimpleIntegerProperty(Constants.BOMB_VALUE).asString());
        this.generatedSymbol = String.valueOf(Constants.SYMBOLS.charAt(this.getRandomNumber().nextInt(6)));
        if (this.getGeneratedNum() == 20) {
            this.setGeneratedNum(Constants.BOMB_VALUE);
            this.getTextLabel().textProperty().bind(new SimpleIntegerProperty(Constants.BOMB_VALUE).asString());
        } else {
            this.getTextLabel().textProperty().bind(new SimpleStringProperty(this.generatedSymbol));
        }
    }
}
