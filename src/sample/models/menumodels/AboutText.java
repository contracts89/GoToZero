package sample.models.menumodels;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.constants.Constants;

public class AboutText extends Text {

    public AboutText(String text) {
        super(text);
        this.setTranslateX(320);
        this.setTranslateY(570);
        this.setFill(Color.DARKRED);
        this.setFont(Constants.MENU_FONT);
        this.setOpacity(5);
    }
}
