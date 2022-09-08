package fr.istic.aco.editor.Receiver;

import fr.istic.aco.editor.Command;
import fr.istic.aco.editor.ConcretCommand.EmptyMemento;
import fr.istic.aco.editor.Memento;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

import fr.istic.aco.editor.Command;

public class RecorderImpl implements Recorder {

	 private List<Pair<Command, Memento>> recorderList;
	    private boolean isRecording;
	    private boolean isReplaying;


	    public RecorderImpl(){
	        this.isRecording = false;
	        this.isReplaying = false;
	        recorderList = new ArrayList<>();
	    }

	
	@Override
	public void save(Command command) {
		// TODO Auto-generated method stub
		 Memento memento = command.getMemento().orElse(new EmptyMemento());
	        this.add(command,memento);
	}
	
	private void add(Command command, Memento memento){
        recorderList.add(new Pair<>(command,memento));
    }
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		this.isRecording = true;
        this.isReplaying = false;
        this.recorderList = new ArrayList<>();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		this.isRecording = false;
	}

	@Override
	public void replay() {
		// TODO Auto-generated method stub
		this.isRecording =false;
		this.isReplaying = true;
		 for (Pair<Command, Memento> pair : recorderList){

	            Command command = pair.getKey();
	            Memento memento = pair.getValue();
	            command.setMemento(memento);
	            command.execute();
	        }
	}
	
	public boolean isReplaying() {
    return isReplaying;
    }

    public boolean isRecording() {
       return isRecording;
    }

}
