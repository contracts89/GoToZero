
    package sample.stages;

    import javafx.scene.Scene;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.Pane;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;
    import sample.constants.Constants;
    import sample.models.menumodels.Options;


    public class Highscore extends AbstractStage{
        private Image image;
        private ImageView background = new ImageView(this.image);
        private Text help;
        private Options controls;
        private Options backButton;


        public Highscore(Stage stage, Scene scene) {
            super(stage, scene);
            this.image = new Image(getClass().getResourceAsStream("../resources/menuWallpaper.jpg"));
            this.background = new ImageView(this.image);

        }

        @Override
        public void visualize() {
            Pane root = new Pane();

            root.setPrefSize(Constants.WIDTH, Constants.HEIGHT);

            this.backButton = new Options("Back button", Constants.backButton());

            this.backButton.getItem(0).setOnMousePressed(e -> stage.setScene(scene));

            root.getChildren().addAll(this.background, this.backButton, Constants.HIGH_SCORE_TEXT);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }



