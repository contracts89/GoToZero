package sample.models.playmodels;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by lgoychev on 6/27/16.
 */
public class StopWatch {

    private AnimationTimer timer;
    private Label stopwatch = new Label();
    public StopWatch() {
        this.createScene();
    }

    public Label getStopwatch() {
        return this.stopwatch;
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
