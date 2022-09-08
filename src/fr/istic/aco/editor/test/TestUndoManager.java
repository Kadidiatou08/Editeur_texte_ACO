package fr.istic.aco.editor.test;

import fr.istic.aco.editor.Command;
import fr.istic.aco.editor.ConcretCommand.*;
import fr.istic.aco.editor.Invoker.UiInvoker;
import fr.istic.aco.editor.Invoker.UiInvokerImpl;
import fr.istic.aco.editor.Receiver.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUndoManager {
	
	private Engine engine;
    private Command insert, delete, cut, copy, paste, selectionChange, undo, redo;
    private UiInvoker ui;
    private Recorder recorder;
    private UndoManager undoManager;

    @BeforeEach
    void setUp() {
        engine = new EngineImpl();
        recorder = new RecorderImpl();
        ui = new UiInvokerImpl();
        undoManager = new UndoManagerImpl();
        delete = new Delete(engine, recorder, undoManager);
        insert = new Insert(engine, ui, recorder, undoManager);
        cut = new CutSelectedText(engine, recorder, undoManager);
        copy = new CopySelectedText(engine, recorder, undoManager);
        paste = new PasteClipboard(engine, recorder, undoManager);
        selectionChange = new SelectionChange(engine.getSelection(),ui,recorder,undoManager,engine);

        undo = new Undo(undoManager);
        redo = new Redo(undoManager);
    }

    @Test
    @DisplayName("Test du Undo d'une action d'Insertion")
    public void testInsertUndo(){
        String text = "Bonjour";

        ui.setTextForTest(text);
        insert.execute();

        ui.setTextForTest(" ISTIC");
        insert.execute();

        undo.execute();
        engine = ((Undo)undo).getEngine();

        assertEquals("Bonjour", engine.getBufferContents());

    }

    @Test
    @DisplayName("Test du redo d'une action d'insertion")
    public void testInsertRedo(){
        String text = "Bonjour";

        ui.setTextForTest(text);
        insert.execute();

        ui.setTextForTest(" ISTIC");
        insert.execute();

        undo.execute();
        engine = ((Undo)undo).getEngine();
        redo.execute();
        engine = ((Redo)redo).getEngine();

        assertEquals("Bonjour ISTIC", engine.getBufferContents());
    }

   @Test
   @DisplayName("Test du Undo d'une action d'Insertion")
    public void testSelectionUndo(){

       String text = "Bonjour";

       ui.setTextForTest(text);
       insert.execute();

       ui.setBeginIndexForTest(0);
       ui.setEndIndexForTest(7);
       selectionChange.execute();

       ui.setBeginIndexForTest(0);
       ui.setEndIndexForTest(3);
       selectionChange.execute();

       undo.execute();
       engine = ((Undo)undo).getEngine();

       assertEquals(0, engine.getSelection().getBeginIndex());
       assertEquals(7, engine.getSelection().getEndIndex());

   }

    @Test
    @DisplayName("Test du Redo d'une action d'Insertion")
    public void testSelectionRedo(){

        String text = "Hello";

        ui.setTextForTest(text);
        insert.execute();

        ui.setBeginIndexForTest(0);
        ui.setEndIndexForTest(5);
        selectionChange.execute();

        ui.setBeginIndexForTest(0);
        ui.setEndIndexForTest(3);
        selectionChange.execute();

        undo.execute();
        engine = ((Undo)undo).getEngine();

        redo.execute();
        engine = ((Redo)redo).getEngine();

        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(3, engine.getSelection().getEndIndex());
    }

}
