package sample.stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.collisions.CollsionDetectorImpl;
import sample.output.HighscoreManager;

import java.io.IOException;


public class WinDialog extends AbstractStage {

    private static Stage winStage;
    private String fxmlFile;
    private FXMLLoader loader;
    private Parent rootNode;
    private Label winResult;

    public WinDialog(Stage stage, Scene scene) throws IOException {
        super(stage, scene);
        winStage = new Stage();
        this.fxmlFile = "../fxmlFiles/winDialog.fxml";
        this.loader = new FXMLLoader();
        this.rootNode = null;
    }



    @Override
    public void visualize() {

        try {
            this.rootNode = loader.load(getClass().getResourceAsStream(this.fxmlFile));
            //add total taken obects as result
            winResult = (Label) rootNode.lookup("#winResult");
            if (winResult!=null) {
                winResult.setText(String.format("You Won!\nYou go to Zero with %d objects ", CollsionDetectorImpl.getCollidedObjects()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Scene scene = new Scene(this.rootNode);
        winStage.setTitle("You Won!");
        winStage.setScene(scene);
        winStage.setResizable(true);
        winStage.show();

        HighscoreManager.saveScore(CollsionDetectorImpl.getCollidedObjects());
    }

    public static void goToMenu() {
        winStage.close();
        stage.setScene(scene);
    }

    public static void playAgain() {
        new PlayState(stage, scene).visualize();
        CollsionDetectorImpl.setCollidedObjects(0);
    }
}
