package sample.stages;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.stages.interfaces.Visualizable;

public abstract class AbstractStage implements Visualizable {

    private  Stage stage;
    private  Scene scene;

    public AbstractStage(javafx.stage.Stage stage, Scene scene) {
        this.setStage(stage);
        this.setScene(scene);
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return this.scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public abstract void visualize();
}
