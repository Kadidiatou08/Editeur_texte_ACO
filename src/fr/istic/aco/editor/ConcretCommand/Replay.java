package fr.istic.aco.editor.ConcretCommand;

import fr.istic.aco.editor.Command;
import fr.istic.aco.editor.Memento;
import fr.istic.aco.editor.Receiver.Recorder;

import java.util.Optional;

/**
 * Commande concrete pour faire le Replay des commandes enregistreés
 *
 * @author Eddy KOKOYE & Kadidiatou SANOGO
 * @version 1.0
 */
public class Replay implements Command {

    private final Recorder recorder;

    public Replay(Recorder recorder){
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        if (this.recorder !=null){
            this.recorder.replay();
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
