package fr.istic.aco.editor.test;

import fr.istic.aco.editor.Receiver.Engine;
import fr.istic.aco.editor.Receiver.EngineImpl;
import fr.istic.aco.editor.Receiver.Selection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe Test de la Selection
 * @author SANOGO Kadidiatou
 * @version 1.0
 */

public class SelectionTest {

	  private Engine engine;
	  
	  @org.junit.jupiter.api.BeforeEach
	    void setUp() {
	        engine = new EngineImpl();
	    }

	  
	    @Test
	    @DisplayName("Buffer must be empty after initialisation")
	    void getSelection() {
	        Selection selection = engine.getSelection();
	        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
	        assertEquals("",engine.getBufferContents());
	    }


	    @Test
	    @DisplayName("Selection begin and end index must be same and equals to 0 after initialisation")
	    void selection(){
	        Selection selection = engine.getSelection();
	        int begin = selection.getBeginIndex();
	        int end = selection.getEndIndex();
	        assertEquals(0, begin);
	        assertEquals(0, end);
	        assertEquals(begin, end);

	    }

	    @Test
	    @DisplayName("Leve une exception IndexOutOfBoundsException si le begin index est inferieur a 0")
	    void selectionBeginIndex1(){
	        Selection selection = engine.getSelection();
	        assertThrows(IndexOutOfBoundsException.class, () -> {
	            selection.setBeginIndex(-1);
	        });

	    }

	    @Test
	    @DisplayName("Ne leve pas une exception IndexOutOfBoundsException si le begin index est >= 0")
	    void selectionBeginIndex2(){
	        Selection selection = engine.getSelection();
	        engine.insert("texte");
	        assertDoesNotThrow(() -> {
	            selection.setBeginIndex(1);
	        });

	    }

	    @Test
	    @DisplayName("Leve une exception IndexOutOfBoundsException si le endIndex est inferieur a 0 ou inferieur au beginIndex ou superieur au endIndex du buffer")
	    void selectionEndIndex1(){
	        Selection selection = engine.getSelection();
	        assertThrows(IndexOutOfBoundsException.class, () -> {
	            selection.setEndIndex(-1);
	        });
	    }
}
