package fr.istic.aco.editor.ConcretCommand;

import fr.istic.aco.editor.Command;
import fr.istic.aco.editor.Invoker.UiInvoker;
import fr.istic.aco.editor.Memento;
import fr.istic.aco.editor.Originator;
import fr.istic.aco.editor.Receiver.*;

import java.util.Optional;

/**
 * Commande concrete pour faire la selection de texte dans le Buffer
 *
 * @author Eddy KOKOYE & Kadidiatou SANOGO
 * @version 1.0
 */
public class SelectionChange implements Command, Originator {

    private final Selection selection;
    private final UiInvoker invoker;
    private int beginIndex;
    private int endIndex;
    private final Recorder recorder;
    private final UndoManager undoManager;
    private final Engine engine;

    public SelectionChange(Selection selection, UiInvoker invoker,Recorder recorder,UndoManager undoManager, Engine engine){
        this.selection = selection;
        this.invoker = invoker;
        this.recorder = recorder;
        this.undoManager = undoManager;
        this.engine = engine;
    }

    @Override
    public void execute() {
        if (selection != null && invoker != null) {

            if (((RecorderImpl)this.recorder).isReplaying()){
                this.selection.setBeginIndex(beginIndex);
                this.selection.setEndIndex(endIndex);
            }else {
                beginIndex = invoker.getBeginIndex();
                endIndex = invoker.getEndIndex();

                this.selection.setBeginIndex(beginIndex);
                this.selection.setEndIndex(endIndex);

                if (((RecorderImpl) this.recorder).isRecording()){
                    this.recorder.save(this);
                }
            }

            this.undoManager.save(this.engine);
        }
    }

    @Override
    public Optional<Memento> getMemento() {

        SelectionMemento memento = new SelectionMemento();
        memento.setBeginIndex(beginIndex);
        memento.setEndIndex(endIndex);
        return Optional.of(memento);
    }

    @Override
    public void setMemento(Memento m) {

        beginIndex = ((SelectionMemento)m).getBeginIndex();
        endIndex = ((SelectionMemento)m).getEndIndex();
    }

    /**
     * Classe interne qui permet de sauvegarder la selection
     */
    private class SelectionMemento implements Memento{

        private int beginIndex;
        private int EndIndex;

        void setBeginIndex(int beginIndex) {
            this.beginIndex = beginIndex;
        }

        public int getBeginIndex() {
            return beginIndex;
        }

        public void setEndIndex(int endIndex) {
            EndIndex = endIndex;
        }

        public int getEndIndex() {
            return EndIndex;
        }
    }


}
