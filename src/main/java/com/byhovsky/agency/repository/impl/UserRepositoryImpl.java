package com.byhovsky.agency.repository.impl;

import com.byhovsky.agency.entity.User;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * UserRepositoryImpl provides the methods of
 * manipulating  users  data
 *
 * @author Denis Byhovsky
 */
@Deprecated
public class UserRepositoryImpl implements Repository<User> {

    private List<User> users;

    public UserRepositoryImpl(List<User> users) {
        this.users = users;
    }

    /**
     * Constructs a new UserRepositoryImpl.
     */
    public UserRepositoryImpl() {
    }

    /**
     * Getter for property 'users'.
     *
     * @return Value for property 'users'.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Setter for property 'users'.
     *
     * @param users Value to set for property 'users'.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> add(User user) throws RepositoryException {
        if (!users.contains(user)) {
            users.add(user);
            return Optional.of(user);

        } else {
            throw new RepositoryException("Cant add review");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAll() {
        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(User user) throws RepositoryException {
        if (users.contains(user)) {
            users.remove(user);
            return true;
        } else {
            throw new RepositoryException("Cant remove user");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> update(User user) throws RepositoryException {
        if (users.get(user.getUserId()) != null) {
            users.set(user.getUserId(), user);
            return Optional.of(user);
        } else {
            throw new RepositoryException("Cant update user");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> read(Integer id) {
        throw new UnsupportedOperationException();
    }
}
