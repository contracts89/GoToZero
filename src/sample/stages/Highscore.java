
package sample.stages;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.constants.MenuConstants;
import sample.models.menumodels.Options;

import static sample.constants.MenuConstants.*;


public class Highscore extends AbstractStage {

    private Image image;
    private ImageView background;
    private Options backButton;
    private Text highScoreText;

    public Highscore(Stage stage, Scene scene) {
        super(stage, scene);
        this.image = new Image(getClass().getResourceAsStream(BACKGROUND_PATH));
        this.background = new ImageView(this.image);
    }

    @Override
    public void visualize() throws ReflectiveOperationException {
        Pane root = new Pane();

        root.setPrefSize(WIDTH, HEIGHT);


//        Constants constants = new Constants();
//        String highscoreText = new HighscoreManager().getScores();
//        this.highScoreText = constants.displayHighScore(highscoreText);
        this.highScoreText = this.textCreator.createText("HighscoreText");
        this.backButton = new Options("Back button", MenuConstants.backButton());
        this.backButton.getItem(0).setOnMousePressed(e -> stage.setScene(scene));

        root.getChildren().addAll(this.background, this.backButton, highScoreText);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
