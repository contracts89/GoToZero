package sample.input;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.menumodels.MenuOptions;
import sample.stages.Singleplayer;

public class MenuHandler {

    private Scene scene;
    private MenuOptions menu;
    public int currentItem;

    public MenuHandler(Scene scene, MenuOptions menuoptions) {
        this.scene = scene;
        this.menu = menuoptions;
        this.menu.getMenuItem(0).setActive(true);
    }


    public Scene getScene() {
        return this.scene;
    }

    public int getCurrentItem() {
        return this.currentItem;
    }

    public void processInput(Stage stage, Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (this.currentItem > 0) {
                    this.menu.getMenuItem(currentItem).setActive(false);
                    this.menu.getMenuItem(--currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (this.currentItem < this.menu.getChildren().size() - 1) {
                    this.menu.getMenuItem(currentItem).setActive(false);
                    this.menu.getMenuItem(++currentItem).setActive(true);
                }
            }
            if (event.getCode() == KeyCode.ENTER) {
                this.menu.getMenuItem(currentItem).activate();
                switch (currentItem) {
                    case 0:
                        new Singleplayer(stage, scene).visualize();
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        Platform.exit();
                        break;
                }
            }
        });
    }
}
