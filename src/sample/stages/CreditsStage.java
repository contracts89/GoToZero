package sample.stages;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.animations.FallTransition;
import sample.constants.Constants;
import sample.models.menumodels.Options;

public class CreditsStage extends AbstractStage {

    private Image image;
    private ImageView background = new ImageView(this.image);
    private FallTransition creditsTransition;
    private Options developers;
    private Options backButton;

    public CreditsStage(Stage stage, Scene scene) {
        super(stage, scene);
        this.image = new Image(getClass().getResourceAsStream("../resources/menuWallpaper.jpg"));
        this.background = new ImageView(this.image);
        this.creditsTransition = new FallTransition(450, 170);
        this.creditsTransition.reverse();
    }

    @Override
    public void visualize() {
        Pane root = new Pane();

        root.setPrefSize(Constants.WIDTH, Constants.HEIGHT);

        this.developers = new Options("CreditsStage", Constants.creditsText());
        this.backButton = new Options("Back button", Constants.backButton());

        this.backButton.getItem(0).setOnMousePressed(e->stage.setScene(scene));

        root.getChildren().addAll(this.background, this.developers, this.backButton,Constants.CREDITS_BY);
        this.creditsTransition.useFallAnimation(this.developers, 6, Constants.CREDITS_ANIMATION_DROP_POINT);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}