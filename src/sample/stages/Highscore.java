
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


    import java.io.*;


    public class Highscore extends AbstractStage{
        private Image image;
        private ImageView background = new ImageView(this.image);
        private Text help;
        private Options controls;
        private Options backButton;
        public Text highScoreText;



        public Highscore(Stage stage, Scene scene) {
            super(stage, scene);
            this.image = new Image(getClass().getResourceAsStream("../resources/menuWallpaper.jpg"));
            this.background = new ImageView(this.image);
            try {
                highScoreText = createHighScoreText();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        public Text createHighScoreText() throws IOException {
            StringBuilder highScore = getHighScoreFilePath();
            Text highScoreText = new Text(highScore.toString());
            highScoreText.setTranslateX(60);
            highScoreText.setTranslateY(40);
            highScoreText.setFill(Color.ANTIQUEWHITE);
            highScoreText.setFont(Constants.HELP_FONT);
            highScoreText.setOpacity(1.5);
            return highScoreText;
        }

        private StringBuilder getHighScoreFilePath() throws IOException {
            StringBuilder helpBuilder = new StringBuilder();
            File file = new File("src/sample/resources/highScore.txt");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("789");
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                while (line != null) {
                    helpBuilder.append(line);
                    helpBuilder.append(System.lineSeparator());
                    line = br.readLine();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return helpBuilder;
        }
        @Override
        public void visualize() {
            Pane root = new Pane();

            root.setPrefSize(Constants.WIDTH, Constants.HEIGHT);

            this.backButton = new Options("Back button", Constants.backButton());

            this.backButton.getItem(0).setOnMousePressed(e -> stage.setScene(scene));

            root.getChildren().addAll(this.background, this.backButton, highScoreText);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }



