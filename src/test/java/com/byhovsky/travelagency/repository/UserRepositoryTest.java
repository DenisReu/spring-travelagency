package com.byhovsky.travelagency.repository;

import com.byhovsky.agency.entity.User;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.UserRepositoryImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * UserRepositoryTest
 *
 * @author Denis Byhovsky
 */
public class UserRepositoryTest {
    private UserRepositoryImpl userRepository;
    private User user;
    private User user1;
    private User user3;
    private char[] pass;

    @Before
    public void init() {
        pass = new char[]{'K', 'E', 'V', 'I', 'N'};
        CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
        user = new User( "newAccc11", pass);
        user1 = new User("newAcccww", pass);
        users.add(user);
        users.add(user1);
        userRepository = new UserRepositoryImpl(users);
    }

    @Test
    public void addUserTest() throws RepositoryException {
        user3 = new User( "newAccc33", pass);
        Optional<User> optionalUser = userRepository.add(user3);
        Assert.assertTrue(optionalUser.isPresent());
        Assert.assertEquals(user3, optionalUser.get());
    }

    @Test
    public void readUserTest() {
        Assert.assertNotNull(userRepository.getAll());
    }

    @Test
    public void updateUserTest() throws RepositoryException {
        char[] pass1 = new char[]{'K', 'E', 'W', 'I', 'R'};
        user3 = new User( "newAccc33", pass1);
        Optional<User> optionalUser = userRepository.add(user3);
        Assert.assertTrue(optionalUser.isPresent());
        Assert.assertEquals(user3, optionalUser.get());
    }

    @Test
    public void deleteUserTest() throws RepositoryException {
        Assert.assertTrue(userRepository.remove(user));
    }
}
