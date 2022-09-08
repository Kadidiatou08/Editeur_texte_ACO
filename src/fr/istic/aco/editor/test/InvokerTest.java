package fr.istic.aco.editor.test;


import fr.istic.aco.editor.Command;
import fr.istic.aco.editor.ConcretCommand.*;
import fr.istic.aco.editor.Invoker.UiInvoker;
import fr.istic.aco.editor.Invoker.UiInvokerImpl;
import fr.istic.aco.editor.Receiver.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe Test de l'Invoker
 *
 * @author Sanogo Kadidiatou
 * @version 1.0
 */
public class InvokerTest {

    private Engine engine;
    private Command insert, delete, selectionChange, cut,copy, paste;
    private UiInvoker ui;
    private Recorder recorder;
    private UndoManager undoManager;

    @BeforeEach
    void setUp(){
        engine = new EngineImpl();
        recorder = new RecorderImpl();
        ui = new UiInvokerImpl();
        undoManager = new UndoManagerImpl();
        insert = new Insert(engine,ui,recorder,undoManager);
        delete = new Delete(engine,recorder,undoManager);
        cut = new CutSelectedText(engine,recorder,undoManager);
        copy = new CopySelectedText(engine,recorder,undoManager);
        paste = new PasteClipboard(engine,recorder,undoManager);
        selectionChange = new SelectionChange(engine.getSelection(),ui,recorder,undoManager,engine);

    }

    @Test
    @DisplayName("Test d'une serie de commande concrete")
    public void testConcretCommand() throws Exception{


        ui.setTextForTest("le partage");

        //insert "le partage"
        insert.execute();

        //cut "le partage"

        ui.setBeginIndexForTest(0);
        ui.setEndIndexForTest(10);
        selectionChange.execute();

        cut.execute();

        //insert La miage
        ui.setTextForTest("La miage ");
        insert.execute();

        //coller "Le partage dans le buffer juste devant "La miage "

        ui.setBeginIndexForTest(9);
        ui.setEndIndexForTest(9);
        selectionChange.execute();

        paste.execute();

        ui.setTextForTest(" c'est");
        ui.setBeginIndexForTest(8);
        ui.setEndIndexForTest(8);
        selectionChange.execute();

        insert.execute();

        assertEquals("La miage c'est le partage", engine.getBufferContents());
    }

    @Test
    @DisplayName("Test de la commande Delete")
    void testDelete(){


        ui.setTextForTest("MIAGE ISTIC");

        insert.execute();

        ui.setBeginIndexForTest(5);
        ui.setEndIndexForTest(11);
        selectionChange.execute();


        delete.execute();

        assertEquals("MIAGE", engine.getBufferContents());
    }

    @Test
    @DisplayName("Test de commande Copy")
    void testCopy(){


        ui.setTextForTest("MIAGE");
        insert.execute();
        ui.setBeginIndexForTest(0);
        ui.setEndIndexForTest(5);
        selectionChange.execute();

        copy.execute();

        assertEquals("MIAGE", engine.getClipboardContents());
    }
}


