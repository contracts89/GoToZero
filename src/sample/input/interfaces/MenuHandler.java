package sample.input.interfaces;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface MenuHandler {
    void processMenuInput(Stage stage, Scene scene) throws ReflectiveOperationException;
}
