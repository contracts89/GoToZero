package sample.stages;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.animations.FallTransition;
import sample.constants.Constants;
import sample.models.menumodels.Options;

public class Credits extends AbstractStage {

    private Image image;
    private ImageView background = new ImageView(this.image);
    private FallTransition creditsTransition;
    private Text creditsBy;
    private Options developers;
    private Options backButton;

    public Credits(Stage stage, Scene scene) {
        super(stage, scene);
        this.developers = new Options("Credits", Constants.creditsText());
        this.backButton = new Options("Back button", Constants.backButton());
        this.image = new Image(getClass().getResourceAsStream("../resources/creditsWallpaper.jpg"));
        this.background = new ImageView(this.image);
        this.creditsTransition = new FallTransition(450, 170);
        this.creditsTransition.reverse();
        this.createCreditText();
    }

    @Override
    public void visualize() {
        Pane root = new Pane();

        root.setPrefSize(Constants.WIDTH, Constants.HEIGHT);

        root.getChildren().addAll(this.background, this.developers, this.backButton, this.creditsBy);
        this.creditsTransition.useFallAnimation(this.developers, 6, Constants.CREDITS_ANIMATION_DROP_POINT);
        this.backButton.getItem(0).setOnMouseClicked(event -> {
            root.setVisible(false);
            this.stage.setScene(this.scene);
        });
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
    }

    private void createCreditText() {
        this.creditsBy = new Text("CREDITS BY =>\n\nTEAM ANCALOGON");
        this.creditsBy.setTranslateX(130);
        this.creditsBy.setTranslateY(250);
        this.creditsBy.setFill(Color.WHITESMOKE);
        this.creditsBy.setFont(Constants.MENU_FONT);
        this.creditsBy.setOpacity(5);
    }
}