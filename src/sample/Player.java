package sample;


import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Player extends Pane {
    Image survivorImg = new Image(getClass().getResourceAsStream("resources/playerSprite.png"));
    ImageView imageView = new ImageView(survivorImg);
    int count = 4;
    int columns = 4;
    int offsetX = 0;
    int offsetY = 0;
    int width = 40;
    int height = 40;
    public SpriteAnimation animation;

    //char position on the "matrix"...
    int posX;
    int posY;

    public Player() {
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(this.imageView, Duration.millis(500), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(this.imageView);
    }

    public void move(int value) {

        }
    }