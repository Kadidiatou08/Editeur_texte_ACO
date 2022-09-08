package fr.istic.aco.editor;

import java.util.Optional;

/**
 *Classe qui gère la sauvegarde de l’état actuel et la récupération des données
 * @author SANOGO Kadidiatou
 * @version 1.0
 */
public interface Originator {

	/**
     * Sauvegarde la Commande ou le Engine dans un memento
     * @return memento sauvegarder
     */
    Optional<Memento> getMemento();

    /**
     * Restore l'état sauvegarder dans le memento
     * @param m
     */
    void setMemento(Memento memento);}
