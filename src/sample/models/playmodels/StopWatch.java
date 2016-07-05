package sample.models.playmodels;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.Duration;
import java.time.LocalTime;

import static javafx.application.Application.STYLESHEET_MODENA;

/**
 * Created by lgoychev on 6/27/16.
 */
public class StopWatch {

    private AnimationTimer timer;
    private Label stopwatch;
    public StopWatch() {
        this.createScene();
        this.setStopwatch(new Label());
    }

    public Label getStopwatch() {
        return this.stopwatch;
    }
    private void setStopwatch(Label stopwatch) {
        this.stopwatch = stopwatch;
        this.stopwatch.setTranslateX(0);// X position of Timer
        this.stopwatch.setTranslateY(60); // Y postion of Timer
        this.stopwatch.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 20));
        this.stopwatch.setTextFill(Color.WHITE);
    }

    public void stopTimer(){
        this.timer.stop();
    }
    public void createScene() {

        BooleanProperty running = new SimpleBooleanProperty(false);

        this.timer = new AnimationTimer() {

            private LocalTime startTime;


            public void handle(long now) {
                long elapsedSeconds = Duration.between(this.startTime, LocalTime.now()).getSeconds();

                long minutes = elapsedSeconds / 60;
                long seconds = elapsedSeconds % 60;
                stopwatch.setText(minutes + "Minutes " + seconds + " seconds");

            }

            public void start() {
                running.set(true);
                this.startTime = LocalTime.now();
                super.start();

            }

            public void stop() {
                running.set(false);
                super.stop();
            }
        };
        this.timer.start();

    }
}
