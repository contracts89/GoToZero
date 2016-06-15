package sample;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Player extends Pane {
    private final static int SPEED = 5;
    private final static int LEFT_WALL_BOUNDARY = -10;
    private final static int RIGHT_WALL_BOUNDARY = 850;
    private SpriteAnimation animation;
    private SpriteAnimation animationOnPlace;
    boolean isMovingRight;
    boolean isMovingLeft;
    private Image playerImg = new Image(getClass().getResourceAsStream("resources/playerSprite.png"));

    public Player() {
        int offsetX = 0;
        int width = 60;
        int height = 60;
        int offsetY = 120;
        int count = 8;
        int columns = 8;
        int offsetYOnPlace = 0;
        ImageView imageView = new ImageView(playerImg);

        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(750), count, columns, offsetX, offsetY, width, height);
        animationOnPlace = new SpriteAnimation(imageView, Duration.millis(750), count, columns, offsetX, offsetYOnPlace, width, height);
        useTheAnimation();
        getChildren().addAll(imageView);
    }


    void move() {
        if (isMovingRight) {
            if (this.getTranslateX() < RIGHT_WALL_BOUNDARY) { //CHECK TO SEE IF RIGHT WALL IS REACHED
                this.setTranslateX(this.getTranslateX() + SPEED);
            }
        } else if (isMovingLeft) {
            if (this.getTranslateX() > LEFT_WALL_BOUNDARY) { //CHECK TO SEE IF LEFT WALL IS REACHED
                this.setTranslateX(this.getTranslateX() - SPEED);
            }
        }
    }

    void useTheAnimation() {
        if(isMovingLeft || isMovingRight){
            animation.play();
        }else {
            animation.stop();
            animationOnPlace.play();
        }
    }

}