package sample.stages;

import javafx.scene.Scene;
import javafx.stage.Stage;
public abstract class AbstractStage {

    protected static Stage stage;
    protected static Scene scene;
    // it doesnt work without this (If someone could fix it it would be nice))
    public AbstractStage(javafx.stage.Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    public abstract void visualize();
}
