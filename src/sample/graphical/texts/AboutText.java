package sample.graphical.texts;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AboutText extends AbstractText {

    private static final Font ABOUT_FONT = Font.font("Consolas", FontWeight.THIN, 25);
    private static final String ABOUT_TEXT = "GoToZero\n\nby Team Ancalogon \u00AE";
    private static final int ABOUT_X = 60;
    private static final int ABOUT_Y = 500;
    private static final Paint ABOUT_PAINT = Color.DARKRED;
    private static final double ABOUT_OPACITY = 1.5;

    public AboutText() {
        super(ABOUT_TEXT, ABOUT_X, ABOUT_Y, ABOUT_PAINT, ABOUT_FONT, ABOUT_OPACITY);
    }
}
