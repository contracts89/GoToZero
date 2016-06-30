package sample.stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by KNikov on 30/06/2016.
 */
public class WinDialog {

    private Stage stage;
    private String fxmlFile;
    private FXMLLoader loader;
    private Parent rootNode;

    public WinDialog() throws IOException {
        this.stage = new Stage();
        this.fxmlFile = "../winDialog.fxml";
        this.loader = new FXMLLoader();
        this.rootNode = null;
    }

    public void show() {
        try {
            rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Scene scene = new Scene(rootNode);


        stage.setTitle("You Win");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }
}
