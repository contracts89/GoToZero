
package sample.animations;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import static sample.constants.PlayStateConstants.*;

public class SpriteAnimation extends Transition {

    private ImageView imageView;
    private int offsetX;
    private int offsetY;

    public SpriteAnimation(ImageView imageView, Duration duration, int offsetX, int offsetY) {
        this.imageView = imageView;
        this.setOffsetX(offsetX);
        this.setOffsetY(offsetY);
        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGTH));

    }

    private void setOffsetX(int x) {
        this.offsetX = x;
    }

    private void setOffsetY(int y) {
        this.offsetY = y;
    }

    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(PLAYER_SPRITE_COUNT * frac), PLAYER_SPRITE_COUNT - 1);
        final int x = (index % PLAYER_SPRITE_COLUMNS) * PLAYER_SPRITE_WIDTH + offsetX;
        final int y = (index / PLAYER_SPRITE_COLUMNS) * PLAYER_SPRITE_HEIGTH + this.offsetY;
        this.imageView.setViewport(new Rectangle2D(x, y, PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGTH));
    }
}
