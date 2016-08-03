package sample.graphical;

import javafx.scene.text.Text;
import sample.graphical.interfaces.TextCreator;
import sample.graphical.texts.AbstractText;

import java.lang.reflect.Constructor;

public class TextCreatorImpl implements TextCreator {

    private static final String TEXT_PATH = "sample.graphical.texts.";

    @Override
    @SuppressWarnings("unchecked")
    public Text createText(String textClassName) throws ReflectiveOperationException {
        Class<AbstractText> abstractTextClass = (Class<AbstractText>) Class.forName(TEXT_PATH + textClassName);
        Constructor<AbstractText> abstractTextConstructor = abstractTextClass.getDeclaredConstructor();
        return abstractTextConstructor.newInstance();
    }
}
