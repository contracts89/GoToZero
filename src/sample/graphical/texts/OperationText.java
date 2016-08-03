package sample.graphical.texts;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static sample.constants.MenuConstants.GAME_FONT;

public class OperationText extends AbstractText {

    private static final String OPERATION_TEXT = "operation";
    private static final int OPERATION_X = 5;
    private static final int OPERATION_Y = 50;
    private static final Paint OPERATION_PAINT = Color.WHITESMOKE;
    private static final double OPERATION_OPACITY = 1;

    public OperationText() {
        super(OPERATION_TEXT, OPERATION_X, OPERATION_Y, OPERATION_PAINT, GAME_FONT, OPERATION_OPACITY);
    }
}

