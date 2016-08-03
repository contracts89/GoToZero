package sample.models.playmodels;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import sample.constants.Constants;

import static sample.constants.PlayStateConstants.SYMBOLS;

public class Symbol extends FallingObject {

    private String generatedSymbol;

    public Symbol() {
        super();
        Constants constants = new Constants();
        this.setNumberScore(this.getGeneratedNum());
        this.getTextLabel().setFont(constants.getNUMBER_FONT());
        this.getTextLabel().setTextFill(Color.AQUAMARINE);
    }

    @Override
    protected void setFallingObject() {
        this.setGeneratedNum(this.getRandomNumber().nextInt(10) + 10);

        this.generatedSymbol = String.valueOf(SYMBOLS.charAt(this.getRandomNumber().nextInt(6)));

        this.getTextLabel().textProperty().bind(new SimpleStringProperty(this.generatedSymbol));
    }
}