package fr.istic.aco.editor.Invoker;

import fr.istic.aco.editor.Command;

import java.io.IOException;
import java.io.InputStream;

/**
 * Invoker
 * @author Eddy KOKOYE & Kadidiatou SANOGO
 * @version 1.0
 */
public interface UiInvoker {

    /**
     * Starts the reading of the read stream set by setReadStream operation
     */
    void runInvokerLoop();

    /**
     * Stops the read stream loop now.
     */
    void stopLoop();

    /**
     * Sets the read stream that be be used by runInvokerLoop
     *
     * @param inputStream the read stream
     * @throws IllegalArgumentException if inputStream is null
     */
    void setReadStream(InputStream inputStream);


    /**
     * Registers a new keyword/command pair
     *
     * @param keyword a non-null String
     * @param cmd     a non-null Command reference
     * @throws java.lang.IllegalArgumentException for null parameters
     */
    void addCommand(String keyword, Command cmd);

    String getText();

    void setTextForTest(String s);

    void setTextUI();

    int getBeginIndex();

    void setBeginIndexForTest(int index);
    void setBeginIndexUI();

    void setEndIndexForTest(int index);
    void setEndIndexUI();

    int getEndIndex();

}