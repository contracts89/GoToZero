package sample.stages;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.stages.interfaces.Visualizable;

public abstract class AbstractStage implements Visualizable {

    protected static Stage stage;
    protected static Scene scene;

    public AbstractStage(javafx.stage.Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    @Override
    public abstract void visualize();
}
