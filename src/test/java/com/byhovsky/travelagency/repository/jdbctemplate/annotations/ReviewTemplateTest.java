package com.byhovsky.travelagency.repository.jdbctemplate.annotations;

import com.byhovsky.agency.ContextConfiguration;
import com.byhovsky.agency.entity.*;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.repository.jdbctemplate.ReviewTemplate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReviewTemplateTest {

    private static AnnotationConfigApplicationContext ctx;
    private static Repository<Review> reviewTemplate;

    private Review review1;
    private Review review;
    private Tour tour;
    private Country country;
    private Hotel hotel;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        country = new Country("Russia");
        hotel = new Hotel(3, "Russia-hotel", "37434", 4);
        tour = new Tour(7, "1.jpg", new BigDecimal(343), "For two person", 5, country, TourType.BICUCLE, hotel, LocalDate.of(2017, 05, 06));
        review1 = new Review("good", tour);
        review = new Review("so-so", tour);
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");
        ctx.register(ContextConfiguration.class);
        ctx.refresh();
        reviewTemplate = ctx.getBean(ReviewTemplate.class);
    }

    /**
     * Create
     */
    @Test
    public void create() {
        //for procedures
        //  Assert.assertEquals(review, reviewTemplate.add(review));
    }

    /**
     * Read
     */
    @Test
    public void read() {
        Assert.assertEquals("bad", reviewTemplate.read(2).get().getContent());
    }

    /**
     * Find all
     */
    @Test
    public void findAll() {
        Assert.assertEquals(3, reviewTemplate.getAll().size());
    }

    /**
     * Delete
     */
    @Test
    public void delete() {
        Assert.assertTrue(reviewTemplate.remove(review1));

    }

    /**
     * Close
     */
    @After
    public void close() {
        ctx.close();
    }

}
