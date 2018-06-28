package com.byhovsky.travelagency.service;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.service.impl.CountryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * CountryServiceTest
 *
 * @author Denis Byhovsky
 */

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CountryServiceTest {

    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private Repository<Country> repository;

    private CopyOnWriteArrayList<Country> countries;
    private Country country;
    private Country country1;
    private Country country2;
    private Country country3;

    @Before
    public void init() {
        countries = new CopyOnWriteArrayList<>();
        country = new Country("Germany");
        country1 = new Country("Spain");
        country2 = new Country("Poland");
        country3 = new Country("Turkey");
        countries.add(country);
        countries.add(country1);
        countries.add(country2);
        countries.add(country3);
    }

    @Test
    public void addCountryTest() throws RepositoryException {
        when(repository.add(country)).thenReturn(Optional.of(country));
        assertEquals(country, countryService.create(country));
    }

    @Test
    public void addCountryVerTets() throws RepositoryException {
        repository.add(country);
        verify(repository, times(1)).add(country);
    }

    @Test
    public void updateCountryTest() throws RepositoryException {
        when(repository.update(country)).thenReturn(Optional.of(country));
        assertEquals(country, countryService.update(country));
    }

    @Test
    public void updateCountryVerTest() throws RepositoryException {
        repository.update(country);
        verify(repository, times(1)).update(country);
    }

    @Test
    public void deleteCountryTest() throws RepositoryException {
        when(repository.remove(country)).thenReturn(true);
        assertTrue(countryService.delete(country));
    }

    @Test
    public void deleteCountryVerTest() throws RepositoryException {
        repository.remove(country);
        verify(repository, times(1)).remove(country);
    }
}
