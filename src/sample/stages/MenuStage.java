package sample.stages;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.constants.Constants;
import sample.input.MenuHandler;
import sample.models.menumodels.AboutText;
import sample.models.menumodels.ContentFrame;
import sample.models.menumodels.Options;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MenuStage extends Application {

    private Stage stage;
    private Scene scene;
    private MenuHandler menuHandler;
    private Options menuOptions;
    private ContentFrame contentFrame1;
    private ContentFrame contentFrame2;
    private HBox hBox;
    private AboutText aboutText;
    private ScheduledExecutorService bgThread = Executors.newSingleThreadScheduledExecutor();
    private Image background;
    private ImageView imageView;

    public  Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Constants.WIDTH, Constants.HEIGHT);

        this.menuOptions = new Options("Menu", Constants.menuNodes());
        this.background = new Image(getClass().getResourceAsStream("../resources/menuWallpaper.jpg"));
        this.imageView = new ImageView(this.background);
        this.contentFrame1 = new ContentFrame(ContentFrame.createRightContent());
        this.contentFrame2 = new ContentFrame(ContentFrame.createMiddleContent());
        this.hBox = new HBox(15, contentFrame1, contentFrame2);
        this.hBox.setTranslateX(35);
        this.hBox.setTranslateY(20);
        this.aboutText = new AboutText("GoToZero\n\nby Team Ancalogon \u00AE");
        root.getChildren().addAll(this.imageView, this.menuOptions, this.hBox, this.aboutText);
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.scene = new Scene(createContent());
        this.menuHandler = new MenuHandler(scene, this.menuOptions);
        this.menuHandler.processMenuInput(primaryStage, this.scene);
        this.stage = primaryStage;
        this.stage.setScene(this.scene);
        this.stage.setOnCloseRequest(event -> {
            this.bgThread.shutdownNow();
        });
        this.stage.setTitle("GoToZero");
        this.stage.setResizable(false);
        this.stage.show();
    }
}
