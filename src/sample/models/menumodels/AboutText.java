package sample.models.menumodels;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.constants.Constants;

public class AboutText extends Text {

    public AboutText(String text) {
        super(text);
        this.setTranslateX(120);
        this.setTranslateY(70);
        this.setFill(Color.DARKRED);
        this.setFont(Constants.ABOUT_FONT);
        this.setOpacity(1.5);
    }
}
