package sample;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Player extends Pane {
    Image playerImg = new Image(getClass().getResourceAsStream("resources/playerSprite.png"));
    ImageView imageView = new ImageView(playerImg);
    int count = 4;
    int columns = 4;
    int offsetX = 0;
    int offsetY = 0;
    int width = 40;
    int height = 40;
    public SpriteAnimation animation;
    boolean isMovingRight ;

    public Player() {
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(this.imageView, Duration.millis(500), count-1, columns, offsetX, offsetY, width, height);
        getChildren().addAll(this.imageView);
    }

    public void move() {
        if(isMovingRight){
            this.setTranslateX(this.getTranslateX()+1);
        }else{
            this.setTranslateX(this.getTranslateX()-1);
        }
    }
}