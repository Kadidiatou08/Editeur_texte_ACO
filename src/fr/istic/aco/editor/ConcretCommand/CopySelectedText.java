package fr.istic.aco.editor.ConcretCommand;

import fr.istic.aco.editor.Command;
import fr.istic.aco.editor.Memento;
import fr.istic.aco.editor.Originator;
import fr.istic.aco.editor.Receiver.Engine;
import fr.istic.aco.editor.Receiver.Recorder;
import fr.istic.aco.editor.Receiver.RecorderImpl;
import fr.istic.aco.editor.Receiver.UndoManager;

import java.util.Optional;

/**
 * Commande concrete pour la copie du texte selectionné
 *
 * @author Eddy KOKOYE & Kadidiatou SANOGO
 * @version 1.0
 */
public class CopySelectedText implements Command, Originator {

    private final Engine engine;
    private final Recorder recorder;
    private final UndoManager undoManager;

    public CopySelectedText(Engine engine,Recorder recorder,UndoManager undoManager){
        this.engine = engine;
        this.recorder = recorder;
        this.undoManager = undoManager;
    }
    @Override
    public void execute() {
        if (engine != null) {
            this.engine.copySelectedText();
            if (((RecorderImpl) this.recorder).isRecording()){
                this.recorder.save(this);
            }

            this.undoManager.save(this.engine);
        }
    }


    @Override
    public Optional<Memento> getMemento() {
        return Optional.empty();
    }

    @Override
    public void setMemento(Memento m) {

    }
}
