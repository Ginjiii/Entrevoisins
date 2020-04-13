package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbourList = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoriteList = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbour() {

        return neighbourList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbourList.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbourList.add(neighbour);
    }

    /**
     * return a list of favorite neighbour
     *
     * @return
     */
    @Override
    public List<Neighbour> getFavorite() {

        return favoriteList;
    }

    @Override
    public void addFavorite(Neighbour neighbour) {

        if (!favoriteList.contains(neighbour)) {
            favoriteList.add(neighbour);
        }
    }
        /**
         * delete neighbour from favorite list
         * @param neighbour
         */
        @Override
        public void deleteFavorite(Neighbour neighbour) {

            if(favoriteList.contains(neighbour)){

                favoriteList.remove(neighbour);
            }
    }
}
