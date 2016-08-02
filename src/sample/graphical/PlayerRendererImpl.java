package sample.graphical;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.graphical.interfaces.PlayerRenderer;

import static sample.constants.PlayStateConstants.*;

public class PlayerRendererImpl implements PlayerRenderer {

    private Image playerImg;
    private ImageView playerImageView;

    @Override
    public void render() {
        this.playerImg = new Image(this.getClass().getResourceAsStream(PLAYER_SPRITE_IMAGE));
        this.playerImageView = new ImageView(this.playerImg);
        this.playerImageView.setFitHeight(PLAYER_SPRITE_HEIGTH);
        this.playerImageView.setFitWidth(PLAYER_SPRITE_WIDTH);
        this.playerImageView.setViewport(
                new Rectangle2D(PLAYER_SPRITE_RENDERER_INITIAL_OFFSET_X,
                                PLAYER_SPRITE_RENDERER_INITIAL_OFFSET_Y,
                                PLAYER_SPRITE_WIDTH,
                                PLAYER_SPRITE_HEIGTH));
    }

    @Override
    public ImageView getPlayerImage() {
        return this.playerImageView;
    }

}
