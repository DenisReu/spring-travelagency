package com.byhovsky.travelagency.repository.jdbctemplate.annotations;

import com.byhovsky.agency.ContextConfiguration;
import com.byhovsky.agency.entity.User;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.repository.jdbctemplate.UserTemplate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class UserTemplateTest {

    private static AnnotationConfigApplicationContext ctx;
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
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");
        ctx.register(ContextConfiguration.class);
        ctx.refresh();
        userTemplate = ctx.getBean(UserTemplate.class);
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
