package fr.istic.aco.editor.Receiver;

import fr.istic.aco.editor.Command;

/**
 * API qui permet de gerer l'enregistrement et le replay des commandes
 * @author SANOGO Kadidiatou
 * @version 1.0
 */
public interface Recorder {

	/**
     * Sauvegarde la commande
     * @param command
     */
    void save(Command command);

    /**
     * Demarre l'enregistrement des commandes
     */
    void start();

    /**
     * Arrete l'enregistrement des Commandes
     */
    void stop();

    /**
     * Rejeux des Actions enregirstrees
     */
    void replay();
}
