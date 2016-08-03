package sample.graphical.texts;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static sample.constants.MenuConstants.HELP_FONT;


public class HelpText extends AbstractText {


    private static final int HELP_X_POSITION = 60;
    private static final int HELP_Y_POSITION = 40;
    private static final Paint HELP_PAINT = Color.ANTIQUEWHITE;
    private static final double HELP_OPACITY = 1.5;


    public HelpText() {
        super(getFilePath(), HELP_X_POSITION, HELP_Y_POSITION, HELP_PAINT, HELP_FONT, HELP_OPACITY);
    }


    private static String getFilePath() {
        StringBuilder helpBuilder = new StringBuilder();
        File file = new File("src/sample/resources/help.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                helpBuilder.append(line);
                helpBuilder.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return helpBuilder.toString();
    }
}
