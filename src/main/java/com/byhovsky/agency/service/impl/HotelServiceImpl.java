package com.byhovsky.agency.service.impl;

import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * HotelServiceImpl  provides the methods of
 * service layer
 *
 * @author Denis Byhovsky
 */
@org.springframework.stereotype.Service
public class HotelServiceImpl extends AbstractService<Hotel> implements Service<Hotel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelServiceImpl.class.getName());

    private Repository<Hotel> repository;

    @Autowired
    public HotelServiceImpl(Repository<Hotel> repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hotel create(Hotel hotel) {
        Optional<Hotel> optionalHotel = repository.add(hotel);
        LOGGER.info("Hotel was created successfully");
        return optionalHotel.orElseThrow(
                () -> new RepositoryException("Cant create hotel in hotel create method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hotel update(Hotel hotel) {
        Optional<Hotel> optionalHotel = repository.update(hotel);
        LOGGER.info("Hotel was updated successfully");
        return optionalHotel.orElseThrow(
                () -> new RepositoryException("Cant update hotel in hotel update method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Hotel hotel) throws RepositoryException {
        repository.remove(hotel);
        LOGGER.info("Hotel was deleted successfully");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Hotel> read() {
        return repository.getAll();
    }
}
