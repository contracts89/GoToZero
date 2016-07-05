package sample.models.playmodels;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.animations.SpriteAnimation;
import sample.constants.Constants;

public class Player extends Pane {

    private SpriteAnimation animation;
    private SpriteAnimation animationOnPlace;
    private boolean isMoving;
    private Image playerImg;
    private ImageView playerImageView;

    public Player() {
        this.drawPlayer();
        this.animate();
        this.getChildren().addAll(playerImageView);
    }

    private void drawPlayer() {
        this.playerImg = new Image(getClass().getResourceAsStream("../../resources/playerSprite.png"));
        this.playerImageView = new ImageView(this.playerImg);
        int offsetX = 0;
        int width = 60;
        int height = 60;
        int offsetY = 120;
        int count = 8;
        int columns = 8;
        int offsetYOnPlace = 0;
        this.playerImageView.setFitHeight(width);
        this.playerImageView.setFitWidth(height);
        this.playerImageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        this.animation = new SpriteAnimation(this.playerImageView, Duration.millis(750), count, columns, offsetX,
                offsetY, width,
                height);
        this.animationOnPlace = new SpriteAnimation(this.playerImageView, Duration.millis(750), count, columns, offsetX,
                offsetYOnPlace, width, height);
        this.setTranslateX(Constants.DEFAULT_X_START_POSITION);
        this.setTranslateY(Constants.DEFAULT_Y_START_POSITION);
    }

    private boolean isMoving() {
        return isMoving;
    }

    private void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void moveRight() {
        this.setMoving(true);
        if (this.getTranslateX() < Constants.RIGHT_WALL_BOUNDARY) { //CHECK TO SEE IF RIGHT WALL IS REACHED
            this.setTranslateX(this.getTranslateX() + Constants.VELOCITY);
        }
    }

    public void moveLeft() {
        this.setMoving(true);
        if (this.getTranslateX() > Constants.LEFT_WALL_BOUNDARY) { //CHECK TO SEE IF LEFT WALL IS REACHED
            this.setTranslateX(this.getTranslateX() - Constants.VELOCITY);
        }
    }

    public void stayAtPos() {
        this.setMoving(false);
    }

    public void stopAnimation() {
        this.animation.stop();
        this.animationOnPlace.stop();
    }

    public void animate() {
        if (this.isMoving) {
            this.animation.play();
        } else {
            this.animation.stop();
            this.animationOnPlace.play();
        }
    }

}