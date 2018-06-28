package com.byhovsky.agency.service.impl;

import com.byhovsky.agency.entity.Tour;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TourServiceImpl  provides the methods of
 * service layer
 *
 * @author Denis Byhovsky
 */
@org.springframework.stereotype.Service
public class TourServiceImpl extends AbstractService<Tour> implements Service<Tour> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceImpl.class.getName());

    CopyOnWriteArrayList<Tour> tours;
    private Repository<Tour> repository;

    @Autowired
    public TourServiceImpl(Repository<Tour> repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tour create(Tour tour) {
        Optional<Tour> optionalTour = repository.add(tour);
        LOGGER.info("Tour was created successfully");
        return optionalTour.orElseThrow(
                () -> new RepositoryException("Cant create tour in tour create method")
        );
    }

    /**
     * Update Tour description
     *
     * @param index
     * @param newDescription
     */
    public void updateDescription(int index, String newDescription) {
        tours = new CopyOnWriteArrayList<>();
        tours.get(index).setDescription(newDescription);
        LOGGER.info("Description was updated successfully");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tour update(Tour tour) {
        Optional<Tour> optionalTour = repository.update(tour);
        LOGGER.info("Tour was updated successfully");
        return optionalTour.orElseThrow(
                () -> new RepositoryException("Cant update tour in tour update method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Tour tour) throws RepositoryException {
        repository.remove(tour);
        LOGGER.info("Tour was deleted successfully");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tour> read() {
        return repository.getAll();
    }
}
