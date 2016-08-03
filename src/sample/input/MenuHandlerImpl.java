package sample.input;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.input.interfaces.MenuHandler;
import sample.models.menumodels.Options;
import sample.stages.CreditsStage;
import sample.stages.HelpStage;
import sample.stages.Highscore;
import sample.stages.PlayState;

public class MenuHandlerImpl implements MenuHandler{

    private Scene scene;
    private Options menu;

    public MenuHandlerImpl(Scene scene, Options menuoptions) {
        this.scene = scene;
        this.menu = menuoptions;
    }

    public Scene getScene() {
        return this.scene;
    }

    public void processMenuInput(Stage stage, Scene scene) throws ReflectiveOperationException {

        this.menu.getItem(0).setOnMousePressed(e -> {
            try {
                new PlayState(stage, scene).visualize();
            } catch (ReflectiveOperationException e1) {
                e1.printStackTrace();
            }
        });
        this.menu.getItem(1).setOnMousePressed(e -> {
            try {
                new PlayState(stage, scene).visualize();
            } catch (ReflectiveOperationException e1) {
                e1.printStackTrace();
            }
        });
        this.menu.getItem(2).setOnMousePressed(e -> {
            try {
                new Highscore(stage, scene).visualize();
            } catch (ReflectiveOperationException e1) {
                e1.printStackTrace();
            }
        });
        this.menu.getItem(3).setOnMousePressed(e -> {
            try {
                new HelpStage(stage, scene).visualize();
            } catch (ReflectiveOperationException e1) {
                e1.printStackTrace();
            }
        });
        this.menu.getItem(4).setOnMousePressed(e -> {
            try {
                new CreditsStage(stage, scene).visualize();
            } catch (ReflectiveOperationException e1) {
                e1.printStackTrace();
            }
        });
        this.menu.getItem(5).setOnMousePressed(e -> Platform.exit());

    }
}
