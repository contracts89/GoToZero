package sample.graphical.texts;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static sample.constants.MenuConstants.GAME_FONT;

public class ObjectsTakenText extends AbstractText {

    private static final String FALLEN_BLANK_STRING = ""; // the text is set dynamically
    private static final int FALLEN_X = 5;
    private static final int FALLEN_Y = 80;
    private static final Paint FALLEN_PAINT = Color.YELLOW;
    private static final double FALLEN_OPACITY = 5;

    public ObjectsTakenText() {
        super(FALLEN_BLANK_STRING, FALLEN_X, FALLEN_Y, FALLEN_PAINT, GAME_FONT, FALLEN_OPACITY);
    }
}
