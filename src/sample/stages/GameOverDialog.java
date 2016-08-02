package sample.stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class GameOverDialog extends AbstractStage {

    private static Stage winStage;
    private String fxmlFile;
    private FXMLLoader loader;
    private Parent rootNode;

    public GameOverDialog(Stage stage, Scene scene) throws IOException {
        super(stage, scene);
        winStage = new Stage();
        this.fxmlFile = "../fxmlFiles/winDialogGameOver.fxml";
        this.loader = new FXMLLoader();
        this.rootNode = null;
    }



    @Override
    public void visualize() {

        try {
            this.rootNode = loader.load(getClass().getResourceAsStream(this.fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Scene scene = new Scene(this.rootNode);
        winStage.setTitle("Game Over!");
        winStage.setScene(scene);
        winStage.setResizable(true);
        winStage.show();
    }
     public static void goToMenu(){
        winStage.close();
        stage.setScene(scene);
    }
}
