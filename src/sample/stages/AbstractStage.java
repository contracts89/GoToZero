package sample.stages;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.interfaces.Visible;

public abstract class AbstractStage implements Visible {

    protected static Stage stage;
    protected static Scene scene;

    public AbstractStage(javafx.stage.Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    public abstract void visualize();
}
