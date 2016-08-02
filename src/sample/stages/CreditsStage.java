<<<<<<< HEAD
package sample.stages;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.animations.FallTransition;
import sample.constants.Constants;
import sample.models.menumodels.Options;

import static sample.constants.MenuConstants.*;
import static sample.constants.PlayStateConstants.*;

public class CreditsStage extends AbstractStage {

    private Image image;
    private ImageView background = new ImageView(this.image);
    private FallTransition creditsTransition;
    private Options developers;
    private Options backButton;

    public CreditsStage(Stage stage, Scene scene) {
        super(stage, scene);
        this.image = new Image(getClass().getResourceAsStream(BACKGROUND_PATH));
        this.background = new ImageView(this.image);
        this.creditsTransition = new FallTransition(450, 170);
        this.creditsTransition.reverse();
    }

    @Override
    public void visualize() {
        Pane root = new Pane();

        root.setPrefSize(WIDTH, HEIGHT);

        this.developers = new Options("CreditsStage", Constants.creditsText());
        this.backButton = new Options("Back button", Constants.backButton());

        this.backButton.getItem(0).setOnMousePressed(e->getStage().setScene(getScene()));

        root.getChildren().addAll(this.background, this.developers, this.backButton,Constants.CREDITS_BY);
        this.creditsTransition.useFallAnimation(this.developers, 6, CREDITS_ANIMATION_DROP_POINT);
        Scene scene = new Scene(root);
        getStage().setScene(scene);
        getStage().show();
    }
=======
package sample.stages;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.animations.FallTransition;
import sample.constants.Constants;
import sample.models.menumodels.Options;

import static sample.constants.Constants.BACKGROUND_PATH;

public class CreditsStage extends AbstractStage {

    private Image image;
    private ImageView background = new ImageView(this.image);
    private FallTransition creditsTransition;
    private Options developers;
    private Options backButton;

    public CreditsStage(Stage stage, Scene scene) {
        super(stage, scene);
        this.image = new Image(getClass().getResourceAsStream(BACKGROUND_PATH));
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
>>>>>>> 3fcd8582235d14e95e9f63e689b45d21e27c74fe
}