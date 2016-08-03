package sample.graphical.texts;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static sample.constants.MenuConstants.MENU_FONT;

public class CreditText extends AbstractText {

    private static final int CREDIT_X_POSITION = 130;
    private static final int CREDIT_Y_POSITION = 250;
    private static final Paint CREDIT_PAINT = Color.WHITESMOKE;
    private static final double CREDIT_OPACITY = 5;

    private static final String CREDIT_TEXT = "CREDITS BY =>\n\nTEAM ANCALOGON";

    public CreditText() {
        super(CREDIT_TEXT, CREDIT_X_POSITION, CREDIT_Y_POSITION, CREDIT_PAINT, MENU_FONT, CREDIT_OPACITY);
    }
}
