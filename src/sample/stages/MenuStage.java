package sample.stages;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.constants.Constants;
import sample.input.MenuHandler;
import sample.menumodels.ContentFrame;
import sample.menumodels.MenuOptions;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MenuStage extends Application {

    private MenuHandler menuHandler;
    private MenuOptions menuOptions;
    private ContentFrame contentFrame1;
    private ContentFrame contentFrame2;
    private HBox hBox;
    private ScheduledExecutorService bgThread = Executors.newSingleThreadScheduledExecutor();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Constants.WIDTH, Constants.HEIGHT);

        Rectangle bg = new Rectangle(Constants.WIDTH, Constants.HEIGHT);
        this.menuOptions = new MenuOptions();
        this.contentFrame1 = new ContentFrame(ContentFrame.createRightContent());
        this.contentFrame2 = new ContentFrame(ContentFrame.createMiddleContent());
        this.hBox = new HBox(15, contentFrame1, contentFrame2);
        this.hBox.setTranslateX(235);
        this.hBox.setTranslateY(50);
        Text about = new Text("GoToZero by Team Ancalogon");
        about.setTranslateX(320);
        about.setTranslateY(570);
        about.setFill(Color.DARKRED);
        about.setFont(Constants.MENU_FONT);
        about.setOpacity(5);
        root.getChildren().addAll(bg, this.hBox, this.menuOptions, about);
        return root;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());

        this.menuHandler = new MenuHandler(scene, this.menuOptions);
        this.menuHandler.processInput(primaryStage,scene);

        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
            this.bgThread.shutdownNow();
        });
        primaryStage.show();
    }
}
