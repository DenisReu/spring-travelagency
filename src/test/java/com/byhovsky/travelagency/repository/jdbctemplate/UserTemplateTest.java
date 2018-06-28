package com.byhovsky.travelagency.repository.jdbctemplate;

import com.byhovsky.agency.entity.User;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.repository.jdbctemplate.UserTemplate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Optional;

public class UserTemplateTest {

    private static GenericXmlApplicationContext ctx;
    private static Repository<User> userTemplate;
    private char[] pass;
    private User user;
    private User user2;
    private User user3;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        pass = new char[]{'K', 'E', 'V', 'I', 'N'};
        user2 = new User("newAccc11", pass);
        user = new User("newAcccww", pass);
        user3 = new User("newAcccww1", pass);
        System.setProperty("spring.profiles.active", "postgres");
        ctx = new GenericXmlApplicationContext();
        ctx.load("test.xml");
        ctx.refresh();
        userTemplate = (UserTemplate) ctx.getBean("userRepository");
    }

    /**
     * tour
     * Create.
     */
    @Test
    public void create() {
        //procedure
        // Assert.assertEquals(user3, userTemplate.add(user3));
    }

    /**
     * Update.
     */
    @Test
    public void update() {
        Assert.assertEquals(Optional.of(user2), userTemplate.update(user2));
    }

    /**
     * Delete.
     */
    @Test
    public void delete() {
        Assert.assertTrue(userTemplate.remove(user3));
    }


    /**
     * Close.
     */
    @After
    public void close() {
        ctx.close();
    }

}
