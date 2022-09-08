package fr.istic.aco.editor.Receiver;

import fr.istic.aco.editor.Memento;
import java.util.Optional;

/**Implementation de l'API
*
*@author SANOGO Kadidiatou
*@version 1.0
*/
public class EngineImpl implements Engine {
   
	private Buffer buffer;
    private Clipboard clipboard;
    private Selection selection;

    public EngineImpl(){
        buffer = new Buffer();
        clipboard = new Clipboard();
        selection = new SelectionImpl(buffer);
    }

    public EngineImpl(Engine otherEngine){
        buffer = new Buffer(((EngineImpl)otherEngine).buffer);
        clipboard = new Clipboard(((EngineImpl)otherEngine).getClipboard().getContent());
        selection = new SelectionImpl(buffer);
        selection.setBeginIndex(((EngineImpl) otherEngine).selection.getBeginIndex());
        selection.setEndIndex(((EngineImpl) otherEngine).selection.getEndIndex());
    }
	
	/**
     * Provides access to the selection control object
     *
     * @return the selection object
     */
    @Override
    public Selection getSelection() {
        // TODO
    	return this.selection;
    }

    /**
     * Provides the whole contents of the buffer, as a string
     *
     * @return a copy of the buffer's contents
     */
    @Override
    public String getBufferContents() {
        // TODO
        return this.buffer.getContent();
    }

    /**
     * Provides the clipboard contents
     *
     * @return a copy of the clipboard's contents
     */
    @Override
    public String getClipboardContents() {
        // TODO
        return this.clipboard.getContent();
    }

    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    @Override
    public void cutSelectedText() {
        // TODO
    	try {

            copySelectedText();
            delete();

        }catch (Exception e){
            System.out.println("Error -> " + e.getMessage());
        }
    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    @Override
    public void copySelectedText() {
        // TODO
    	try {
            int beginIndex = this.selection.getBeginIndex();
            int endIndex = this.selection.getEndIndex();
            String text = buffer.getContent().substring(beginIndex, endIndex);

            clipboard.setContent(text);
        }catch (Exception e){ System.err.println("Error -> " + e.getMessage()); }

    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    @Override
    public void pasteClipboard() {
        // TODO
    	try {
    		insert(clipboard.getContent());
    	}catch (Exception e) {
    		System.err.println("Error -> +e.getMessage()");
    		}
    	}

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
            int beginIndex = this.selection.getBeginIndex();
            int endIndex = this.selection.getEndIndex();

            delete();
            this.buffer.setContent(s,beginIndex);
            //On met a jour end index du buffer
            this.buffer.setEndIndex(buffer.getContent().length());

            //on met a jour la selection
            beginIndex = this.selection.getBeginIndex();
            endIndex = this.selection.getEndIndex();

            selection.setBeginIndex(beginIndex+ s.length());
            selection.setEndIndex(endIndex + s.length());
    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
    	 try {
             int beginIndex = selection.getBeginIndex();
             int endIndex = selection.getEndIndex();

             buffer.deleteSelection(beginIndex,endIndex);
             //met a jour l'index de fin du buffer
             buffer.setEndIndex(buffer.getContent().length());

             //met a jour la selection
             selection.setBeginIndex(beginIndex);
             selection.setEndIndex(beginIndex);

         }catch (Exception e){
             System.err.println("Error -> " +e.getMessage());
         }
    }
    
    @Override
    public Optional<Memento> getMemento() {
        EngineMemento memento = new EngineMemento(buffer,clipboard,selection);
        return Optional.of(memento);
    }

    @Override
    public void setMemento(Memento memento) {

        this.buffer = ((EngineMemento)memento).mBuffer;
        this.selection = ((EngineMemento)memento).mSelection;
        this.clipboard = ((EngineMemento)memento).mClipboard;
    }

    public Buffer getBuffer() {
        return buffer;
    }

    public Clipboard getClipboard() {
        return clipboard;
    }

    private class EngineMemento implements Memento{

        Buffer mBuffer;
        Clipboard mClipboard;
        Selection mSelection;

        private EngineMemento(Buffer b,Clipboard clipb,Selection s){
            mBuffer =b;
            mClipboard = clipb;
            mSelection = s;
        }

    }
}
