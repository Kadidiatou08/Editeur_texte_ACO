package fr.istic.aco.editor;

import java.util.Optional;

/**
 * Defines a common interface for concrete commands.
 * @author SANOGO Kadidiatou
 * @version 1.0
 */
public interface Command {
	 /**
     * Calls an appropriate operation on an appropriate receiver.
     * 'Appropriates' are defined in concrete implementation of Command.
     */
	void execute();

	void setMemento(Memento memento);

	Optional<Memento> getMemento();

}
