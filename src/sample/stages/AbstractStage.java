package sample.stages;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.graphical.TextCreatorImpl;
import sample.stages.interfaces.Visualizable;
import sample.graphical.interfaces.TextCreator;

public abstract class AbstractStage implements Visualizable {

    protected static Stage stage;
    protected static Scene scene;
    protected TextCreator textCreator;

    public AbstractStage(javafx.stage.Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
        this.textCreator = new TextCreatorImpl();
    }

    @Override
    public abstract void visualize() throws ReflectiveOperationException;
}
