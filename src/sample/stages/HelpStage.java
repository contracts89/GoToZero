package sample.stages;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.constants.Constants;
import sample.models.menumodels.Options;

public class HelpStage extends AbstractStage {

    private Image image;
    private ImageView background = new ImageView(this.image);
    private Text help;
    private Options controls;
    private Options backButton;


    public HelpStage(Stage stage, Scene scene) {
        super(stage, scene);
        this.image = new Image(getClass().getResourceAsStream("../resources/menuWallpaper.jpg"));
        this.background = new ImageView(this.image);
        this.createHelpText();
    }

    @Override
    public void visualize() {
        Pane root = new Pane();

        root.setPrefSize(Constants.WIDTH, Constants.HEIGHT);

//        this.controls = new Options("HelpStage", Constants.HelpsText());
        this.backButton = new Options("Back button", Constants.backButton());

        this.backButton.getItem(0).setOnMousePressed(e -> stage.setScene(scene));

        root.getChildren().addAll(this.background, this.backButton,Constants.HELP_TEXT);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void createHelpText() {
        this.help = new Text("HelpStage =>\n\n");
        this.help.setTranslateX(50);
        this.help.setTranslateY(100);
        this.help.setFill(Color.WHITESMOKE);
        this.help.setFont(Constants.MENU_FONT);
        this.help.setOpacity(5);
    }
}
