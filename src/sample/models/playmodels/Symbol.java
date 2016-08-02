<<<<<<< HEAD
package sample.models.playmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import sample.constants.Constants;

import static sample.constants.PlayStateConstants.SYMBOLS;

public class Symbol extends FallingObject {

    private String generatedSymbol;

    public Symbol() {
        super();
        this.setNumberScore(this.getGeneratedNum());
        this.getTextLabel().setFont(Constants.NUMBER_FONT);
        this.getTextLabel().setTextFill(Color.AQUAMARINE);
    }

    @Override
    protected void setFallingObject() {
        this.setGeneratedNum(this.getRandomNumber().nextInt(10) + 10);

        this.generatedSymbol = String.valueOf(SYMBOLS.charAt(this.getRandomNumber().nextInt(6)));

        this.getTextLabel().textProperty().bind(new SimpleStringProperty(this.generatedSymbol));
    }
}

=======
package sample.models.playmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import sample.constants.Constants;

public class Symbol extends FallingObject {

    private String generatedSymbol;

    public Symbol() {
        super();
        this.setNumberScore(this.getGeneratedNum());
        this.getTextLabel().setFont(Constants.NUMBER_FONT);
        this.getTextLabel().setTextFill(Color.AQUAMARINE);
    }

    @Override
    protected void setFallingObject() {
        this.setGeneratedNum(this.getRandomNumber().nextInt(10) + 10);

        this.generatedSymbol = String.valueOf(Constants.SYMBOLS.charAt(this.getRandomNumber().nextInt(6)));

        this.getTextLabel().textProperty().bind(new SimpleStringProperty(this.generatedSymbol));
    }
}

>>>>>>> 3fcd8582235d14e95e9f63e689b45d21e27c74fe
