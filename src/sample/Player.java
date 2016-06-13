package sample;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Player extends Pane {
    private Image playerImg = new Image(getClass().getResourceAsStream("resources/playerSprite.png"));
    private ImageView imageView = new ImageView(playerImg);
    private int count = 1;
    private int columns = 1;
    private int offsetX = 60;
    private int offsetY = 0;
    private int width = 60;
    private int height = 60;
    public SpriteAnimation animation;
    boolean isMovingRight, isMovingLeft;

    public Player() {
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        render();
        getChildren().addAll(this.imageView);
    }


    public void move() {
        if (isMovingRight) {
            this.offsetY = height * 2;
            this.setTranslateX(this.getTranslateX() + 6);
        } else if (isMovingLeft) {
            this.offsetY = height;
            this.setTranslateX(this.getTranslateX() - 6);
        }

        this.columns++;
        this.count++;
        this.offsetX += width;

        if (offsetX == width * 8) {
            offsetX = width;
        }
    }

    public void render() {
        animation = new SpriteAnimation(this.imageView, Duration.INDEFINITE, count - 1, columns, offsetX, offsetY, width, height);
        animation.play();
    }
}