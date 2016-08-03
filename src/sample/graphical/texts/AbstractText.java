package sample.graphical.texts;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class AbstractText extends Text {

    private int xPosition;
    private int yPosition;
    private Paint paint;
    private Double opacity;
    private Font font;

    public AbstractText(String text,
                        int xPosition,
                        int yPosition,
                        Paint paint,
                        Font font,
                        Double opacity) {
        super(text);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.paint = paint;
        this.font = font;
        this.opacity = opacity;
        this.initializeValues();
    }

    private   void initializeValues() {
        this.setTranslateX(this.xPosition);
        this.setTranslateY(this.yPosition);
        this.setFill(this.paint);
        this.setFont(this.font);
        this.setOpacity(this.opacity);
    }
}
