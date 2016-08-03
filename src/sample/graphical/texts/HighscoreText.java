package sample.graphical.texts;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.output.HighscoreManager;

public class HighscoreText extends AbstractText {


    private static final Font HIGHSCORE_FONT = Font.font("Consolas", FontWeight.THIN, 25);
    private static final String HIGHSCORE_TEXT = HighscoreManager.getScores();
    private static final int HIGHSCORE_X = 60;
    private static final int HIGHSCORE_Y = 40;
    private static final Paint HIGHSCORE_PAINT = Color.ANTIQUEWHITE;
    private static final double HIGHSCORE_OPACITY = 1.5;

    public HighscoreText() {
        super(HIGHSCORE_TEXT, HIGHSCORE_X, HIGHSCORE_Y, HIGHSCORE_PAINT, HIGHSCORE_FONT, HIGHSCORE_OPACITY);
    }
}
