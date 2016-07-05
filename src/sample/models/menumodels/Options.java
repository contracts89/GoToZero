package sample.models.menumodels;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class Options extends VBox {

    public Options(String menu, Node... nodes) {
        super(10, nodes);
        this.setMenuPosition(menu);
    }

    private void setMenuPosition(String menuType) {
        switch (menuType) {
            case "Menu":
                this.setAlignment(Pos.TOP_CENTER);
                this.setTranslateX(140);
                this.setTranslateY(240);
                break;
            case "Credits":
                this.setAlignment(Pos.BASELINE_CENTER);
                this.setTranslateX(50);
                this.setTranslateY(150);
                break;
            case "Back button":
                this.setAlignment(Pos.TOP_LEFT);
                this.setTranslateX(140);
                this.setTranslateY(360);
                break;
            case "Help":
                this.setAlignment(Pos.CENTER);
                this.setTranslateX(50);
                this.setTranslateY(150);
                break;
        }
    }

    public Item getItem(int index) {
        return (Item) this.getChildren().get(index);
    }
}
