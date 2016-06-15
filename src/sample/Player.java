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
    private Image playerImg = new Image(getClass().getResourceAsStream("resources/playerSprite.png"));
    private ImageView imageView = new ImageView(playerImg);
    private int count = 8;
    private int columns = 8;
    private int offsetX = 0;
    private int offsetY = 120;
    private int width = 60;
    private int height = 60;
    private SpriteAnimation animation;
    private SpriteAnimation animationOnPlace;
    private int offsetYOnPlace = 0;
    boolean isMovingRight;
    boolean isMovingLeft;
     Rectangle2D boundingBox = new Rectangle2D(this.getTranslateX(),this.getTranslateY(),this.width,this.height);
    public Player() {
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(this.imageView, Duration.millis(750), count, columns, offsetX, offsetY, width, height);
        animationOnPlace = new SpriteAnimation(this.imageView, Duration.millis(500), count, columns, offsetX, offsetYOnPlace, width, height);
        useTheAnimation();
        getChildren().addAll(this.imageView);
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