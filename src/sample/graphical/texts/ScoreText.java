package sample.graphical.texts;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static sample.constants.MenuConstants.GAME_FONT;

public class ScoreText extends AbstractText {

    private static final String SCORE_TEXT = "score";
    private static final int SCORE_X = 5;
    private static final int SCORE_Y = 20;
    private static final Paint SCORE_PAINT = Color.WHITESMOKE;
    private static final double SCORE_OPACITY = 1;

    public ScoreText() {
        super(SCORE_TEXT, SCORE_X, SCORE_Y, SCORE_PAINT, GAME_FONT, SCORE_OPACITY);
    }
}
