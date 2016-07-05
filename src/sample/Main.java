package sample;

import javafx.application.Application;
import sample.constants.Constants;
import sample.stages.MenuStage;

 /**
 * Created by KNikov on 30/06/2016.
 */
public class Main {

    public static void main(String[] args) {
        Application.launch(MenuStage.class,args);
        Constants.createHelpText();
    }
}
