package com.byhovsky.travelagency.repository.jdbctemplate;

import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.entity.Order;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.repository.jdbctemplate.HotelTemplate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HotelTemplateTest {
    private static GenericXmlApplicationContext ctx;
    private static Repository<Hotel> hotelTemplate;
    private static Repository<Order> orderTemplate;

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
        System.setProperty("spring.profiles.active", "postgres");
        ctx = new GenericXmlApplicationContext();
        ctx.load("test.xml");
        ctx.refresh();
        hotelTemplate = (HotelTemplate) ctx.getBean("hotelRepository");
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
