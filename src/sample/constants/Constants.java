package sample.constants;

import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.models.menumodels.Item;


public class Constants {

    public static final int WIDTH;
    public static final int HEIGHT;
    public static final Font NUMBER_FONT;
    public static final int VELOCITY;
    public static final int LEFT_WALL_BOUNDARY;
    public static final int RIGHT_WALL_BOUNDARY;
    public static final Font MENU_FONT;
    public static final int NUMBER_ANIMATION_DROP_POINT;
    public static final int CREDITS_ANIMATION_DROP_POINT;
    public static final Font CREDIT_FONT;
    public static final Font ABOUT_FONT;
    static {
        WIDTH = 900;
        HEIGHT = 600;
        NUMBER_FONT = Font.font("Times New Roman", FontWeight.BLACK, 22);
        ABOUT_FONT = Font.font("Consolas", FontWeight.THIN, 25);
        CREDIT_FONT = Font.font("Calibri", FontWeight.NORMAL, 25);
        VELOCITY = 5;
        LEFT_WALL_BOUNDARY = -10;
        RIGHT_WALL_BOUNDARY = 850;
        MENU_FONT = Font.font("", FontWeight.BOLD, 18);
        NUMBER_ANIMATION_DROP_POINT = 850;
        CREDITS_ANIMATION_DROP_POINT = 430;
    }

    public static  Node[] menuNodes() {
        return new Node[]{
                new Item("ONE PLAYER","MENU"),
                new Item("TWO PLAYERS","MENU"),
                new Item("HIGHSCORE","MENU"),
                new Item("HELP","MENU"),
                new Item("CREDITS","MENU"),
                new Item("EXIT","MENU")
        };
    }

    public static  Node[] creditsText() {
        Node[] arr = new Node[]{
                new Item("ABELINA GEORGIEVA","CREDITS"),
                new Item("EYUB DJELIL","CREDITS"),
                new Item("GEORGI GROZEV","CREDITS"),
                new Item("KALOYAN NIKOV","CREDITS"),
                new Item("LYUBOMIR GOYCHEV","CREDITS"),
                new Item("NIKIFOR STOYNOV","CREDITS"),
                new Item("PETAR NYAGOLOV","CREDITS"),
                new Item("RADOSLAV DIMITROV","CREDITS"),
                new Item("VIKTORIA SHTEREVA","CREDITS"),
                new Item("TSVETELIN PANTEV","CREDITS"),
        };
        for (Node node : arr) {
            node.setDisable(true);
        }
        return arr;
    }

    public static  Node backButton() {
        return new Item("BACK TO MENU","MENU");
    }
}
