package com.byhovsky.travelagency.repository;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.entity.Tour;
import com.byhovsky.agency.entity.TourType;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.TourRepositoryImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TourRepositoryTest
 *
 * @author Denis Byhovsky
 */
public class TourRepositoryTest {
    private TourRepositoryImpl tourRepository;
    private Tour tour;
    private Tour tour1;
    private Tour tour3;
    private Country country;
    private Hotel hotel;

    @Before
    public void init() {
        CopyOnWriteArrayList<Tour> tours = new CopyOnWriteArrayList<>();
        country = new Country( "Russia");
        hotel = new Hotel( 2,"Russia-hotel", "37434", 4);
        tour = new Tour(8, "1.jpg", new BigDecimal(343), "For two person", 5, country, TourType.BICUCLE, hotel, LocalDate.of(2017, 05, 06));
        tour1 = new Tour(9, "2.jpg", new BigDecimal(322), "For one person", 6,  country, TourType.BICUCLE, hotel,LocalDate.of(2017, 05, 06));
        tours.add(tour);
        tours.add(tour1);
        tourRepository = new TourRepositoryImpl(tours);
    }

    @Test
    public void addTourTest() throws RepositoryException {
        tour3 = new Tour( 10,"d", new BigDecimal(322), "For one person", 6, country, TourType.BICUCLE, hotel,LocalDate.of(2017, 05, 06));
        Optional<Tour> optionalTour = tourRepository.add(tour3);
        Assert.assertTrue(optionalTour.isPresent());
        Assert.assertEquals(tour3, optionalTour.get());
    }

    @Test
    public void readTourTest() {
        Assert.assertNotNull(tourRepository.getAll());
    }

    @Test
    public void updateTourTest() throws RepositoryException {
        tour3 = new Tour( 1,"e", new BigDecimal(322), "For one person", 6, country, TourType.BICUCLE, hotel,LocalDate.of(2017, 05, 06));
        Optional<Tour> optionalTour = tourRepository.update( tour3);
        Assert.assertTrue(optionalTour.isPresent());
        Assert.assertEquals(tour3, optionalTour.get());
    }

    @Test
    public void deleteTourTest() throws RepositoryException {
        Assert.assertTrue(tourRepository.remove(tour));
    }
}
