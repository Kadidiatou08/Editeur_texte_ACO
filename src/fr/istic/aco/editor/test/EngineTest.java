package fr.istic.aco.editor.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.Receiver.Engine;
import fr.istic.aco.editor.Receiver.EngineImpl;
import fr.istic.aco.editor.Receiver.Selection;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe Test du Engine
 * @author Sanogo Kadidiatou
 * @version 1.0
 */
class EngineTest {

    private Engine engine;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }

    private void todo() {
        fail("Unimplemented test");
    }
    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        //todo();
        assertTrue(engine.getBufferContents().isEmpty());
        String bufferContent = "contenu du buffer";
        // ajouter ce contenu au buffer
        engine.insert(bufferContent);
        assertEquals(bufferContent, engine.getBufferContents());
        
    }

    @Test
    void getClipboardContents() {
        //todo();
        String textToCopy = "mon texte";
        engine.insert(textToCopy);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(2);
        engine.copySelectedText();
        assertFalse(engine.getClipboardContents().isEmpty(), "Le Clipboard ne devrait pas etre vide apres la copie");
        assertEquals("le", engine.getClipboardContents());
    }
    

    @Test
    @DisplayName("Test du CUT du selected text")
    void cutSelectedText() {
       // todo();
    	 String textToCut = "le texte";
         engine.insert(textToCut);
         Selection selection = engine.getSelection();
         selection.setBeginIndex(0);
         selection.setEndIndex(5);
         engine.cutSelectedText();
         assertFalse(engine.getBufferContents().isEmpty(), "Le Clipboard ne devrait pas etre vide apres le cut");
         assertEquals("texte", engine.getBufferContents());

    }
    
    @Test
    @DisplayName("Test du CUT quand rien n'est selectionner")
    void cutEmptySelectedText(){
        String text = "Text";
        engine.insert(text);

        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(0);

        engine.cutSelectedText();
        assertEquals("", engine.getClipboardContents());
    }


    @Test
    @DisplayName("Test de la copy, si le text selectionne est bien present dans le Clipboard")
    void copySelectedText() {
        //todo();
    	String text = "le texte";
        //Selection selection = engine.getSelection();
        engine.insert(text);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(2);
        engine.copySelectedText();
        assertEquals("le",engine.getClipboardContents());
   
    }

    @Test
    @DisplayName("Test du paste, recupere le texte du clipboard et le met dans la zone selectionnee du Buffer")
    void pasteClipboard() {
        //todo();
        engine.insert("Goodbye");

        //Copie du mot goodbye dans le clipboard
        Selection select = engine.getSelection();
        select.setBeginIndex(0);
        select.setEndIndex(7);
        engine.copySelectedText();

        // Selection du mot Hello
        String textInit = "Hello world";
        engine.insert(textInit);
       // select = engine.getSelection();
        select.setBeginIndex(0);
        select.setEndIndex(5);

        //Remplacement par Goodbye
        engine.pasteClipboard();
        assertEquals( "Goodbye world",engine.getBufferContents());
    	 }
    
    @Test
    @DisplayName("Inserer un text dans la selection")
    void insertText1(){

        engine.insert("ADO");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(1);
        selection.setEndIndex(2);
        engine.insert("C");
        assertEquals("ACO", engine.getBufferContents());
    }

    @Test
    @DisplayName("Inserer un text a la fin du Buffer")
    void insertText2(){

        engine.insert("ABC");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(3);
        selection.setEndIndex(3);
        engine.insert("**");
        assertEquals("ABC**", engine.getBufferContents());
    }

    @Test
    @DisplayName("Inserer un text au debut du Buffer")
    void insertText3(){

        engine.insert("ABC");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(0);
        engine.insert("**");
        assertEquals("**ABC", engine.getBufferContents());
    }

    @Test
    @DisplayName("Inserer un text au milieu du Buffer")
    void insertText4(){

        engine.insert("ABC");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(2);
        selection.setEndIndex(2);
        engine.insert("**");
        assertEquals("AB**C", engine.getBufferContents());
    }

    @Test
    @DisplayName("Inserer un text vide")
    void insertText5(){

        engine.insert("ABC");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        engine.insert("");
        assertEquals("", engine.getBufferContents());
    }

    @Test
    @DisplayName("Supprimer tout le contenu du Buffer")
    void deleteText1(){
        engine.insert("ABC");

        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        engine.delete();
        assertTrue(engine.getBufferContents().isEmpty());
    }

    @Test
    @DisplayName("Supprimer tout le contenu du Buffer")
    void deleteText2(){
        engine.insert("ABBAHC");

        Selection selection = engine.getSelection();
        selection.setBeginIndex(2);
        selection.setEndIndex(5);

        engine.delete();

        assertEquals("ABC",engine.getBufferContents());
    }

    @Test
    @DisplayName("Supprimer un espace vide")
    void deleteText3(){
        engine.insert("ABC");

        Selection selection = engine.getSelection();
        selection.setBeginIndex(2);
        selection.setEndIndex(2);

        engine.delete();

        assertEquals("ABC",engine.getBufferContents());
    }
    
    @Test
    @DisplayName("Test si rien n'a ete selectionner")
    void testNothingSelected(){
        String contenue = "Mon texte";
        engine.insert(contenue);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(0);
        assertEquals(0, selection.getBeginIndex());
        assertEquals(0, selection.getEndIndex());

    }
}
