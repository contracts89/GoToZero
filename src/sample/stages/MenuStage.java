package sample.stages;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.constants.Constants;
import sample.input.MenuHandler;
import sample.models.menumodels.AboutText;
import sample.models.menumodels.ContentFrame;
import sample.models.menumodels.Options;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MenuStage extends Application {

    private MenuHandler menuHandler;
    private Options menuOptions;
    private ContentFrame contentFrame1;
    private ContentFrame contentFrame2;
    private HBox hBox;
    private AboutText aboutText;
    private ScheduledExecutorService bgThread = Executors.newSingleThreadScheduledExecutor();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Constants.WIDTH, Constants.HEIGHT);

        Rectangle bg = new Rectangle(Constants.WIDTH, Constants.HEIGHT);
        this.menuOptions = new Options("Menu", Constants.menuNodes());
        this.contentFrame1 = new ContentFrame(ContentFrame.createRightContent());
        this.contentFrame2 = new ContentFrame(ContentFrame.createMiddleContent());
        this.hBox = new HBox(15, contentFrame1, contentFrame2);
        this.hBox.setTranslateX(235);
        this.hBox.setTranslateY(50);
        this.aboutText = new AboutText("GoToZero by Team Ancalogon");
        root.getChildren().addAll(bg, this.hBox, this.menuOptions, this.aboutText);
        return root;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());

        this.menuHandler = new MenuHandler(scene, this.menuOptions);
        this.menuHandler.processMenuInput(primaryStage, scene);

        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
            this.bgThread.shutdownNow();
        });
        primaryStage.show();
    }
}
