package sample.graphical;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.graphical.interfaces.PlayerRenderer;

public class PlayerRendererImpl implements PlayerRenderer {

    private Image playerImg;
    private ImageView playerImageView;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;

    @Override
    public void render() {
        this.playerImg = new Image(getClass().getResourceAsStream("../resources/playerSprite.png"));
        this.playerImageView = new ImageView(this.playerImg);
        this.playerImageView.setFitHeight(this.width);
        this.playerImageView.setFitWidth(this.height);
        this.initializeValues();
        this.playerImageView.setViewport(new Rectangle2D(this.offsetX, this.offsetY,this.width, this.height));
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public int getOffsetY() {
        return this.offsetY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public ImageView getPlayerImage() {
        return this.playerImageView;
    }

    private void initializeValues() {
        this.offsetX = 0;
        this.offsetY = 120;
        this.width = 60;
        this.height = 60;
    }
}
