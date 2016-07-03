package sample.stages;

import javafx.scene.Scene;
import javafx.stage.Stage;
public abstract class AbstractStage {

    protected Stage stage;
    protected Scene scene;

    public AbstractStage(javafx.stage.Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    public abstract void visualize();
}
