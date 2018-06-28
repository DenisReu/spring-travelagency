package com.byhovsky.travelagency.repository;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.CountryRepositoryImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CountryRepositoryTest
 *
 * @author Denis Byhovsky
 */
public class CountryRepositoryTest {

    private CountryRepositoryImpl countryRepository;
    private CopyOnWriteArrayList<Country> countries;
    private Country country;
    private Country country1;
    private Country country3;
    private Country country4;

    @Before
    public void init() {
        countries = new CopyOnWriteArrayList<>();
        country = new Country("Russia");
        countries.add(country);
        countryRepository = new CountryRepositoryImpl(countries);
    }

    @Test
    public void addCountryTest() throws RepositoryException {
        country1 = new Country("Belarus");
        Optional<Country> optionalCountry = countryRepository.add(country1);
        Assert.assertTrue(optionalCountry.isPresent());
        Assert.assertEquals(country1, optionalCountry.get());
    }

    @Test
    public void addCountryFailTest() throws RepositoryException {
        country4 = new Country("Bulgaria");
        Assert.assertNotSame(countries.get(0), countryRepository.add(country4));
    }

    @Test
    public void readCountryTest() {
        Assert.assertNotNull(countryRepository.getAll());
    }

    @Test
    public void updateCountryTest() throws RepositoryException {
        country3 = new Country("Spain");
        Optional<Country> optionalCountry = countryRepository.update(country3);
        Assert.assertTrue(optionalCountry.isPresent());
        Assert.assertEquals(country3, optionalCountry.get());
    }

    @Test
    public void deleteCountryTest() throws RepositoryException {
        Assert.assertTrue(countryRepository.remove(country));
    }
}
