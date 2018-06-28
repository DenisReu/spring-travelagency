package com.byhovsky.travelagency.repository.jdbctemplate;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.repository.jdbctemplate.CountryTemplate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CountryTemplateTest {

    private static GenericXmlApplicationContext ctx;
    private static Repository<Country> countryTemplate;

    private Country country;
    private Country country1;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        country = new Country("Russia");
        country1 = new Country("SAN-MARINO");
        System.setProperty("spring.profiles.active", "collection");
        ctx = new GenericXmlApplicationContext();
        ctx.load("test.xml");
        ctx.refresh();
        countryTemplate = (CountryTemplate) ctx.getBean("countryRepository");
    }

    /**
     * Read.
     */
    @Test
    public void read() {
        Assert.assertEquals("Italy", countryTemplate.read(13).get().getName());
    }

    /**
     * Create.
     */
    @Test
    public void create() {
        //for procedures
        //Assert.assertEquals(country1, countryTemplate.add(country1));
    }

    /**
     * Delete.
     */
    @Test
    public void delete() {
        Assert.assertTrue(countryTemplate.remove(country1));
    }

    /**
     * Find all.
     */
    @Test
    public void findAll() {
        int size = 3;
        Assert.assertEquals(size, countryTemplate.getAll().size());
    }

    /**
     * Close.
     */
    @After
    public void close() {
        ctx.close();
    }
}

