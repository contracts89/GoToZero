package sample.stages;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.animations.FallTransition;
import sample.constants.Constants;
import sample.models.menumodels.Options;

public class Credits extends AbstractStage {

    private FallTransition creditsTransition;
    private Text creditsBy;
    private Options developers;
    private Options backButton;

    public Credits(Stage stage, Scene scene) {
        super(stage, scene);
        this.creditsTransition = new FallTransition(450, 170);
        this.creditsTransition.reverse();
        this.createCreditText();
    }

    @Override
    public void visualize() {
        Pane root = new Pane();

        root.setPrefSize(Constants.WIDTH, Constants.HEIGHT);

        Rectangle bg = new Rectangle(Constants.WIDTH, Constants.HEIGHT);

        this.developers = new Options("Credits", Constants.creditsText());
        this.backButton = new Options("Back button", Constants.backButton());

        root.getChildren().addAll(bg, this.developers, this.backButton, this.creditsBy);
        this.creditsTransition.useFallAnimation(this.developers, 6,Constants.CREDITS_ANIMATION_DROP_POINT);
        this.backButton.getItem(0).setOnMousePressed(event -> {
            stage.setScene(this.scene);
        });
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
    }

    private void createCreditText() {
        this.creditsBy = new Text("CREDITS BY =>");
        this.creditsBy.setTranslateX(130);
        this.creditsBy.setTranslateY(250);
        this.creditsBy.setFill(Color.ANTIQUEWHITE);
        this.creditsBy.setFont(Constants.MENU_FONT);
        this.creditsBy.setOpacity(5);
    }
}