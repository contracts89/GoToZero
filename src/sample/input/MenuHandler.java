package sample.input;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.models.menumodels.Options;
import sample.stages.CreditsStage;
import sample.stages.HelpStage;
import sample.stages.Highscore;
import sample.stages.PlayState;

public class MenuHandler {

    private Scene scene;
    private Options menu;
    private boolean isMultiplayer;

    public MenuHandler(Scene scene, Options menuoptions) {
        this.scene = scene;
        this.menu = menuoptions;

    }

    public Scene getScene() {
        return this.scene;
    }


    public void processMenuInput(Stage stage, Scene scene) {

        this.menu.getItem(0).setOnMousePressed(e-> new PlayState(stage,scene,false).visualize());
        this.menu.getItem(1).setOnMousePressed(e -> new PlayState(stage,scene,true).visualize());
        this.menu.getItem(2).setOnMousePressed(e -> new Highscore(stage,scene).visualize());
        this.menu.getItem(3).setOnMousePressed(e-> new HelpStage(stage,scene).visualize());
        this.menu.getItem(4).setOnMousePressed(e-> new CreditsStage(stage,scene).visualize());
        this.menu.getItem(5).setOnMousePressed(e-> Platform.exit());
    }
}
