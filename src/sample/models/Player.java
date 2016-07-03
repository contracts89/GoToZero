package sample.models;


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
    private Image playerImg = new Image(getClass().getResourceAsStream("../resources/playerSprite.png"));

    public Player() {
        int offsetX = 0;
        int width = 60;
        int height = 60;
        int offsetY = 120;
        int count = 8;
        int columns = 8;
        int offsetYOnPlace = 0;
        ImageView imageView = new ImageView(playerImg);

        imageView.setFitHeight(width);
        imageView.setFitWidth(height);
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(750), count, columns, offsetX, offsetY, width, height);
        animationOnPlace = new SpriteAnimation(imageView, Duration.millis(750), count, columns, offsetX, offsetYOnPlace, width, height);
        animate();
        getChildren().addAll(imageView);
    }

    private boolean isMoving() {
        return isMoving;
    }

    private void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void moveRight(){
        this.setMoving(true);
        if (this.getTranslateX() < Constants.RIGHT_WALL_BOUNDARY) { //CHECK TO SEE IF RIGHT WALL IS REACHED
            this.setTranslateX(this.getTranslateX() + Constants.VELOCITY);
        }
    }
    public void moveLeft(){
        this.setMoving(true);
        if (this.getTranslateX() > Constants.LEFT_WALL_BOUNDARY) { //CHECK TO SEE IF LEFT WALL IS REACHED
            this.setTranslateX(this.getTranslateX() - Constants.VELOCITY);
        }
    }
    public void stayAtPos(){
        this.setMoving(false);
    }

    public void animate() {
        if(this.isMoving){
            this.animation.play();
        }else {
            this.animation.stop();
            this.animationOnPlace.play();
        }
    }

}