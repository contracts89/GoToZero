
package sample.stages;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.animations.FallTransitionImpl;
import sample.animations.interfaces.FallTransition;
import sample.constants.MenuConstants;
import sample.models.menumodels.Options;

import static sample.constants.MenuConstants.*;

public class CreditsStage extends AbstractStage {

    private Image image;
    private ImageView background = new ImageView(this.image);
    private FallTransition creditsTransition;
    private Options developers;
    private Options backButton;
    private Text creditText;

    public CreditsStage(Stage stage, Scene scene) {
        super(stage, scene);
        this.image = new Image(getClass().getResourceAsStream(BACKGROUND_PATH));
        this.background = new ImageView(this.image);
        this.creditsTransition = new FallTransitionImpl(450, 170);
        this.creditsTransition.reverse();
    }

    @Override
    public void visualize() throws ReflectiveOperationException {
        Pane root = new Pane();

        root.setPrefSize(WIDTH, HEIGHT);


        this.developers = new Options("CreditsStage", OptionsCreator.creditsText());
        this.backButton = new Options("Back button", MenuConstants.backButton());

        this.backButton.getItem(0).setOnMousePressed(e->stage.setScene(scene));
        this.creditText = this.textCreator.createText("CreditText");
        root.getChildren().addAll(this.background, this.developers, this.backButton,this.creditText);
        this.creditsTransition.useFallAnimation(this.developers, 6, CREDITS_ANIMATION_DROP_POINT);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}