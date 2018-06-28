package com.byhovsky.agency.service.impl;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


/**
 * CountryServiceImpl  provides the methods of
 * service layer
 *
 * @author Denis Byhovsky
 */
@org.springframework.stereotype.Service
public class CountryServiceImpl extends AbstractService<Country> implements Service<Country> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class.getName());

    private Repository<Country> repository;

    @Autowired
    public CountryServiceImpl(Repository<Country> repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country create(Country country) {
        Optional<Country> optionalCountry = repository.add(country);
        LOGGER.info("Country was created successfully");
        return optionalCountry.orElseThrow(
                () -> new RepositoryException("Cant create country in country create method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country update(Country country) {
        Optional<Country> optionalCountry = repository.update(country);
        LOGGER.info("Country was updated successfully");
        return optionalCountry.orElseThrow(
                () -> new RepositoryException("Cant update country in country update method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Country country) throws RepositoryException {
        LOGGER.info("Country was deleted successfully");
        return repository.remove(country);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> read() {
        return repository.getAll();
    }
}
