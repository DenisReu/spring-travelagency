package com.byhovsky.travelagency.repository.jdbctemplate.annotations;

import com.byhovsky.agency.ContextConfiguration;
import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.entity.Tour;
import com.byhovsky.agency.entity.TourType;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.repository.jdbctemplate.TourTemplate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

//todo
public class TourTemplateTest {

    private static AnnotationConfigApplicationContext ctx;
    private static Repository<Tour> tourTemplate;

    private Tour tour;
    private Tour tour1;
    private Tour tour2;
    private Country country;
    private Hotel hotel;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {

        country = new Country("Russia");
        hotel = new Hotel(2, "Russia-hotel", "37434", 4);
        tour = new Tour(12, "e", new BigDecimal(343), "For two person", 5, country, TourType.BICUCLE, hotel, LocalDate.of(2017, 05, 06));
        tour1 = new Tour(13, "e", new BigDecimal(322), "For one person", 6, country, TourType.BICUCLE, hotel, LocalDate.of(2017, 05, 06));
        tour2 = new Tour(14, "e", new BigDecimal(322), "For one person", 6, country, TourType.BICUCLE, hotel, LocalDate.of(2017, 05, 06));
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");
        ctx.register(ContextConfiguration.class);
        ctx.refresh();
        tourTemplate = ctx.getBean(TourTemplate.class);
    }

    /**
     * Create.
     */
    @Test
    public void create() {
        //procedures
        // Assert.assertEquals(tour, tourTemplate.add(tour));
    }

    /**
     * Delete.
     */
    @Test
    public void delete() {
        Assert.assertTrue(tourTemplate.remove(tour));
    }

    /**
     * Update.
     */
    @Test
    public void update() {
        Assert.assertEquals(Optional.of(tour2), tourTemplate.update(tour2));
    }


    /**
     * Find all.
     */
    @Test
    public void findAll() {
        int size = 3;
        Assert.assertEquals(size, tourTemplate.getAll().size());
    }

    /**
     * Close.
     */
    @After
    public void close() {
        ctx.close();
    }
}
