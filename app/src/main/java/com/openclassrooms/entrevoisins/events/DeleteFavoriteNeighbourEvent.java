package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavoriteNeighbourEvent {
    /**
     * Event fired when a user deletes a Neighbour
     */

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteFavoriteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}

