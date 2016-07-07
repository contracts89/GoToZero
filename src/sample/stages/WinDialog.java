package sample.stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.collisions.CollisionDetector;
import sample.output.HighscoreManager;

import java.io.IOException;


public class WinDialog extends AbstractStage {

    private static Stage winStage;
    private String fxmlFile;
    private FXMLLoader loader;
    private Parent rootNode;

    public WinDialog(Stage stage, Scene scene) throws IOException {
        super(stage, scene);
        winStage = new Stage();
        this.fxmlFile = "../fxmlFiles/winDialog.fxml";
        this.loader = new FXMLLoader();
        this.rootNode = null;
    }

    public void show() {
    }

    @Override
    public void visualize() {

        try {
            this.rootNode = loader.load(getClass().getResourceAsStream(this.fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Scene scene = new Scene(this.rootNode);
        winStage.setTitle("You Won!");
        winStage.setScene(scene);
        winStage.setResizable(true);
        winStage.show();

        HighscoreManager.saveScore(CollisionDetector.getCollidedObjects());
    }

    public static void goToMenu() {
        winStage.close();
        stage.setScene(scene);
    }

    public static void playAgain() {
        new PlayState(stage, scene, false).visualize();
        CollisionDetector.setCollidedObjects(0);
    }
}
