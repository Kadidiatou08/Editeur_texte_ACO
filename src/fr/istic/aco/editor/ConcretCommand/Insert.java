package fr.istic.aco.editor.ConcretCommand;

import fr.istic.aco.editor.Command;
import fr.istic.aco.editor.Invoker.UiInvoker;
import fr.istic.aco.editor.Memento;
import fr.istic.aco.editor.Receiver.*;

import java.util.Optional;

/**
 * Commande concrete pour inserer un texte
 *
 * @author Eddy KOKOYE & Kadidiatou SANOGO
 * @version 1.0
 */
public class Insert implements Command{

    private final Engine engine;
    private final UiInvoker invoker;
    private final Recorder recorder;
    private final UndoManager undoManager;
    private String textToInsert;

    public Insert(Engine engine,UiInvoker invoker,Recorder recorder, UndoManager undoManager){
        this.engine = engine;
        this.invoker = invoker;
        this.recorder = recorder;
        this.undoManager = undoManager;
    }

    @Override
    public void execute() {
        if (invoker != null && engine !=null) {
            if (((RecorderImpl)this.recorder).isReplaying()){
                this.engine.insert(textToInsert);

            }else {
                    this.textToInsert = invoker.getText();
                    this.engine.insert(textToInsert);

                    if (((RecorderImpl) this.recorder).isRecording()){
                        this.recorder.save(this);
                    }
            }


            undoManager.save(this.engine);
        }
    }

    @Override
    public Optional<Memento> getMemento() {

        InsertMemento memento = new InsertMemento();

        memento.setText(textToInsert);
        return Optional.of(memento);
    }

    @Override
    public void setMemento(Memento m) {
        textToInsert = ((InsertMemento)m).getText();
    }


    /**
     * Classe interne qui va permettre de sauvegarder l'Etat de la commande Insert
     */
    private class InsertMemento implements Memento{

        private String text;

        void setText(String s){
            this.text = s;
        }

        String getText(){
            return this.text;
        }
    }
}
