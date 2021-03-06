package sample.models.menumodels;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class Options extends VBox {

    public Options(String menu, Node... nodes) {
        super(10, nodes);
        this.setMenuPosition(menu);
    }
// All options for click: "Menu","Back button" and "Credits",and shows where
// must draw

    private void setMenuPosition(String menuType) {
        switch (menuType) {
            case "Menu":
                this.setAlignment(Pos.TOP_CENTER);
                this.setTranslateX(140);
                this.setTranslateY(240);
                break;
            case "CreditsStage":
                this.setAlignment(Pos.BASELINE_CENTER);
                this.setTranslateX(50);
                this.setTranslateY(150);
                break;
            case "Back button":
                this.setAlignment(Pos.TOP_LEFT);
                this.setTranslateX(120);
                this.setTranslateY(450);
                break;
            case "HelpStage":
                this.setAlignment(Pos.CENTER);
                this.setTranslateX(50);
                this.setTranslateY(110);
                break;
        }
    }

    public Item getItem(int index) {
        return (Item) this.getChildren().get(index);
    }
}
