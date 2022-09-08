package fr.istic.aco.editor.Receiver;

import fr.istic.aco.editor.ConcretCommand.EmptyMemento;
import fr.istic.aco.editor.Memento;

import java.util.Optional;
import java.util.Stack;

public class UndoManagerImpl implements UndoManager {
	
	 private Stack<Memento> undoStack;
	    private Stack<Memento> redoStack;

	    public UndoManagerImpl(){
	        this.undoStack = new Stack<>();
	       // this.undoStack.add(new EmptyMemento());
	        this.redoStack = new Stack<>();
	    }

	@Override
	public Engine undo() {
		// TODO Auto-generated method stub
		 Engine engine = new EngineImpl();
	        Memento memento = undoStack.pop();
	        redoStack.add(memento);

	        if (undoStack.empty()){
	            engine = new EngineImpl();
	        }else {
	            engine.setMemento(undoStack.peek());
	        }

	        return engine;
	}

	@Override
	public Engine redo() {
		// TODO Auto-generated method stub
		 Engine engine = new EngineImpl();

	        Memento memento = redoStack.pop();
	        undoStack.add(memento);
	        engine.setMemento(memento);

	        return engine;
	}

	@Override
	public void save(Engine engine) {
		// TODO Auto-generated method stub
		 Engine engineToSave = new EngineImpl(engine);
	        Optional<Memento> memento = engineToSave.getMemento();
	        undoStack.add(memento.orElse(new EmptyMemento()));
	        redoStack.clear();
	}

}
