package sample;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Player extends Pane {
    private final static int LEFT_WALL_BOUNDARY = -10;
    private final static int RIGHT_WALL_BOUNDARY = 850;
    private Image playerImg = new Image(getClass().getResourceAsStream("resources/playerSprite.png"));
    private ImageView imageView = new ImageView(playerImg);
    private int count = 1;
    private int columns = 1;
    private int offsetX = 60;
    private int offsetY = 0;
    private int width = 60;
    private int height = 60;
    private SpriteAnimation animation;
    boolean isMovingRight, isMovingLeft;

    public Player() {
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        render();
        getChildren().addAll(this.imageView);
    }


    void move() {
        if (isMovingRight) {
            this.offsetY = height * 2;
            if (this.getTranslateX() < RIGHT_WALL_BOUNDARY) { //CHECK TO SEE IF RIGHT WALL IS REACHED
                this.setTranslateX(this.getTranslateX() + 5);
            }
        } else if (isMovingLeft) {
            this.offsetY = height;
            if (this.getTranslateX() > LEFT_WALL_BOUNDARY) { //CHECK TO SEE IF LEFT WALL IS REACHED
                this.setTranslateX(this.getTranslateX() - 5);
            }
        }
        this.columns++;
        this.count++;
        this.offsetX += width;

        if (offsetX == width * 8) {
            offsetX = width;
        }
    }
    void stayAtPos(){
        this.offsetY=0;
    }
    void render() {
        animation = new SpriteAnimation(this.imageView, Duration.INDEFINITE, count - 1, columns, offsetX, offsetY, width, height);
        animation.play();
    }
}