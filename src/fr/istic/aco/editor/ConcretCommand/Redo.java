package fr.istic.aco.editor.ConcretCommand;

import fr.istic.aco.editor.Command;
import fr.istic.aco.editor.Memento;
import fr.istic.aco.editor.Receiver.Engine;
import fr.istic.aco.editor.Receiver.EngineImpl;
import fr.istic.aco.editor.Receiver.UndoManager;

import java.util.Optional;

/**
 * Commande concrete pour faire le Redo
 *
 * @author Eddy KOKOYE & Kadidiatou SANOGO
 * @version 1.0
 */
public class Redo implements Command {

    private final UndoManager undoManager;
    private Engine engine;

    public Redo(UndoManager undoManager){
        this.undoManager = undoManager;
        this.engine= new EngineImpl();
    }

    @Override
    public void execute() {
        this.engine = undoManager.redo();
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public Optional<Memento> getMemento() {
        return Optional.empty();
    }

    @Override
    public void setMemento(Memento m) {

    }
}
