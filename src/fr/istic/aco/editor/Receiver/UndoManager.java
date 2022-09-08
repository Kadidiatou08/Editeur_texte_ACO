package fr.istic.aco.editor.Receiver;

/**
 * Le caretaker pour le undo/redo
 * @author SANOGO Kadidiatou
 * @version 1.0
 */
public interface UndoManager {
	/**
     * Annule la derniere action
     * @return le Engine dans l'etat avant l'action
     */
    Engine undo();

    /**
     * Reprend la derniere action
     * @return le Engine dans l'etat apres le Redo
     */
    Engine redo();

    /**
     * Enregistre le Engine dans un memento et l'ajoute à une Pile d'actions
     * @param engine
     */
    void save(Engine engine);

}
