
package sample.stages;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.constants.MenuConstants;
import sample.graphical.interfaces.TextCreator;
import sample.graphical.TextCreatorImpl;
import sample.input.MenuHandlerImpl;
import sample.input.interfaces.MenuHandler;
import sample.models.menumodels.ContentFrame;
import sample.models.menumodels.Options;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static sample.constants.MenuConstants.*;

public class MenuStage extends Application {

    private Stage stage;
    private Scene scene;
    private MenuHandler menuHandler;
    private Options menuOptions;
    private ContentFrame contentFrame1;
    private ContentFrame contentFrame2;
    private HBox hBox;
    private ScheduledExecutorService bgThread = Executors.newSingleThreadScheduledExecutor();
    private Image background;
    private ImageView imageView;
    private TextCreator textCreator;
    private Text aboutText;

    private Parent createContent() throws ReflectiveOperationException {
        Pane root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);

        this.menuOptions = new Options("Menu", MenuConstants.menuNodes());
        this.background = new Image(getClass().getResourceAsStream(BACKGROUND_PATH));
        this.imageView = new ImageView(this.background);
        this.contentFrame1 = new ContentFrame(ContentFrame.createRightContent());
        this.contentFrame2 = new ContentFrame(ContentFrame.createMiddleContent());
        this.hBox = new HBox(15, contentFrame1, contentFrame2);
        this.hBox.setTranslateX(35);
        this.hBox.setTranslateY(20);
        this.aboutText = this.textCreator.createText("AboutText");

        root.getChildren().addAll(this.imageView, this.menuOptions, this.hBox, this.aboutText);
        return root;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.textCreator = new TextCreatorImpl();
        this.scene = new Scene(createContent());
        this.menuHandler = new MenuHandlerImpl(scene, this.menuOptions);
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
