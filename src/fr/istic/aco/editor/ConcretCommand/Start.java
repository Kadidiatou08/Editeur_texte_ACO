package fr.istic.aco.editor.ConcretCommand;

import fr.istic.aco.editor.Command;
import fr.istic.aco.editor.Memento;
import fr.istic.aco.editor.Receiver.Recorder;

import java.util.Optional;
/**
 * Commande concrete qui permet de demarrer l'entregistrement de commandes
 *
 *@author Eddy KOKOYE & Kadidiatou SANOGO
 *@version 1.0
 */
public class Start implements Command {

    private final Recorder recorder;


    public Start(Recorder recorder){
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        if (recorder !=null){
            this.recorder.start();
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
