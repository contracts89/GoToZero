package sample.input;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.models.menumodels.Options;
import sample.stages.Credits;
import sample.stages.Singleplayer;

public class MenuHandler {

    private Scene scene;
    private Options menu;

    public MenuHandler(Scene scene, Options menuoptions) {
        this.scene = scene;
        this.menu = menuoptions;
    }


    public Scene getScene() {
        return this.scene;
    }


    public void processMenuInput(Stage stage, Scene scene) {

        scene.setOnMousePressed(event -> {
            if (this.menu.getItem(0).isPressed()) {
                new Singleplayer(stage, this.scene).visualize();
            }
            if (this.menu.getItem(4).isPressed()) {
                new Credits(stage, this.scene).visualize();
            }
        });
    }
}
