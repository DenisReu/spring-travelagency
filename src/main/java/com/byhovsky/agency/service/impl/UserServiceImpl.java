package com.byhovsky.agency.service.impl;

import com.byhovsky.agency.entity.User;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.exception.ServiceException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * UserServiceImpl  provides the methods of
 * service layer
 *
 * @author Denis Byhovsky
 */
@org.springframework.stereotype.Service
public class UserServiceImpl extends AbstractService<User> implements Service<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    private Repository<User> repository;

    @Autowired
    public UserServiceImpl(Repository<User> repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User create(User user) {
        Optional<User> optionalUser = repository.add(user);
        LOGGER.info("User  was created successfully");
        return optionalUser.orElseThrow(
                () -> new ServiceException("Cant create user in user create method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User update(User user) {
        Optional<User> optionalUser = repository.update(user);
        LOGGER.info("User  was updated successfully");
        return optionalUser.orElseThrow(
                () -> new ServiceException("Cant update user in user update method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(User user) throws RepositoryException {
        repository.remove(user);
        LOGGER.info("User  was deleted successfully");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> read() {
        return repository.getAll();
    }
}
