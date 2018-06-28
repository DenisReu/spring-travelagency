package com.byhovsky.agency.repository.impl;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * CountryRepositoryImpl provides the methods of
 * manipulating  countries data
 *
 * @author Denis Byhovsky
 */
@Deprecated
public class CountryRepositoryImpl implements Repository<Country> {

    private List<Country> countries;

    public CountryRepositoryImpl(List<Country> countries) {
        this.countries = countries;
    }

    /**
     * Constructs a new CountryRepositoryImpl.
     */
    public CountryRepositoryImpl() {
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Country> add(Country country) {
        if (!countries.contains(country)) {
            countries.add(country);
            return Optional.of(country);
        } else {
            throw new RepositoryException("Cant create country");
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getAll() {
        return countries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Country country) {
        if (countries.contains(country)) {
            countries.remove(country);
            return true;
        } else {
            throw new RepositoryException("Cant remove");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Country> update(Country country) {
        if (countries.get(country.getId()) != null) {
            countries.set(country.getId(), country);
            return Optional.of(country);
        } else {
            throw new RepositoryException("Cant update");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Country> read(Integer id) {
        throw new UnsupportedOperationException();
    }
}
