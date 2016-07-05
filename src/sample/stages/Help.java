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

public class Help extends AbstractStage {

    private Image image;
    private ImageView background = new ImageView(this.image);
    private FallTransition creditsTransition;
    private Text help;
    private Options controls;
    private Options backButton;


    public Help(Stage stage, Scene scene) {
        super(stage, scene);
        this.image = new Image(getClass().getResourceAsStream("../resources/menuWallpaper.jpg"));
        this.background = new ImageView(this.image);
//        this.creditsTransition = new FallTransition(450, 170);
//        this.creditsTransition.reverse();
        this.createHelpText();
    }

    @Override
    public void visualize() {
        Pane root = new Pane();

        root.setPrefSize(Constants.WIDTH, Constants.HEIGHT);

        this.controls = new Options("Help", Constants.HelpsText());
        this.backButton = new Options("Back button", Constants.backButton());

        this.backButton.getItem(0).setOnMousePressed(e->this.stage.setScene(this.scene));

        root.getChildren().addAll(this.background, this.controls, this.backButton, this.help);
      //  this.creditsTransition.useFallAnimation(this.developers, 6, Constants.CREDITS_ANIMATION_DROP_POINT);
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
    }

    private void createHelpText() {
        this.help = new Text("Help =>\n\n");
        this.help.setTranslateX(50);
        this.help.setTranslateY(100);
        this.help.setFill(Color.WHITESMOKE);
        this.help.setFont(Constants.MENU_FONT);
        this.help.setOpacity(5);
    }
}
