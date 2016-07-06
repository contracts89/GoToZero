package sample;

import javafx.application.Application;
import sample.constants.Constants;
import sample.stages.MenuStage;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Application.launch(MenuStage.class,args);
        Constants.createHelpText();
    }
}
