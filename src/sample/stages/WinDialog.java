package sample.stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.WinController;

import java.io.IOException;


public class WinDialog {

    private Stage stage;
    private String fxmlFile;
    private FXMLLoader loader;
    private Parent rootNode;

    public WinDialog() throws IOException {
        this.stage = new Stage();
        this.fxmlFile = "../fxmlFiles/winDialog.fxml";
        this.loader = new FXMLLoader();
        this.rootNode = null;
    }

    public void show() {
        try {
            this.rootNode = loader.load(getClass().getResourceAsStream(this.fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Scene scene = new Scene(this.rootNode);

        WinController winController = new WinController();


        this.stage.setTitle("You Win");
        this.stage.setScene(scene);
        this.stage.setResizable(false);

        this.stage.show();
    }
}
