package sample.constants;

import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.models.menumodels.Item;

public class MenuConstants {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    public static final String BACKGROUND_PATH = "../resources/menuWallpaper.jpg";
    public static final int CREDITS_ANIMATION_DROP_POINT = 430;
    public static final Font MENU_FONT = Font.font("", FontWeight.BOLD, 18);


    public static final Font NUMBER_FONT = Font.font("Consolas", FontWeight.BLACK, 20);
    public static final Font CREDIT_FONT = Font.font("Calibri", FontWeight.NORMAL, 22);  ;
    public static final Font HELP_FONT = Font.font("Consolas", FontWeight.BLACK, 18);
    public static final Font GAME_FONT = Font.font("Consolas", FontWeight.SEMI_BOLD, 22); ;


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

    public static Node backButton() {
        return new Item("BACK TO MENU", "MENU");
    }
}
