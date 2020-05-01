package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbour();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbour().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbour().contains(neighbourToDelete));
        Neighbour neighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/350?u=a042581f4e29026704d", "lyon ; 5km",
                "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.addFavorite(neighbour);
        Neighbour neighbourFavToDelete = service.getFavorite().get(0);
        service.deleteFavorite(neighbourFavToDelete);
        assertFalse(service.getFavorite().contains(neighbourFavToDelete));

    }

    @Test
    public void getNeighbourFavorite (){
        List<Neighbour> mFavoriteNeighbour = service.getNeighbour();
        List<Neighbour> expectedmFavoriteNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(mFavoriteNeighbour, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedmFavoriteNeighbour.toArray()));



    }

    @Test
    public void addNeighbourFavorite (){
        Neighbour neighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/350?u=a042581f4e29026704d", "lyon ; 5km",
                "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.addFavorite(neighbour);
        assertEquals(1, service.getFavorite().size());



    }

    @Test
    public void deleteNeighbourFavorite (){
        Neighbour neighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/350?u=a042581f4e29026704d", "lyon ; 5km",
                "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.addFavorite(neighbour);
        Neighbour neighbourFavToDelete = service.getFavorite().get(0);
        service.deleteFavorite(neighbourFavToDelete);
        assertFalse(service.getFavorite().contains(neighbourFavToDelete));
    }
}
