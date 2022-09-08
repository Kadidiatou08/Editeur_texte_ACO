package fr.istic.aco.editor.test;

import com.sun.webkit.Invoker;
import fr.istic.aco.editor.Command;
import fr.istic.aco.editor.ConcretCommand.*;
import fr.istic.aco.editor.Invoker.UiInvoker;
import fr.istic.aco.editor.Invoker.UiInvokerImpl;
import fr.istic.aco.editor.Receiver.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe Test de Recorder
 *
 * @author Sanogo Kadidiatou
 * @version 1.0
 */
public class RecorderTest {
		private Engine  engine;
	    private Command insert, delete, selectionChange, cut,copy, paste;
	    private UiInvoker ui;
	    private Recorder recorder;
	    private Command replay, start, stop;
	    private UndoManager undoManager;

	    @BeforeEach
	    void setUp(){
	       engine = new EngineImpl();
	        recorder = new RecorderImpl();
	        ui = new UiInvokerImpl();
	        insert = new Insert(engine,ui,recorder,undoManager);
	        delete = new Delete(engine,recorder,undoManager);
	        copy = new CopySelectedText(engine,recorder,undoManager);
	        cut = new CutSelectedText(engine,recorder,undoManager);
	        paste = new PasteClipboard(engine,recorder,undoManager);
	        replay = new Replay(recorder);
	        start = new Start(recorder);
	        stop = new Stop(recorder);

	        selectionChange = new SelectionChange(engine.getSelection(),ui,recorder,undoManager,engine);

	    }

	    @Test
	    @DisplayName("Test du replay d'une action insert(a)")
	    void replayInsertTest(){

	        String chaine = "a";
	        ui.setTextForTest(chaine);

	        start.execute();
	        insert.execute();
	        stop.execute();

	        chaine = "b";
	        ui.setTextForTest(chaine);
	        insert.execute();


	        replay.execute();

	        replay.execute();


	        assertEquals("abaa", engine.getBufferContents());

	    }

	    @Test
	    @DisplayName("Replay d'une action de selection")
	    void replaySelectionTest(){


	        String chaine = "La miage c'est le partage";
	        ui.setTextForTest(chaine);

	        insert.execute();

	        start.execute();
	        System.out.println("Debut sauvegarde");

	        ui.setBeginIndexForTest(3);
	        ui.setEndIndexForTest(8);
	        selectionChange.execute();

	        stop.execute();
	        System.out.println("Fin sauvegarde");

	        ui.setBeginIndexForTest(14);
	        ui.setEndIndexForTest(25);
	        selectionChange.execute();

	        chaine = "PARTAGER";
	        ui.setTextForTest(chaine);
	        insert.execute();

	        replay.execute();

	        cut.execute();

	        assertEquals("miage", engine.getClipboardContents());
	    }

	    @Test
	    @DisplayName("Test du Replay d'une action delete")
	    void replayDeleteTest(){
	        String chaine = "projet editor";

	        ui.setTextForTest(chaine);
	        insert.execute();

	        ui.setBeginIndexForTest(7);
	        ui.setEndIndexForTest(13);
	        selectionChange.execute();


	        start.execute();
	        delete.execute();
	        stop.execute();

	        ui.setBeginIndexForTest(0);
	        ui.setEndIndexForTest(6);

	        selectionChange.execute();


	        replay.execute();

	        assertEquals(" ", engine.getBufferContents());
	    }
	    
	    @Test
	    @DisplayName("Test du Replay d'une action Copy")
	    void replayCopyTest(){

	        String chaine = "projet editor";
	        ui.setTextForTest(chaine);
	        insert.execute();

	        ui.setBeginIndexForTest(0);
	        ui.setEndIndexForTest(6);
	        selectionChange.execute();


	        start.execute();
	        copy.execute();
	        stop.execute();

	        ui.setBeginIndexForTest(7);
	        ui.setEndIndexForTest(13);
	        selectionChange.execute();


	        replay.execute();

	        assertEquals("editor",engine.getClipboardContents());
	    }

	    @Test
	    @DisplayName("Test du Replay d'une action CUT")
	    void replayCutTest(){
	        String chaine = "projet editor";

	        ui.setTextForTest(chaine);
	        insert.execute();

	        ui.setBeginIndexForTest(7);
	        ui.setEndIndexForTest(13);
	        selectionChange.execute();

	        start.execute();
	        cut.execute();
	        stop.execute();

	        ui.setBeginIndexForTest(0);
	        ui.setEndIndexForTest(6);
	        selectionChange.execute();

	        replay.execute();

	        assertEquals("projet",engine.getClipboardContents());
	    }

	    @Test
	    @DisplayName("Test du Replay d'une action paste")
	    void replayPasteTest(){
	        String chaine = "MiageIstic";

	        ui.setTextForTest(chaine);
	        insert.execute();

	        ui.setBeginIndexForTest(5);
	        ui.setEndIndexForTest(10);
	        selectionChange.execute();

	        copy.execute();

	        ui.setBeginIndexForTest(0);
	        ui.setEndIndexForTest(0);
	        selectionChange.execute();



	        start.execute();
	        paste.execute();
	        stop.execute();

	       replay.execute();

	       assertEquals("IsticIsticMiageIstic",engine.getBufferContents());

	    }

	    @Test
	    @DisplayName("Replay d'une serie d'actions (insert, cut, paste, selection")
	    void replayLongSequenceTest(){

	        String chaine = "le partage";
	        start.execute();

	        ui.setTextForTest(chaine);

	        //insert "le partage"
	        insert.execute();

	        //cut "le partage"

	        ui.setBeginIndexForTest(0);
	        ui.setEndIndexForTest(10);
	        selectionChange.execute();

	        cut.execute();

	        //insert La miage
	        chaine = "La miage ";
	        ui.setTextForTest(chaine);
	        insert.execute();

	        //coller "Le partage dans le buffer juste devant "La miage "

	        ui.setBeginIndexForTest(9);
	        ui.setEndIndexForTest(9);
	        selectionChange.execute();

	        paste.execute();

	        //inserer "c'est" dans le buffer pour avoir "La miage c'est le partage"
	        chaine = " c'est";
	        ui.setTextForTest(chaine);
	        ui.setBeginIndexForTest(8);
	        ui.setEndIndexForTest(8);
	        selectionChange.execute();

	        insert.execute();



	        stop.execute();

	        ui.setBeginIndexForTest(0);
	        ui.setEndIndexForTest(25);
	        selectionChange.execute();
	        delete.execute();

	        replay.execute();

	        System.out.println("Print de fin");
	        System.out.println(engine.getBufferContents());

	        assertEquals("La miage c'est le partage",engine.getBufferContents());
	    }

}
