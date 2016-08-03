package sample.stages;


import javafx.scene.Node;
import sample.models.menumodels.Item;

public class OptionsCreator {

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

}
