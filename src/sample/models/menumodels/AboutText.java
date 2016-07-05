package sample.models.menumodels;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.constants.Constants;

public class AboutText extends Text {

    public AboutText(String text) {
        super(text);
        this.setTranslateX(60);
        this.setTranslateY(500);
        this.setFill(Color.DARKRED);
        this.setFont(Constants.ABOUT_FONT);
        this.setOpacity(1.5);
    }
}
