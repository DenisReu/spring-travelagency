package com.byhovsky.agency.repository.impl;

import com.byhovsky.agency.entity.Tour;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TourRepositoryImpl provides the methods of
 * manipulating  tours data
 *
 * @author Denis Byhovsky
 */
@Deprecated
public class TourRepositoryImpl implements Repository<Tour> {

    private List<Tour> tours;

    public TourRepositoryImpl(List<Tour> tours) {
        this.tours = tours;
    }

    /**
     * Constructs a new TourRepositoryImpl.
     */
    public TourRepositoryImpl() {
    }

    /**
     * Getter for property 'tours'.
     *
     * @return Value for property 'tours'.
     */
    public List<Tour> getTours() {
        return tours;
    }

    /**
     * Setter for property 'tours'.
     *
     * @param tours Value to set for property 'tours'.
     */
    public void setTours(CopyOnWriteArrayList<Tour> tours) {
        this.tours = tours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Tour> add(Tour tour) throws RepositoryException {
        if (tours.contains(tour)) {
            throw new RepositoryException("Cant add tour");
        } else {
            tours.add(tour);
            return Optional.of(tour);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tour> getAll() {
        return tours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Tour tour) throws RepositoryException {
        if (tours.contains(tour)) {
            tours.remove(tour);
            return true;
        } else {
            throw new RepositoryException("Cant remove tour");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Tour> update(Tour tour) throws RepositoryException {
        if (tours.get(tour.getTourId()) != null) {
            tours.set(tour.getTourId(), tour);
            return Optional.of(tour);
        } else {
            throw new RepositoryException("Cant update tour");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Tour> read(Integer id) {
        throw new UnsupportedOperationException();
    }
}
