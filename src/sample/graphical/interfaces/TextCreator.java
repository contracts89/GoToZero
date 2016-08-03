package sample.graphical.interfaces;
import javafx.scene.text.Text;

public interface TextCreator {

    Text createText(String type) throws ReflectiveOperationException;
}
