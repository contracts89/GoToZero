package sample.input;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.models.menumodels.Options;
import sample.stages.Credits;
import sample.stages.Singleplayer;

public class MenuHandler {

    private Scene scene;
    private Options menu;
    public int currentItem;

    public MenuHandler(Scene scene, Options menuoptions) {
        this.scene = scene;
        this.menu = menuoptions;
    }


    public Scene getScene() {
        return this.scene;
    }

    public int getCurrentItem() {
        return this.currentItem;
    }

    public void processMenuInput(Stage stage, Scene scene) {

        scene.setOnMousePressed(event -> {
            if (this.menu.getItem(0).isPressed()) {
                new Singleplayer(stage, scene).visualize();
            }else if(this.menu.getItem(4).isPressed()){
                new Credits(stage,scene).visualize();
            }
            else if (this.menu.getItem(5).isPressed()) {
                Platform.exit();
            }
        });
    }
}
