package sample.models.menumodels;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class MenuOptions extends VBox {

    public MenuOptions() {
        super(10,
                new MenuItem("ONE PLAYER"),
                new MenuItem("TWO PLAYERS"),
                new MenuItem("HIGHSCORE"),
                new MenuItem("HELP"),
                new MenuItem("CREDITS"),
                new MenuItem("EXIT"));
        this.setAlignment(Pos.TOP_CENTER);
        this.setTranslateX(360);
        this.setTranslateY(300);

    }
    public MenuItem getMenuItem(int index) {
        return (MenuItem) this.getChildren().get(index);
    }
}
