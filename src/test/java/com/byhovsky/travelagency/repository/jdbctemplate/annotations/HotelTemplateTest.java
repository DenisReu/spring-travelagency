package com.byhovsky.travelagency.repository.jdbctemplate.annotations;

import com.byhovsky.agency.ContextConfiguration;
import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.entity.Order;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.repository.jdbctemplate.HotelTemplate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HotelTemplateTest {
    private static AnnotationConfigApplicationContext ctx;
    private static Repository<Hotel> hotelTemplate;

    private Hotel hotel;
    private Hotel hotel1;
    private Hotel hotel2;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        hotel = new Hotel(1, "Russia-hotel", "37434", 4);
        hotel1 = new Hotel(2, "Belarus-hotel", "33243", 3);
        hotel2 = new Hotel(3, "Italy-hotel", "33243", 2);
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");
        ctx.register(ContextConfiguration.class);
        ctx.refresh();
        hotelTemplate = ctx.getBean(HotelTemplate.class);
    }

    /**
     * Create.
     */
    @Test
    public void create() {
        //for procedures
        // Assert.assertEquals(hotel,hotelTemplate.add(hotel));
    }

    /**
     * Read
     */
    @Test
    public void read() {
        String expected = "Europe";
        Assert.assertEquals(expected, hotelTemplate.read(9).get().getHotelName());
    }

    /**
     * Delete.
     */
    @Test
    public void delete() {
        Assert.assertTrue(hotelTemplate.remove(hotel2));
    }

    /**
     * Find all.
     */
    @Test
    public void findAll() {
        int expected = 3;
        Assert.assertEquals(expected, hotelTemplate.getAll().size());
    }

    /**
     * Close.
     */
    @After
    public void close() {
        ctx.close();
    }
}
