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
    int offsetX = 60;
    int offsetY = 0;
    int width = 60;
    int height = 60;
    public SpriteAnimation animation;
    boolean isMovingRight ;

    public Player() {
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));


        getChildren().addAll(this.imageView);
        animation = new SpriteAnimation(this.imageView,Duration.millis(500), count-1, columns, offsetX, offsetY+180, width, height);

    }


    public void move() {
        boolean isBeginning;
        if (isBeginning)
        if(isMovingRight){
            this.setTranslateX(this.getTranslateX()+2);
           animation=new SpriteAnimation(this.imageView,Duration.millis(500),count-1,columns,offsetX,offsetY+120,width,height);
        }else{
            this.setTranslateX(this.getTranslateX()-2);
            animation=new SpriteAnimation(this.imageView,Duration.millis(500),count-1,columns,offsetX,offsetY+60,width,height);
        }
    }
}