
package sample.stages;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.constants.MenuConstants;
import sample.models.menumodels.Options;

import static sample.constants.MenuConstants.*;


public class HelpStage extends AbstractStage {

    private Image image;
    private ImageView background = new ImageView(this.image);
    private Options backButton;
    private Text helpText;

    public HelpStage(Stage stage, Scene scene) {
        super(stage, scene);
        this.image = new Image(getClass().getResourceAsStream(BACKGROUND_PATH));
        this.background = new ImageView(this.image);
    }

    @Override
    public void visualize() throws ReflectiveOperationException {
        Pane root = new Pane();

        root.setPrefSize(WIDTH, HEIGHT);

        this.backButton = new Options("Back button", MenuConstants.backButton());

        this.backButton.getItem(0).setOnMousePressed(e -> stage.setScene(scene));
        this.helpText = this.textCreator.createText("HelpText");
        root.getChildren().addAll(this.background, this.backButton, this.helpText);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
