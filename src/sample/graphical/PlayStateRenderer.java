package sample.graphical;

import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.collisions.interfaces.CollisionDetector;
import sample.controllers.interfaces.OperationSwitcher;
import sample.graphical.interfaces.Renderer;
import sample.graphical.interfaces.TextCreator;
import sample.models.playmodels.PlayerImpl;

import static sample.constants.MenuConstants.HEIGHT;
import static sample.constants.MenuConstants.WIDTH;
import static sample.constants.PlayStateConstants.PLAYSTATE_BACKGROUND_PATH;

public class PlayStateRenderer implements Renderer {

    private CollisionDetector collisionDetector;
    private Text fallenObjectsAcquired;
    private TextCreator textCreator;
    private Text scoreText;
    private Text operationText;
    private Pane pane;
    private Image background;
    private ImageView imageView;
    private PlayerImpl player;
    private LongProperty score; // Set the starting Score (default is 128)
    private OperationSwitcher operationSwitcher;

    public PlayStateRenderer(CollisionDetector collisionDetector,
                             TextCreator textCreator,
                             Pane pane,
                             PlayerImpl player,
                             LongProperty score,
                             OperationSwitcher operationSwitcher) throws ReflectiveOperationException {
        this.collisionDetector = collisionDetector;
        this.textCreator = textCreator;
        this.pane = pane;

        this.background = new Image(getClass().getResourceAsStream(PLAYSTATE_BACKGROUND_PATH));
        this.imageView = new ImageView(this.background);
        this.player = player;
        this.operationSwitcher = operationSwitcher;
        this.score = score;
        this.drawThePlayScene();
    }

    @Override
    public void render() throws ReflectiveOperationException {
        this.drawObjectsAcquired();
        this.drawScoreAndCurrentOperation();
    }

    private void drawObjectsAcquired() {
        int currentCount = collisionDetector.getCollidedObjectsCount();
        this.fallenObjectsAcquired.setText(String.format("CURRENT COUNT : %d", currentCount));
    }

    //draw the current Score on the scene
    private void drawScoreAndCurrentOperation() throws ReflectiveOperationException {

        this.scoreText.textProperty().bind(Bindings.createStringBinding(() -> ("SCORE: " + score.get()),
                score));
        //replace infinity score with String INFINITY
        if (this.score.get() == 999999999) {
            this.scoreText.textProperty().bind(Bindings.createStringBinding(() -> ("SCORE: INFINITY...")));
        }
        this.operationText.textProperty().bind(Bindings.createStringBinding(() -> ("CURRENT OPERATION: " +
                this.operationSwitcher.getMathOperation())));
    }

    private void drawThePlayScene() throws ReflectiveOperationException {

        this.fallenObjectsAcquired = this.textCreator.createText("ObjectsTakenText");
        this.scoreText = this.textCreator.createText("ScoreText");
        this.operationText = this.textCreator.createText("OperationText");
        this.pane.setPrefSize(WIDTH, HEIGHT); // set the scene dimensions

        this.pane.getChildren()
                .addAll(this.imageView,
                        this.scoreText,
                        this.operationText,
                        this.fallenObjectsAcquired,
                        this.player); // add objects in the scene
    }
}
