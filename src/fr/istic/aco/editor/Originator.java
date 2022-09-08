package fr.istic.aco.editor;

import java.util.Optional;

/**
 *Classe qui g�re la sauvegarde de l��tat actuel et la r�cup�ration des donn�es
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
     * Restore l'�tat sauvegarder dans le memento
     * @param m
     */
    void setMemento(Memento memento);}
