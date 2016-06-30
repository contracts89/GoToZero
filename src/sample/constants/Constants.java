package sample.constants;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by KNikov on 30/06/2016.
 */
public class Constants {

    public static final Font NUMBER_FONT;
    public static final int VELOCITY;
    public static final int LEFT_WALL_BOUNDARY;
    public static final int RIGHT_WALL_BOUNDARY;
    public static final Font MENU_FONT;
    static {
        NUMBER_FONT = Font.font("Times New Roman", FontWeight.BLACK, 22);
        VELOCITY = 5;
        LEFT_WALL_BOUNDARY = -10;
        RIGHT_WALL_BOUNDARY = 850;
        MENU_FONT = Font.font("", FontWeight.BOLD, 18);
    }
}
