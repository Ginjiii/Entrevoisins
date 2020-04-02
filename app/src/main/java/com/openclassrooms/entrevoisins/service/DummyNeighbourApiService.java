package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favorite = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbour() {

        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * return a list of favorite neighbour
     *
     * @return
     */
    @Override
    public List<Neighbour> getFavorite() {

        return favorite;
    }

    @Override
    public void addFavorite(Neighbour neighbour) {

        if (favorite.contains(neighbour)) {
            favorite.add(neighbour);
        }
    }
        /**
         * delete neighbours from favorites list
         * @param neighbour
         */
        @Override
        public void deleteFavorite(Neighbour neighbour) {

            if(favorite.contains(neighbour)){

                favorite.remove(neighbour);
            }
    }
}
