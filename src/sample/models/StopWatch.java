package sample.models;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by lgoychev on 6/27/16.
 */
public class StopWatch {
    private Stage primaryStage;
    public StopWatch() {
        this.createScene();
    }
    public Label stopwatch = new Label();
    public void createScene() {

        BooleanProperty running = new SimpleBooleanProperty(false);

        AnimationTimer timer = new AnimationTimer() {

            private LocalTime startTime ;


            public void handle(long now) {
                long elapsedSeconds = Duration.between(startTime, LocalTime.now()).getSeconds();
                long hours = elapsedSeconds / 360;
                long minutes = elapsedSeconds / 60 ;
                long seconds = elapsedSeconds % 60 ;
                stopwatch.setText("Time: "+hours + " hours " +minutes +" minutes "+seconds + " seconds");


            }

            public void start() {
                running.set(true);
                startTime = LocalTime.now();
                super.start();

            }

            public void stop() {
                running.set(false);
                super.stop();
            }
        };
        timer.start();

    }
}
