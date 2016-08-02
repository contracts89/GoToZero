<<<<<<< HEAD
package sample.constants;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sample.models.menumodels.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Constants {

//    public static final int WIDTH = 900;
//    public static final int HEIGHT = 600;
//    public static final int LEFT_WALL_BOUNDARY = -10;
//    public static final int RIGHT_WALL_BOUNDARY = 850;
//    public static final int VELOCITY = 5;
//    public static final int NUMBER_ANIMATION_DROP_POINT = 850;
//    public static final int CREDITS_ANIMATION_DROP_POINT = 430;
//    public static final double DEFAULT_Y_START_POSITION = 540;
//    public static final double DEFAULT_X_START_POSITION = 500;
    public static final Font NUMBER_FONT;
    public static final Font MENU_FONT;
    public static final Font CREDIT_FONT;
    public static final Font ABOUT_FONT;
    public static final Text ABOUT_TEXT;
    public static final Text CREDITS_BY;
    public static final Font HELP_FONT;
    public static final Text HELP_TEXT;
//    public static final int BOMB_VALUE = 0;
//    public static final String BACKGROUND_PATH = "../resources/menuWallpaper.jpg";
//    public static final String SYMBOLS = "?!&^%$";
//    public static final String MATH_OPERATORS = "+-*/";
    public static final Font GAME_FONT;
    public static final Text SCORE_TEXT;
    public static final Text OPERATION_TEXT;

    static {
        GAME_FONT = Font.font("Consolas", FontWeight.SEMI_BOLD, 22);
        MENU_FONT = Font.font("", FontWeight.BOLD, 18);
        NUMBER_FONT = Font.font("Consolas", FontWeight.BLACK, 20);
        CREDIT_FONT = Font.font("Calibri", FontWeight.NORMAL, 22);
        HELP_FONT = Font.font("Consolas", FontWeight.BLACK, 18);
        ABOUT_FONT = Font.font("Consolas", FontWeight.THIN, 25);
        ABOUT_TEXT = createAboutText();
        CREDITS_BY = creditsByText();
        HELP_TEXT = createHelpText();
        SCORE_TEXT = createScoreAndOpText("score");
        OPERATION_TEXT = createScoreAndOpText("operation");
    }

    public static Node[] menuNodes() {
        return new Node[]{
                new Item("ONE PLAYER", "MENU"),
                new Item("TWO PLAYERS", "MENU"),
                new Item("HIGHSCORE", "MENU"),
                new Item("HELP", "MENU"),
                new Item("CREDITS", "MENU"),
                new Item("EXIT", "MENU")
        };
    }

    public static Node[] creditsText() {
        {
            Node[] arr = new Node[]{
                    new Item("ABELINA GEORGIEVA", "CREDITS"),
                    new Item("EYUB DJELIL", "CREDITS"),
                    new Item("GEORGI GROZEV", "CREDITS"),
                    new Item("KALOYAN NIKOV", "CREDITS"),
                    new Item("LYUBOMIR GOYCHEV", "CREDITS"),
                    new Item("NIKIFOR STOYNOV", "CREDITS"),
                    new Item("PETAR NYAGOLOV", "CREDITS"),
                    new Item("RADOSLAV DIMITROV", "CREDITS"),
                    new Item("VIKTORIA SHTEREVA", "CREDITS"),
                    new Item("TSVETELIN PANTEV", "CREDITS"),
            };
            for (Node node : arr) {
                node.setDisable(true);
            }
            return arr;
        }
    }

    public static Text displayHighScore(String text) {
        Text highScoreText = new Text(text);
        highScoreText.setTranslateX(60);
        highScoreText.setTranslateY(40);
        highScoreText.setFill(Color.ANTIQUEWHITE);
        highScoreText.setFont(Constants.HELP_FONT);
        highScoreText.setOpacity(1.5);
        return highScoreText;
    }

    private static Text creditsByText() {
        Text creditsBy = new Text("CREDITS BY =>\n\nTEAM ANCALOGON");
        creditsBy.setTranslateX(130);
        creditsBy.setTranslateY(250);
        creditsBy.setFill(Color.WHITESMOKE);
        creditsBy.setFont(Constants.MENU_FONT);
        creditsBy.setOpacity(5);
        return creditsBy;
    }

    private static Text createAboutText() {
        Text aboutText = new Text("GoToZero\n\nby Team Ancalogon \u00AE");
        aboutText.setTranslateX(60);
        aboutText.setTranslateY(500);
        aboutText.setFill(Color.DARKRED);
        aboutText.setFont(Constants.ABOUT_FONT);
        aboutText.setOpacity(1.5);
        return aboutText;
    }

    public static Text createHelpText() {
        StringBuilder help = getFilePath();
        Text helpText = new Text(help.toString());
        helpText.setTranslateX(60);
        helpText.setTranslateY(40);
        helpText.setFill(Color.ANTIQUEWHITE);
        helpText.setFont(Constants.HELP_FONT);
        helpText.setOpacity(1.5);
        return helpText;
    }

    private static StringBuilder getFilePath() {
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
        return helpBuilder;
    }

    private static Text createScoreAndOpText(String type) {
        Text tempText = new Text();
        tempText.setFont(Constants.GAME_FONT);
        if (type.equals("score")) {
            tempText.setY(20);
            tempText.setX(5);
        } else {
            tempText.setY(50);
            tempText.setX(5);
        }
        tempText.setFill(Color.WHITESMOKE);
        tempText.setEffect(new DropShadow(1.5, Color.WHITE));
        tempText.setOpacity(1);
        return tempText;
    }

    public static Node backButton() {
        return new Item("BACK TO MENU", "MENU");
    }
}
=======
package sample.constants;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sample.models.menumodels.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Constants {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    public static final Font NUMBER_FONT;
    public static final int VELOCITY = 5;
    public static final int LEFT_WALL_BOUNDARY = -10;
    public static final int RIGHT_WALL_BOUNDARY = 850;
    public static final Font MENU_FONT;
    public static final int NUMBER_ANIMATION_DROP_POINT = 850;
    public static final int CREDITS_ANIMATION_DROP_POINT = 430;
    public static final Font CREDIT_FONT;
    public static final Font ABOUT_FONT;
    public static final double DEFAULT_X_START_POSITION = 500;
    public static final double DEFAULT_Y_START_POSITION = 540;
    public static final Text ABOUT_TEXT;
    public static final Text CREDITS_BY;
    public static final Font HELP_FONT;
    public static final Text HELP_TEXT;
    public static final int BOMB_VALUE = 0;
    public static final String BACKGROUND_PATH = "../resources/menuWallpaper.jpg";
    public static final String SYMBOLS = "?!&^%$";
    public static final String MATH_OPERATORS = "+-*/";
    public static final Font GAME_FONT;
    public static final Text SCORE_TEXT;
    public static final Text OPERATION_TEXT;

    static {
        GAME_FONT = Font.font("Consolas", FontWeight.SEMI_BOLD, 22);
        MENU_FONT = Font.font("", FontWeight.BOLD, 18);
        NUMBER_FONT = Font.font("Consolas", FontWeight.BLACK, 20);
        CREDIT_FONT = Font.font("Calibri", FontWeight.NORMAL, 22);
        HELP_FONT = Font.font("Consolas", FontWeight.BLACK, 18);
        ABOUT_FONT = Font.font("Consolas", FontWeight.THIN, 25);
        ABOUT_TEXT = createAboutText();
        CREDITS_BY = creditsByText();
        HELP_TEXT = createHelpText();
        SCORE_TEXT = createScoreAndOpText("score");
        OPERATION_TEXT = createScoreAndOpText("operation");
    }

    public static Node[] menuNodes() {
        return new Node[]{
                new Item("ONE PLAYER", "MENU"),
                new Item("TWO PLAYERS", "MENU"),
                new Item("HIGHSCORE", "MENU"),
                new Item("HELP", "MENU"),
                new Item("CREDITS", "MENU"),
                new Item("EXIT", "MENU")
        };
    }

    public static Node[] creditsText() {
        {
            Node[] arr = new Node[]{
                    new Item("ABELINA GEORGIEVA", "CREDITS"),
                    new Item("EYUB DJELIL", "CREDITS"),
                    new Item("GEORGI GROZEV", "CREDITS"),
                    new Item("KALOYAN NIKOV", "CREDITS"),
                    new Item("LYUBOMIR GOYCHEV", "CREDITS"),
                    new Item("NIKIFOR STOYNOV", "CREDITS"),
                    new Item("PETAR NYAGOLOV", "CREDITS"),
                    new Item("RADOSLAV DIMITROV", "CREDITS"),
                    new Item("VIKTORIA SHTEREVA", "CREDITS"),
                    new Item("TSVETELIN PANTEV", "CREDITS"),
            };
            for (Node node : arr) {
                node.setDisable(true);
            }
            return arr;
        }
    }

    public static Text displayHighScore(String text) {
        Text highScoreText = new Text(text);
        highScoreText.setTranslateX(60);
        highScoreText.setTranslateY(40);
        highScoreText.setFill(Color.ANTIQUEWHITE);
        highScoreText.setFont(Constants.HELP_FONT);
        highScoreText.setOpacity(1.5);
        return highScoreText;
    }

    private static Text creditsByText() {
        Text creditsBy = new Text("CREDITS BY =>\n\nTEAM ANCALOGON");
        creditsBy.setTranslateX(130);
        creditsBy.setTranslateY(250);
        creditsBy.setFill(Color.WHITESMOKE);
        creditsBy.setFont(Constants.MENU_FONT);
        creditsBy.setOpacity(5);
        return creditsBy;
    }

    private static Text createAboutText() {
        Text aboutText = new Text("GoToZero\n\nby Team Ancalogon \u00AE");
        aboutText.setTranslateX(60);
        aboutText.setTranslateY(500);
        aboutText.setFill(Color.DARKRED);
        aboutText.setFont(Constants.ABOUT_FONT);
        aboutText.setOpacity(1.5);
        return aboutText;
    }

    public static Text createHelpText() {
        StringBuilder help = getFilePath();
        Text helpText = new Text(help.toString());
        helpText.setTranslateX(60);
        helpText.setTranslateY(40);
        helpText.setFill(Color.ANTIQUEWHITE);
        helpText.setFont(Constants.HELP_FONT);
        helpText.setOpacity(1.5);
        return helpText;
    }

    private static StringBuilder getFilePath() {
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
        return helpBuilder;
    }

    private static Text createScoreAndOpText(String type) {
        Text tempText = new Text();
        tempText.setFont(Constants.GAME_FONT);
        if (type.equals("score")) {
            tempText.setY(20);
            tempText.setX(5);
        } else {
            tempText.setY(50);
            tempText.setX(5);
        }
        tempText.setFill(Color.WHITESMOKE);
        tempText.setEffect(new DropShadow(1.5, Color.WHITE));
        tempText.setOpacity(1);
        return tempText;
    }

    public static Node backButton() {
        return new Item("BACK TO MENU", "MENU");
    }
}
>>>>>>> 3fcd8582235d14e95e9f63e689b45d21e27c74fe
